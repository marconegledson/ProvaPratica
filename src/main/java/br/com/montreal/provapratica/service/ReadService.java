package br.com.montreal.provapratica.service;

import java.util.List;

public interface ReadService<T> {

	/**
	 * Faz a pesquisa utilizando o id que representa a classe
	 * @param id que representa a chave primaria
	 * @return O objeto especifico
	 */
	T findById(Long id);
	
	/**
	 * Busca por todos os registros de uma determinada classe
	 * @return A lista de todos os objetos 
	 */
	List<T> findAll();
	
}
