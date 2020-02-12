package com.bolsadeideas.springboot.datajpa.app.auth.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTauthorizationFilter extends BasicAuthenticationFilter{ // se ejecuta en cada request

	public JWTauthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		
		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		
		boolean tokenValid;
		Claims token = null;
		try {
			token = Jwts.parser()
			.setSigningKey("ALGUNA.CLAVE.SECRETA.PASSWORD.123.CONFIRM".getBytes())
			.parseClaimsJws(header.replace("Bearer ","")).getBody();
			tokenValid = true;
		} catch (JwtException | IllegalArgumentException e) {
			tokenValid = false;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = null;
		
		if(tokenValid) {
			String userName = token.getSubject();
			Object rolesRecibido = token.get("Authorities");
			Collection<? extends GrantedAuthority> roles = Arrays.asList(new ObjectMapper()
					.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
					.readValue(rolesRecibido.toString().getBytes(), SimpleGrantedAuthority[].class));
			authenticationToken = new UsernamePasswordAuthenticationToken(userName,null,roles);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}
	
	protected boolean requiresAuthentication(String header) {
		return header == null || !header.startsWith("Bearer ");
	}

}


