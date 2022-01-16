package com.williamab.desafiopubfuture.service.receita;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.service.BasicService;

/**
 * Serviço de manutenção dos dados de {@link ReceitaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface ReceitaService extends BasicService<ReceitaEntity> {

	/**
	 * Retorna o resultado da soma das receitas.
	 * 
	 * @return o valor total
	 */
	Double getValorTotal();

	/**
	 * Busca as receitas filtrando a data de pagamento.
	 * 
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @return as receitas filtradas
	 */
	Page<ReceitaEntity> findByDataRecebimento(Date dataInicial, Date dataFinal);

	/**
	 * Busca as receitas filtrando a data de pagamento.
	 * 
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @param page        a página do resultado
	 * @return as receitas filtradas
	 */
	Page<ReceitaEntity> findByDataRecebimento(Date dataInicial, Date dataFinal, int page);

	/**
	 * Busca as receitas filtrando a data de pagamento.
	 * 
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @param page        a página do resultado
	 * @param limit       o limite de registros do resultado
	 * @return as receitas filtradas
	 */
	Page<ReceitaEntity> findByDataRecebimento(Date dataInicial, Date dataFinal, int page, int limit);

	/**
	 * Busca as receitas filtrando o tipo de receita.
	 * 
	 * @param tipoReceitaId o id do tipo de receita
	 * @return as receitas filtradas
	 */
	Page<ReceitaEntity> findByTipoReceita(Long tipoReceitaId);

	/**
	 * Busca as receitas filtrando o tipo de receita.
	 * 
	 * @param tipoReceitaId o id do tipo de receita
	 * @param page          a página do resultado
	 * @return as receitas filtradas
	 */
	Page<ReceitaEntity> findByTipoReceita(Long tipoReceitaId, int page);

	/**
	 * Busca as receitas filtrando o tipo de receita.
	 * 
	 * @param tipoReceitaId o id do tipo de receita
	 * @param page          a página do resultado
	 * @param limit         o limite de registros do resultado
	 * @return as receitas filtradas
	 */
	Page<ReceitaEntity> findByTipoReceita(Long tipoReceitaId, int page, int limit);

}
