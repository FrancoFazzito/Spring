package com.bolsadeideas.springboot.datajpa.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.datajpa.app.model.Persona;

public interface PersonaDao extends CrudRepository<Persona, Long> {

}
