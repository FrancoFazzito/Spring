package com.bolsadeideas.springboot.id.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.id.app.models.domain.Factura;
import com.bolsadeideas.springboot.id.app.models.domain.ItemFactura;

@Controller
@RequestMapping("/factura")
public class FacturaController {
	
	@Autowired
	private Factura factura;
	
	@GetMapping("/ver")
	public String ver(Model model) {
		model.addAttribute("titulo","factura con inyeccion de dependencia");
		model.addAttribute("factura",factura);
		for(ItemFactura item : factura.getFacturas()) {
			System.out.println(item.getProducto().getDescripcion());
		}
		return "facturas/ver";
	}
}
