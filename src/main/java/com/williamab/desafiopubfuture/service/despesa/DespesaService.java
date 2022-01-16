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
	 * Busca as despesas utilizando filtros por data inicial, data final e tipo de
	 * despesa.
	 * 
	 * @param dataInicial   filtro por data inicial
	 * @param dataFinal     filtro por data final
	 * @param tipoDespesaId filtro por tipo de despesa
	 * @param page          a página do resultado
	 * @param limit         o limite de registros do resultado
	 * @return as despesas filtradas
	 */
	Page<DespesaEntity> findWithFilter(Date dataInicial, Date dataFinal, Long tipoDespesaId, int page, int limit);

}
