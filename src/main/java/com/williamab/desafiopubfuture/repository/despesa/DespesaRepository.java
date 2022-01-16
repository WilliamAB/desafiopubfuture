package com.williamab.desafiopubfuture.repository.despesa;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.williamab.desafiopubfuture.model.despesa.DespesaEntity;
import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;
import com.williamab.desafiopubfuture.repository.BasicRepository;

/**
 * Repositório para {@link DespesaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface DespesaRepository extends BasicRepository<DespesaEntity> {

	/**
	 * Soma o valor de todas as despesas.
	 * 
	 * @return o valor somado
	 */
	@Query("select sum(d.valor) from DespesaEntity d")
	Double sumValorDespesas();

	/**
	 * Busca as despesas filtrando um período.
	 * 
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByDataPagamentoBetween(Date dataInicial, Date dataFinal, Pageable pageable);

	/**
	 * Busca as despesas filtrando o tipo de despesa.
	 * 
	 * @param tipoDespesa o tipo da despesa
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByTipoDespesa(TipoDespesaEntity tipoDespesa, Pageable pageable);

	/**
	 * Busca as despesas filtrando a data de pagamento maior que a data inicial.
	 * 
	 * @param dataInicial a data inicial
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByDataPagamentoGreaterThanEqual(Date dataInicial, Pageable pageable);

	/**
	 * Busca as despesas filtrando a data de pagamento menor que a data final.
	 * 
	 * @param dataFinal a data final
	 * @param pageable  as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByDataPagamentoLessThanEqual(Date dataFinal, Pageable pageable);

	/**
	 * Busca as despesas filtrando o tipo de despesa e a data de pagamento maior que
	 * a data inicial.
	 * 
	 * @param tipoDespesa o tipo de despesa
	 * @param dataInicial a data inicial
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByTipoDespesaAndDataPagamentoGreaterThanEqual(TipoDespesaEntity tipoDespesa,
			Date dataInicial, Pageable pageable);

	/**
	 * Busca as despesas filtrando o tipo de despesa e a data de pagamento menor que
	 * a data final.
	 * 
	 * @param tipoDespesa o tipo de despesa
	 * @param dataFinal   a data final
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByTipoDespesaAndDataPagamentoLessThanEqual(TipoDespesaEntity tipoDespesa, Date dataFinal,
			Pageable pageable);

	/**
	 * Busca as despesas filtrando o tipo de despesa e o período da data de
	 * pagamento.
	 * 
	 * @param tipoDespesa o tipo da despesa
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<DespesaEntity> findByTipoDespesaAndDataPagamentoBetween(TipoDespesaEntity tipoDespesa, Date dataInicial,
			Date dataFinal, Pageable pageable);

}
