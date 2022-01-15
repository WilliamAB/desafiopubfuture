package com.williamab.desafiopubfuture.dto.model.despesa;

import javax.validation.constraints.NotBlank;

import com.williamab.desafiopubfuture.dto.model.BasicDTO;
import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;

/**
 * Implementação do DTO (Data Transfer Object) de tipo de despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TipoDespesaDTO extends BasicDTO<TipoDespesaDTO, TipoDespesaEntity> {

	@NotBlank(message = "A descrição deve ser informada!")
	private String descricao;

	public TipoDespesaDTO() {
	}

	public TipoDespesaDTO(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	protected TipoDespesaEntity convertFields() {
		TipoDespesaEntity entity = new TipoDespesaEntity();
		entity.setDescricao(descricao);
		return entity;
	}

}
