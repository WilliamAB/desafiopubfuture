package com.williamab.desafiopubfuture.dto.model.receita;

import javax.validation.constraints.NotBlank;

import com.williamab.desafiopubfuture.dto.model.BasicDTO;

/**
 * Implementação do DTO (Data Transfer Object) de tipo de receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TipoReceitaDTO extends BasicDTO<TipoReceitaDTO> {

	@NotBlank(message = "A descrição deve ser informada!")
	private String descricao;

	public TipoReceitaDTO() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
