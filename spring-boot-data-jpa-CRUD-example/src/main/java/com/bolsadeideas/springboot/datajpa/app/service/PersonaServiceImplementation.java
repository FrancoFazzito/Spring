package com.bolsadeideas.springboot.datajpa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.datajpa.app.commons.GenericServiceImplementation;
import com.bolsadeideas.springboot.datajpa.app.dao.PersonaDao;
import com.bolsadeideas.springboot.datajpa.app.model.Persona;

@Service
public class PersonaServiceImplementation extends GenericServiceImplementation<Persona, Long>{

	@Autowired
	private PersonaDao personaDao;
	
	@Override
	public CrudRepository<Persona, Long> getDao() {
		return personaDao;
	}

}
