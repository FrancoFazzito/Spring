package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/values")
public class ValueController {
	@Value("${texto.valuecontroller.index.titulo}")
	private String ejemploTitulo;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("titulo",ejemploTitulo);
		return "values/index";
	}
	
	
}
