package com.williamab.desafiopubfuture.model.receita;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.williamab.desafiopubfuture.model.BasicEntity;
import com.williamab.desafiopubfuture.model.conta.ContaEntity;

/**
 * Representa uma receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Entity
@Table(name = "receita")
public class ReceitaEntity extends BasicEntity {

	@Column(name = "valor", nullable = false, precision = 10, scale = 2)
	private Double valor;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_recebimento", nullable = false)
	private Date dataRecebimento;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_recebimento_esperado", nullable = true)
	private Date dataRecebimentoEsperado;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@JoinColumn(name = "conta_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private ContaEntity conta;

	@JoinColumn(name = "tipo_receita_id", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private TipoReceitaEntity tipoReceita;

	public ReceitaEntity() {
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

	public ContaEntity getConta() {
		return conta;
	}

	public void setConta(ContaEntity conta) {
		this.conta = conta;
	}

	public TipoReceitaEntity getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceitaEntity tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

}
