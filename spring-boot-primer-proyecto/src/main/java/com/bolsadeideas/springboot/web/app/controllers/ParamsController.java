package com.bolsadeideas.springboot.web.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class ParamsController {

	@GetMapping("/")
	public String index() {
		return "params/index";
	}
	
	@GetMapping("/string")
	//http://localhost:8080/params/string		/*indica si se necesita o no*/ /*valor por defecto*/
	public String param(@RequestParam(name = "texto", required = false, defaultValue = "no establecido") String texto,Model model) {
		model.addAttribute("resultado","el resultado es: " + texto);
		return "params/ver"; //indica el HTML dentro de templates
	}
	
	@GetMapping("/mix-params")
	public String param(@RequestParam String texto,@RequestParam Integer numero,Model model) {
		String mensaje = String.format("el texto enviado es: %s y su numero es: %d", texto,numero);
		model.addAttribute("resultado",mensaje);
		return "params/ver";
	}

	@GetMapping("/mix-params-request")
	public String param(HttpServletRequest request,Model model) {
		
		//http://localhost:8080/params/mix-params-request?texto=hola%20juan&numero=7
		String texto = request.getParameter("texto");
		Integer numero = Integer.parseInt(request.getParameter("numero"));
		
		String mensaje = String.format("el texto enviado es: %s y su numero es: %d", texto,numero);
		model.addAttribute("resultado",mensaje);
		return "params/ver";
		
		
	}
}
