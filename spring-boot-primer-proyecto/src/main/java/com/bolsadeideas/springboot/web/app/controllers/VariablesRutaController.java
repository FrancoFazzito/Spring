package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variables")
public class VariablesRutaController {
	
	@GetMapping("/")
	public String index (Model model) {
		model.addAttribute("titulo","enviar params de la ruta (@PathVariable)");
		return "variables/index";
	}
	
	//ruta tiene una ruta variable
	@GetMapping("/string/{texto}")
	public String variables(@PathVariable(name = "texto" /*mismo de ruta*/) String textoValue,Model model) {
		model.addAttribute("titulo","recibir params de la ruta (@PathVariable)");
		model.addAttribute("resultado","el texto enviado es:" + textoValue);
		return "variables/ver";
	}
	
	@GetMapping("/string/{texto}/{numero}")
	public String variables(@PathVariable String texto,@PathVariable Integer numero,Model model) {
		model.addAttribute("titulo","recibir params de la ruta (@PathVariable)");
		String mensaje = String.format("el texto enviado es: %s y su numero es: %d", texto,numero);
		model.addAttribute("resultado",mensaje);
		return "variables/ver";
	}
}
