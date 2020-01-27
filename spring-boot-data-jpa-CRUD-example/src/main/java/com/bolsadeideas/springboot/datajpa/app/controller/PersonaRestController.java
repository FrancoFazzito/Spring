package com.bolsadeideas.springboot.datajpa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.datajpa.app.model.Persona;
import com.bolsadeideas.springboot.datajpa.app.service.PersonaServiceImplementation;


@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin("*")
public class PersonaRestController {

	@Autowired
	private PersonaServiceImplementation personaService;

	@GetMapping(value = "/all")
	public List<Persona> getAll() {
		return personaService.getAll();
	}
	
	@GetMapping(value = "/find/{id}")
	public Persona find(@PathVariable Long id) {
		return personaService.get(id);
	}

	@PostMapping(value = "/save")
	public ResponseEntity<Persona> save(@RequestBody Persona persona) {
		Persona obj = personaService.save(persona);
		return new ResponseEntity<Persona>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Persona> delete(@PathVariable Long id) {
		Persona persona = personaService.get(id);
		if (persona != null) {
			personaService.delete(id);
		}else {
			return new ResponseEntity<Persona>(persona, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}

}
