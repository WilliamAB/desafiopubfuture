package com.williamab.desafiopubfuture.service.receita.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;
import com.williamab.desafiopubfuture.repository.receita.ReceitaRepository;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;
import com.williamab.desafiopubfuture.service.receita.ReceitaService;
import com.williamab.desafiopubfuture.service.receita.TipoReceitaService;
import com.williamab.desafiopubfuture.util.APIUtils;

/**
 * Implementação de {@link ReceitaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class ReceitaServiceImpl extends BasicServiceImpl<ReceitaEntity, ReceitaRepository> implements ReceitaService {

	@Autowired
	private TipoReceitaService tipoReceitaService;

	@Override
	public Double getValorTotal() {
		Double valorTotal = getRepository().sumValorReceitas();
		return valorTotal == null ? 0.0 : valorTotal;
	}

	@Override
	public Page<ReceitaEntity> findWithFilter(Date dataInicial, Date dataFinal, Long tipoReceitaId, int page,
			int limit) {

		boolean hasDataInicial = dataInicial != null;
		boolean hasDataFinal = dataFinal != null;
		boolean hasTipoReceitaId = tipoReceitaId != null;

		TipoReceitaEntity tipoReceita = null;

		if (hasTipoReceitaId) {
			tipoReceita = tipoReceitaService.findById(tipoReceitaId);
		}

		Pageable pageable = APIUtils.createPageable(page, limit);

		// Filtro por data inicial, data final e tipo de receita
		if (hasDataInicial && hasDataFinal && hasTipoReceitaId) {
			return getRepository().findByTipoReceitaAndDataRecebimentoBetween(tipoReceita, dataInicial, dataFinal,
					pageable);
		}

		// Filtro por data inicial e data final
		if (hasDataInicial && hasDataFinal && !hasTipoReceitaId) {
			return getRepository().findByDataRecebimentoBetween(dataInicial, dataFinal, pageable);
		}

		// Filtro por data inicial e tipo de receita
		if (hasDataInicial && !hasDataFinal && hasTipoReceitaId) {
			return getRepository().findByTipoReceitaAndDataRecebimentoGreaterThanEqual(tipoReceita, dataInicial,
					pageable);
		}

		// Filtro por data final e tipo de receita
		if (!hasDataInicial && hasDataFinal && hasTipoReceitaId) {
			return getRepository().findByTipoReceitaAndDataRecebimentoLessThanEqual(tipoReceita, dataFinal, pageable);
		}

		// Filtro por data inicial
		if (hasDataInicial && !hasDataFinal && !hasTipoReceitaId) {
			return getRepository().findByDataRecebimentoGreaterThanEqual(dataInicial, pageable);
		}

		// Filtro por data final
		if (!hasDataInicial && hasDataFinal && !hasTipoReceitaId) {
			return getRepository().findByDataRecebimentoLessThanEqual(dataFinal, pageable);
		}

		// Filtro por tipo de receita
		if (!hasDataInicial && !hasDataFinal && hasTipoReceitaId) {
			return getRepository().findByTipoReceita(tipoReceita, pageable);
		}

		return getRepository().findAll(pageable);
	}

}
