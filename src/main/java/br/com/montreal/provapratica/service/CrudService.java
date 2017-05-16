package br.com.montreal.provapratica.service;

import java.util.List;

public interface CrudService<T> {

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
	
	/**
	 * Salva uma nova entidade no banco
	 * @param entity a entidade generica a ser criada
	 * @return a entity salva no banco
	 */
	T save(T entity);
	
	/**
	 * Atualiza uma entidade no banco
	 * @param entity a entidade generica a ser atualizado
	 * @return a entity salva no banco
	 */
	T update(T entity);
	
	
	/**
	 * Exclua uma entidade cujo id exista no banco
	 * @param id
	 */
	void delete(Long id);
	
}
