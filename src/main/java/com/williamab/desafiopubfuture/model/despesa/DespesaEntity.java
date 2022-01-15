package com.williamab.desafiopubfuture.model.despesa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.williamab.desafiopubfuture.model.BasicEntity;
import com.williamab.desafiopubfuture.model.conta.ContaEntity;

/**
 * Representa uma despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "despesa")
public class DespesaEntity extends BasicEntity {

	@Column(name = "valor", nullable = false, precision = 10, scale = 2)
	private Double valor;

	@Column(name = "data_pagamento", nullable = false)
	private Date dataPagamento;

	@Column(name = "data_pagamento_esperado", nullable = true)
	private Date dataPagamentoEsperado;

	@JoinColumn(name = "tipo_despesa_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private TipoDespesaEntity tipoDespesa;

	@JoinColumn(name = "conta_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private ContaEntity conta;

	public DespesaEntity() {
	}

	public DespesaEntity(Double valor, Date dataPagamento, TipoDespesaEntity tipoDespesa, ContaEntity conta) {
		this(valor, dataPagamento, null, tipoDespesa, conta);
	}

	public DespesaEntity(Double valor, Date dataPagamento, Date dataPagamentoEsperado, TipoDespesaEntity tipoDespesa,
			ContaEntity conta) {
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.dataPagamentoEsperado = dataPagamentoEsperado;
		this.tipoDespesa = tipoDespesa;
		this.conta = conta;
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

	public TipoDespesaEntity getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesaEntity tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public ContaEntity getConta() {
		return conta;
	}

	public void setConta(ContaEntity conta) {
		this.conta = conta;
	}

}
