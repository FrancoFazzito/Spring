package com.bolsadeideas.springboot.datajpa.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.datajpa.app.entity.Nota;

@Repository("repositorioNota")
public interface NotaRepository extends JpaRepository<Nota, Serializable>,PagingAndSortingRepository<Nota, Serializable> {
	
	public List<Nota> findByNombre(String nombre);

	public Nota findByTitulo(String titulo);

	public Page<Nota> findAll(Pageable pageable);
	
	public List<Nota> findAll();
}
