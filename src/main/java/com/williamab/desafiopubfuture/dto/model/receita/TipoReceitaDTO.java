package com.williamab.desafiopubfuture.dto.model.receita;

import javax.validation.constraints.NotBlank;

import com.williamab.desafiopubfuture.dto.model.BasicDTO;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;

/**
 * Implementação do DTO (Data Transfer Object) de tipo de receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TipoReceitaDTO extends BasicDTO<TipoReceitaDTO, TipoReceitaEntity> {

	@NotBlank(message = "A descrição deve ser informada!")
	private String descricao;

	public TipoReceitaDTO() {
	}

	public TipoReceitaDTO(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	protected TipoReceitaEntity convertFields() {
		TipoReceitaEntity entity = new TipoReceitaEntity();
		entity.setDescricao(descricao);
		return entity;
	}

}
