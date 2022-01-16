package com.williamab.desafiopubfuture.dto.model;

import org.springframework.hateoas.RepresentationModel;

/**
 * DTO de base para os DTOs da aplicação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 * @param T o tipo da classe que está herdando {@link BasicDTO}
 * @param E a entidade que o DTO representa
 */
public abstract class BasicDTO<T extends BasicDTO<? extends T>> extends RepresentationModel<T> {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
