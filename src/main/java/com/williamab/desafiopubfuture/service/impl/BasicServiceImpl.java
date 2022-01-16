package com.williamab.desafiopubfuture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.williamab.desafiopubfuture.model.BasicEntity;
import com.williamab.desafiopubfuture.repository.BasicRepository;
import com.williamab.desafiopubfuture.service.BasicService;

/**
 * Implementação abstrata para {@link BasicService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 * 
 * @param E uma entidade que estenda {@link BasicEntity}
 * @param R um repositório que estenda {@link BasicRepository}
 */
public abstract class BasicServiceImpl<E extends BasicEntity, R extends BasicRepository<E>> implements BasicService<E> {

	@Autowired
	private R repository;

	protected R getRepository() {
		return repository;
	}

	@Override
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public E findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Page<E> findAll() {
		return repository.findAll(createPageable(1));
	}

	@Override
	public Page<E> findByPage(int page) {
		return repository.findAll(createPageable(page));
	}

	@Override
	public Page<E> findByPage(int page, int limit) {
		return repository.findAll(createPageable(page, limit));
	}

	/**
	 * Cria um {@link Pageable} a partir do número da página, com ordenação padrão
	 * por id.
	 * 
	 * @param page o número da página
	 * @return um {@link Pageable}
	 */
	protected Pageable createPageable(int page) {
		return createPageable(page, 20);
	}

	/**
	 * Cria um {@link Pageable} a partir do número da página e do limite de
	 * registros, com ordenação padrão por id.
	 * 
	 * @param page  o número da página
	 * @param limit o limite de registros
	 * @return um {@link Pageable}
	 */
	protected Pageable createPageable(int page, int limit) {
		if (page < 1) {
			page = 1;
		}

		// A paginação começa em 0, por isso deve reduzir 1
		page -= 1;

		// O limite máximo de resultados por página deve ser 20
		if (limit < 1 || limit > 20) {
			limit = 20;
		}

		return PageRequest.of(page, limit, Sort.by("id"));
	}

}
