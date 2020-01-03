package com.bolsadeideas.springboot.id.app.controllers;
//tiene que estar en package com.bolsadeideas.springboot.id.app

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsadeideas.springboot.id.app.models.service.IServiciable;
//import com.bolsadeideas.springboot.id.app.models.service.Servicio;

@Controller
public class IndexController {

	///private Servicio servicio = new Servicio(); muy acoplado y se busca sacar el new 
	
	//@Autowired
	//private Servicio servicio; se puede reemplazar por una interfaz Iservicio 
	
	@Autowired
	@Qualifier("servicioIntermedio")
	private IServiciable servicio;
	
	@GetMapping({"/","","/index"})
	public String index(Model model) {	
		
		model.addAttribute("resultado",servicio.operacion());
		return "index";
	}
	
//	otras 2 formas
//	@Autowired
//	public IndexController(IServiciable servicio) {
//		this.servicio = servicio;
//	}
//	
//	@Autowired
//	public void setServicio(IServiciable servicio) {
//		this.servicio = servicio;
//	}
	
	
}
