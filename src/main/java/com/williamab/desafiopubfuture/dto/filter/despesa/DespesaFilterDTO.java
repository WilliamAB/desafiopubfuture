package com.williamab.desafiopubfuture.dto.filter.despesa;

import java.util.Date;

import com.williamab.desafiopubfuture.dto.filter.FilterDTO;

/**
 * Representa os filtros disponíveis para despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class DespesaFilterDTO extends FilterDTO {

	private Date dataInicial;

	private Date dataFinal;

	private Long tipoDespesaId;

	public DespesaFilterDTO() {
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

	public Long getTipoDespesaId() {
		return tipoDespesaId;
	}

	public void setTipoDespesaId(Long tipoDespesaId) {
		this.tipoDespesaId = tipoDespesaId;
	}

}
