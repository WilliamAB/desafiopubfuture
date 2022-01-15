package com.williamab.desafiopubfuture.model.despesa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.williamab.desafiopubfuture.model.BasicEntity;

/**
 * Representa um tipo de despesa vinculado a {@link DespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "tipo_despesa")
public class TipoDespesaEntity extends BasicEntity {

	@Column(name = "descricao", nullable = false)
	private String descricao;

	public TipoDespesaEntity() {
	}

	public TipoDespesaEntity(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
