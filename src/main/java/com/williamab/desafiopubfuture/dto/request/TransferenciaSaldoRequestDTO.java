package com.williamab.desafiopubfuture.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Estrutura que representa os dados vindos de uma request na requisição de
 * transferência de saldo entre contas.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TransferenciaSaldoRequestDTO {

	@NotNull(message = "Conta de origem deve ser informada!")
	private Long contaOrigemId;

	@NotNull(message = "Conta de destino deve ser informada!")
	private Long contaDestinoId;

	@NotNull(message = "Valor da transferência deve ser informado!")
	@Positive(message = "Valor da transferência deve ser maior que zero!")
	private Double valor;

	public TransferenciaSaldoRequestDTO() {
	}

	public Long getContaOrigemId() {
		return contaOrigemId;
	}

	public void setContaOrigemId(Long contaOrigemId) {
		this.contaOrigemId = contaOrigemId;
	}

	public Long getContaDestinoId() {
		return contaDestinoId;
	}

	public void setContaDestinoId(Long contaDestinoId) {
		this.contaDestinoId = contaDestinoId;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
