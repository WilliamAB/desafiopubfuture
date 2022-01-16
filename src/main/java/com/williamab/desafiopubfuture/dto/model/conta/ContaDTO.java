package com.williamab.desafiopubfuture.dto.model.conta;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.williamab.desafiopubfuture.dto.model.BasicDTO;

/**
 * Implementação do DTO (Data Transfer Object) de conta.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class ContaDTO extends BasicDTO<ContaDTO> {

	@NotNull(message = "O saldo deve ser informado!")
	private Double saldo;

	@NotNull
	@Pattern(regexp = "^(CARTEIRA|CONTA_CORRENTE|POUPANCA)$",
		message = "O tipo da conta deve ser um dos seguintes: CARTEIRA, CONTA_CORRENTE ou POUPANCA.")
	private String tipoConta;

	private String instituicaoFinanceira;

	public ContaDTO() {
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getInstituicaoFinanceira() {
		return instituicaoFinanceira;
	}

	public void setInstituicaoFinanceira(String instituicaoFinanceira) {
		this.instituicaoFinanceira = instituicaoFinanceira;
	}

}
