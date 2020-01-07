package com.bolsadeideas.springboot.datajpa.app.models.dao.old;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao {
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return manager.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void Save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() > 0) {
			manager.merge(cliente); //update
		}else {
			manager.persist(cliente); //save
		}
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		manager.remove(findOne(id));
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return manager.find(Cliente.class, id);
	}
}
