package com.williamab.desafiopubfuture.model.receita;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.williamab.desafiopubfuture.model.BasicEntity;

/**
 * Representa um tipo de receita vinculado a {@link ReceitaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "tipo_receita")
public class TipoReceitaEntity extends BasicEntity {

	@Column(name = "descricao", nullable = false)
	private String descricao;

	public TipoReceitaEntity() {
	}

	public TipoReceitaEntity(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
