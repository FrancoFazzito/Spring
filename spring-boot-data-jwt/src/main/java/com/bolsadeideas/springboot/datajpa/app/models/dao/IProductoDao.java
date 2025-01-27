package com.bolsadeideas.springboot.datajpa.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Producto;

import java.util.List;


public interface IProductoDao extends CrudRepository<Producto, Long>{

	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
}
