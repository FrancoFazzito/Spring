package com.bolsadeideas.springboot.datajpa.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;

public interface IclienteService {
	public List<Cliente> findAll();
	public void Save(Cliente cliente);
	public void delete(Long id);
	public Cliente findOne(Long id);
}
