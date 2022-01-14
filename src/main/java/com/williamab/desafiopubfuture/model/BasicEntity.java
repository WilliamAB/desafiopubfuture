package com.williamab.desafiopubfuture.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entidade de base para as entidades da aplicação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@MappedSuperclass
public abstract class BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
