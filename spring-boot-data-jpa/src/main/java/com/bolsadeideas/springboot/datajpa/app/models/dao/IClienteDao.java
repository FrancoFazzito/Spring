package com.bolsadeideas.springboot.datajpa.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente> findAll();
	public void Save(Cliente cliente);
	public void delete(Long id);
	public Cliente findOne(Long id);
	
}
