package br.com.montreal.provapratica.service;

import java.util.List;

public interface CrudService<T> {

	T findById(Long id);
	
	List<T> findAll();
	
}
