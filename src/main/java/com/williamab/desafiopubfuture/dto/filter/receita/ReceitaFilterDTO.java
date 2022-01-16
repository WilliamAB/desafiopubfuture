package com.williamab.desafiopubfuture.dto.filter.receita;

import java.util.Date;

import com.williamab.desafiopubfuture.dto.filter.FilterDTO;

/**
 * Representa os filtros disponíveis para receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class ReceitaFilterDTO extends FilterDTO {

	private Date dataInicial;
	private Date dataFinal;
	private Long tipoReceitaId;

	public ReceitaFilterDTO() {
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Long getTipoReceitaId() {
		return tipoReceitaId;
	}

	public void setTipoReceitaId(Long tipoReceitaId) {
		this.tipoReceitaId = tipoReceitaId;
	}

}
