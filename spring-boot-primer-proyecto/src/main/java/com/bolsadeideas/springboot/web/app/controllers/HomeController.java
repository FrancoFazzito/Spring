package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//redirect se reinicia la peticion
//forward dirige al handler sin recargar la pagina

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "forward:/app/index/";
	}
	
	@GetMapping("/google")
	public String google() {
		return "redirect:https://www.google.com";
	}
	
	/*
	@GetMapping("/")
	public String home() {
		return "redirect:/app/index/";
	}
	*/
}
