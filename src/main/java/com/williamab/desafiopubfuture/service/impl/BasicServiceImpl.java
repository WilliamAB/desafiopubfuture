package com.williamab.desafiopubfuture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.williamab.desafiopubfuture.model.BasicEntity;
import com.williamab.desafiopubfuture.repository.BasicRepository;
import com.williamab.desafiopubfuture.service.BasicService;
import com.williamab.desafiopubfuture.util.APIUtils;

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
		return repository.findAll(APIUtils.createPageable(1));
	}

	@Override
	public Page<E> findByPage(int page) {
		return repository.findAll(APIUtils.createPageable(page));
	}

	@Override
	public Page<E> findByPage(int page, int limit) {
		return repository.findAll(APIUtils.createPageable(page, limit));
	}

}
