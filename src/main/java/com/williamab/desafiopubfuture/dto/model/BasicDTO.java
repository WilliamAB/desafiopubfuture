package com.williamab.desafiopubfuture.dto.model;

import org.springframework.hateoas.RepresentationModel;

import com.williamab.desafiopubfuture.model.BasicEntity;

/**
 * DTO de base para os DTOs da aplicação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 * @param T o tipo da classe que está herdando {@link BasicDTO}
 * @param E a entidade que o DTO representa
 */
public abstract class BasicDTO<T extends BasicDTO<? extends T, E>, E extends BasicEntity> extends RepresentationModel<T> {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Converte o DTO em uma entidade.
	 * 
	 * @return uma entidade
	 */
	public final E convertToEntity() {
		E entity = convertFields();
		entity.setId(id);
		return entity;
	}

	/**
	 * Converte os campos do DTO em uma nova entidade.
	 * Não é necessário converter o campo id.
	 * 
	 * @return uma entidade
	 */
	protected abstract E convertFields();

}
