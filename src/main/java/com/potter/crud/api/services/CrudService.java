package com.potter.crud.api.services;

import java.util.List;
import java.util.Optional;

public interface CrudService <T> {
	
	T save(T entity);

	List<T> findAll();

	Optional<T> findById(String id);

	T update(T entity);

	void deleteById(String id);
	
	void deleteAll();

}

