package com.williamab.desafiopubfuture.model.conta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.williamab.desafiopubfuture.model.BasicEntity;

/**
 * Representa uma conta de movimentação financeira.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "conta")
public class ContaEntity extends BasicEntity {

	@Column(name = "saldo", nullable = false, precision = 10, scale = 2)
	private Double saldo;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_conta", nullable = false)
	private TipoConta tipoConta;

	@Column(name = "instituicao_financeira", nullable = true)
	private String instituicaoFinanceira;

	public ContaEntity() {
	}

	public ContaEntity(TipoConta tipoConta, String instituicaoFinanceira) {
		this(0.0, tipoConta, instituicaoFinanceira);
	}

	public ContaEntity(Double saldo, TipoConta tipoConta, String instituicaoFinanceira) {
		this.saldo = saldo;
		this.tipoConta = tipoConta;
		this.instituicaoFinanceira = instituicaoFinanceira;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}

	public void setInstituicaoFinanceira(String instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}

}
