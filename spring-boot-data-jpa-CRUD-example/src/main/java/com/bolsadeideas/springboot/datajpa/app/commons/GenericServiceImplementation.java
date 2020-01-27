package com.bolsadeideas.springboot.datajpa.app.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public abstract class GenericServiceImplementation<T,ID extends Serializable> implements GenericService<T, ID> {

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void delete(ID id) {
		getDao().deleteById(id);
	}

	@Override
	public T get(ID id) {
		return getDao().findById(id).orElse(null);
	}

	@Override
	public List<T> getAll() {
		List<T> returnList = new ArrayList<T>();
		getDao().findAll().forEach(obj -> returnList.add(obj));
		return returnList;
	}

	public abstract CrudRepository<T, ID> getDao();
	
}
