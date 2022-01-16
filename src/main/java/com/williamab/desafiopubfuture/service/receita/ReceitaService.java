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
	 * Busca as receitas utilizando filtros por data inicial, data final e tipo de
	 * receita.
	 * 
	 * @param dataInicial   filtro por data inicial
	 * @param dataFinal     filtro por data final
	 * @param tipoDespesaId filtro por tipo de receita
	 * @param page          a página do resultado
	 * @param limit         o limite de registros do resultado
	 * @return as receitas filtradas
	 */
	Page<ReceitaEntity> findWithFilter(Date dataInicial, Date dataFinal, Long tipoReceitaId, int page, int limit);

}
