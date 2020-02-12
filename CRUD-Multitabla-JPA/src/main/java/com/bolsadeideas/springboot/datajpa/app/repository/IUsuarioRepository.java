package com.bolsadeideas.springboot.datajpa.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.datajpa.app.entity.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario,Long>{

}
