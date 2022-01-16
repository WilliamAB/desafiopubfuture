package com.williamab.desafiopubfuture.dto.model.receita;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.williamab.desafiopubfuture.dto.model.BasicDTO;

/**
 * Implementação do DTO (Data Transfer Object) de receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class ReceitaDTO extends BasicDTO<ReceitaDTO> {

	@NotNull(message = "O valor deve ser informado!")
	@Positive(message = "O valor deve ser maior que zero!")
	private Double valor;

	@NotNull(message = "A data de pagamento deve ser informada!")
	private Date dataRecebimento;

	private Date dataRecebimentoEsperado;

	@NotBlank(message = "A descrição deve ser informada!")
	private String descricao;

	@NotNull(message = "A conta deve ser informada!")
	private Long contaId;

	@NotNull(message = "O tipo de receita deve ser informado!")
	private Long tipoReceitaId;

	public ReceitaDTO() {
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Date getDataRecebimentoEsperado() {
		return dataRecebimentoEsperado;
	}

	public void setDataRecebimentoEsperado(Date dataRecebimentoEsperado) {
		this.dataRecebimentoEsperado = dataRecebimentoEsperado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public Long getTipoReceitaId() {
		return tipoReceitaId;
	}

	public void setTipoReceitaId(Long tipoReceitaId) {
		this.tipoReceitaId = tipoReceitaId;
	}

}
