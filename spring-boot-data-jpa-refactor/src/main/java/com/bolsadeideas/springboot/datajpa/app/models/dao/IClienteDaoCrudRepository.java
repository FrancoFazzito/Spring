package com.bolsadeideas.springboot.datajpa.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;

public interface IClienteDaoCrudRepository extends PagingAndSortingRepository<Cliente, Long>{

}

