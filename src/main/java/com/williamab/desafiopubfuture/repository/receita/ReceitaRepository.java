package com.williamab.desafiopubfuture.repository.receita;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;
import com.williamab.desafiopubfuture.repository.BasicRepository;

/**
 * Repositório para {@link ReceitaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface ReceitaRepository extends BasicRepository<ReceitaEntity> {

	/**
	 * Soma o valor de todas as receitas.
	 * 
	 * @return o valor somado
	 */
	@Query("select sum(r.valor) from ReceitaEntity r")
	Double sumValorReceitas();

	/**
	 * Busca as receitas filtrando um período.
	 * 
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<ReceitaEntity> findByDataRecebimentoBetween(Date dataInicial, Date dataFinal, Pageable pageable);

	/**
	 * Busca as receitas filtrando o tipo de receita.
	 * 
	 * @param tipoReceita o tipo da receita
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<ReceitaEntity> findByTipoReceita(TipoReceitaEntity tipoReceita, Pageable pageable);

	/**
	 * Busca as receitas filtrando a data de pagamento maior que a data inicial.
	 * 
	 * @param dataInicial a data inicial
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<ReceitaEntity> findByDataRecebimentoGreaterThanEqual(Date dataInicial, Pageable pageable);

	/**
	 * Busca as receitas filtrando a data de pagamento menor que a data final.
	 * 
	 * @param dataFinal a data final
	 * @param pageable  as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<ReceitaEntity> findByDataRecebimentoLessThanEqual(Date dataFinal, Pageable pageable);

	/**
	 * Busca as receitas filtrando o tipo de receita e a data de pagamento maior que
	 * a data inicial.
	 * 
	 * @param tipoReceita o tipo de receita
	 * @param dataInicial a data inicial
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<ReceitaEntity> findByTipoReceitaAndDataRecebimentoGreaterThanEqual(TipoReceitaEntity tipoReceita,
			Date dataInicial, Pageable pageable);

	/**
	 * Busca as receitas filtrando o tipo de receita e a data de pagamento menor que
	 * a data final.
	 * 
	 * @param tipoReceita o tipo de receita
	 * @param dataFinal   a data final
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<ReceitaEntity> findByTipoReceitaAndDataRecebimentoLessThanEqual(TipoReceitaEntity tipoReceita, Date dataFinal,
			Pageable pageable);

	/**
	 * Busca as receitas filtrando o tipo de receita e o período da data de
	 * pagamento.
	 * 
	 * @param tipoReceita o tipo da receita
	 * @param dataInicial a data inicial do período
	 * @param dataFinal   a data final do período
	 * @param pageable    as configurações da página de resultado
	 * @return uma página com os resultados
	 */
	Page<ReceitaEntity> findByTipoReceitaAndDataRecebimentoBetween(TipoReceitaEntity tipoReceita, Date dataInicial,
			Date dataFinal, Pageable pageable);

}
