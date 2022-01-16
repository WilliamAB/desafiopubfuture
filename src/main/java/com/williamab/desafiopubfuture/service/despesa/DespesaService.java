package com.williamab.desafiopubfuture.service.despesa;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.williamab.desafiopubfuture.model.despesa.DespesaEntity;
import com.williamab.desafiopubfuture.service.BasicService;

/**
 * Serviço de manutenção dos dados de {@link DespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface DespesaService extends BasicService<DespesaEntity> {

	/**
	 * Retorna o resultado da soma das despesas.
	 * 
	 * @return o valor total
	 */
	Double getValorTotal();

	/**
	 * Busca as despesas filtrando a data de pagamento.
	 * 
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @return as despesas filtradas
	 */
	Page<DespesaEntity> findByDataPagamento(Date dataInicial, Date dataFinal);

	/**
	 * Busca as despesas filtrando a data de pagamento.
	 * 
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @param page        a página do resultado
	 * @return
	 */
	Page<DespesaEntity> findByDataPagamento(Date dataInicial, Date dataFinal, int page);

	/**
	 * Busca as despesas filtrando a data de pagamento.
	 * 
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @param page        a página do resultado
	 * @param limit       o limite de registros do resultado
	 * @return as despesas filtradas
	 */
	Page<DespesaEntity> findByDataPagamento(Date dataInicial, Date dataFinal, int page, int limit);

	/**
	 * Busca as despesas filtrando o tipo de despesa.
	 * 
	 * @param tipoDespesaId o id do tipo de despesa
	 * @return as despesas filtradas
	 */
	Page<DespesaEntity> findByTipoDespesa(Long tipoDespesaId);

	/**
	 * Busca as despesas filtrando o tipo de despesa.
	 * 
	 * @param tipoDespesaId o id do tipo de despesa
	 * @param page          a página do resultado
	 * @return as despesas filtradas
	 */
	Page<DespesaEntity> findByTipoDespesa(Long tipoDespesaId, int page);

	/**
	 * Busca as despesas filtrando o tipo de despesa.
	 * 
	 * @param tipoDespesaId o id do tipo de despesa
	 * @param page          a página do resultado
	 * @param limit         o limite de registros do resultado
	 * @return as despesas filtradas
	 */
	Page<DespesaEntity> findByTipoDespesa(Long tipoDespesaId, int page, int limit);

}
