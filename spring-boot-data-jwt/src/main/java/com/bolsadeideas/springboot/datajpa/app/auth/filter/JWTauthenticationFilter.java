package com.bolsadeideas.springboot.datajpa.app.auth.filter;

import java.io.IOException;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTauthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager; //realizar auth con el user details service de fondo
	
	public JWTauthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException { //AUTENTICATION ACA
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);


		logger.info(username + " " + password); 
		
		if(username == null  && password == null) { //json raw en postman
			Usuario user = null;
			try {
				user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
				username = user.getUsername();
				password = user.getPassword();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		
		username = username.trim();
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);  
		
		return authManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		SecretKey secretKey = new SecretKeySpec("ALGUNA.CLAVE.SECRETA.PASSWORD.123.CONFIRM".getBytes(), SignatureAlgorithm.HS256.getJcaName());
		
		User user = (User) authResult.getPrincipal();
		String username = user.getUsername();
		
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		
		//datos extra
		Claims claims = Jwts.claims();
		claims.put("Authorities", new ObjectMapper().writeValueAsString(roles));
		
		String token = Jwts.builder()
				.setClaims(claims)
                .setSubject(username)
                .signWith(secretKey) //algoritmo
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000 * 2 /*2 horas*/))
                .compact();
		
		response.addHeader("Authorization", "Bearer " + token);
		
		Map<String , Object> body = new HashMap<String,Object>();
		
		body.put("token", token);
		body.put("user", user);
		body.put("mensaje", "hola " + username);
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body)); // escribir en json
		response.setStatus(200);
		response.setContentType("application/json");
	}

	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String , Object> body = new HashMap<String,Object>();
		body.put("mensaje", "Username o password incorrecto");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
	}
	
	
}
