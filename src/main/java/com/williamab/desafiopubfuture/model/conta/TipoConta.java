package com.williamab.desafiopubfuture.model.conta;

/**
 * Representa os tipos de conta para a {@link ContaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public enum TipoConta {

	CARTEIRA("Carteira"),

	CONTA_CORRENTE("Conta Corrente"),

	POUPANCA("Poupança");

	private String descricao;

	private TipoConta(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
