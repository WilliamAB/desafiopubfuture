package com.williamab.desafiopubfuture.dto.model.despesa;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.williamab.desafiopubfuture.dto.model.BasicDTO;

/**
 * Implementação do DTO (Data Transfer Object) de despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class DespesaDTO extends BasicDTO<DespesaDTO> {

	@NotNull(message = "O valor deve ser informado!")
	@Positive(message = "O valor deve ser maior que zero!")
	private Double valor;

	@NotNull(message = "A data de pagamento deve ser informada!")
	private Date dataPagamento;

	private Date dataPagamentoEsperado;

	@NotNull(message = "O tipo de despesa deve ser informado!")
	private Long tipoDespesaId;

	@NotNull(message = "A conta deve ser informada!")
	private Long contaId;

	public DespesaDTO() {
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataPagamentoEsperado() {
		return dataPagamentoEsperado;
	}

	public void setDataPagamentoEsperado(Date dataPagamentoEsperado) {
		this.dataPagamentoEsperado = dataPagamentoEsperado;
	}

	public Long getTipoDespesaId() {
		return tipoDespesaId;
	}

	public void setTipoDespesaId(Long tipoDespesaId) {
		this.tipoDespesaId = tipoDespesaId;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

}
