package com.bolsadeideas.springboot.datajpa.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;
import com.bolsadeideas.springboot.datajpa.app.models.service.IclienteService;

@RestController
@RequestMapping("api/clientes")
public class ClienteRestController {
	
	@Autowired
	@Qualifier("clienteService")
	private IclienteService clienteService;
	
	@GetMapping(value = "/listar")
	@Secured("ROLE_ADMIN")
	public List<Cliente> listar(){
		return clienteService.findAll();
	}
	
}
