package com.bolsadeideas.springboot.datajpa.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;
import com.bolsadeideas.springboot.datajpa.app.models.entity.Factura;
import com.bolsadeideas.springboot.datajpa.app.models.entity.Producto;

public interface IclienteService {
	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public void Save(Cliente cliente);

	public void delete(Long id);

	public Cliente findOne(Long id);
	
	public List<Producto> findByNombre(String term);

	public Producto findProductById(Long id);
	
	public List<Producto> findAllProducts();
	
	public void SaveFactura(Factura factura);
}
