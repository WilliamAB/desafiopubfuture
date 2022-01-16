package com.williamab.desafiopubfuture.dto.model.despesa;

import javax.validation.constraints.NotBlank;

import com.williamab.desafiopubfuture.dto.model.BasicDTO;

/**
 * Implementação do DTO (Data Transfer Object) de tipo de despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TipoDespesaDTO extends BasicDTO<TipoDespesaDTO> {

	@NotBlank(message = "A descrição deve ser informada!")
	private String descricao;

	public TipoDespesaDTO() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
