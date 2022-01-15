package com.williamab.desafiopubfuture.service;

import org.springframework.data.domain.Page;

import com.williamab.desafiopubfuture.model.BasicEntity;

/**
 * Serviço de base para os serviços da aplicação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 * @param E uma entidade que estenda {@link BasicEntity}
 */
public interface BasicService<E extends BasicEntity> {

	/**
	 * Adiciona ou atualiza uma entidade.
	 * 
	 * @param entity a entidade a ser salva/atualizada
	 * @return a entidade salva/atualizada
	 */
	E save(E entity);

	/**
	 * Remove uma entidade a partir do seu id.
	 * 
	 * @param id o id da entidade a ser removida
	 */
	void deleteById(Long id);

	/**
	 * Busca uma entidade pelo id. Se não encontrar retorna <code>null</code>.
	 * 
	 * @param id o id da entidade a ser buscada
	 * @return a entidade
	 */
	E findById(Long id);

	/**
	 * Busca as entidades da primeira página, com o limite de 20 resultados.
	 * 
	 * @return uma lista de entidades
	 */
	Page<E> findAll();

	/**
	 * Busca as entidades a partir da página. O limite de resultados será 20.
	 * 
	 * @param page o número da página a ser buscada
	 * @return uma lista de entidades
	 */
	Page<E> findByPage(int page);

	/**
	 * Busca as entidades a partir da página e do limite de resultados. O limite
	 * máximo de resultados é 20.
	 * 
	 * @param page  o número da página a ser buscada
	 * @param limit o limite de resultados, o máximo é 20
	 * @return uma lista de entidades
	 */
	Page<E> findByPage(int page, int limit);

}
