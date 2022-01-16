package com.williamab.desafiopubfuture.service.despesa.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.despesa.DespesaEntity;
import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;
import com.williamab.desafiopubfuture.repository.despesa.DespesaRepository;
import com.williamab.desafiopubfuture.service.despesa.DespesaService;
import com.williamab.desafiopubfuture.service.despesa.TipoDespesaService;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;
import com.williamab.desafiopubfuture.util.APIUtils;

/**
 * Implementação de {@link DespesaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class DespesaServiceImpl extends BasicServiceImpl<DespesaEntity, DespesaRepository> implements DespesaService {

	@Autowired
	private TipoDespesaService tipoDespesaService;

	@Override
	public Double getValorTotal() {
		Double valorTotal = getRepository().sumValorDespesas();
		return valorTotal == null ? 0.0 : valorTotal;
	}

	@Override
	public Page<DespesaEntity> findWithFilter(Date dataInicial, Date dataFinal, Long tipoDespesaId, int page,
			int limit) {

		boolean hasDataInicial = dataInicial != null;
		boolean hasDataFinal = dataFinal != null;
		boolean hasTipoDespesaId = tipoDespesaId != null;

		TipoDespesaEntity tipoDespesa = null;

		if (hasTipoDespesaId) {
			tipoDespesa = tipoDespesaService.findById(tipoDespesaId);
		}

		Pageable pageable = APIUtils.createPageable(page, limit);

		// Filtro por data inicial, data final e tipo de despesa
		if (hasDataInicial && hasDataFinal && hasTipoDespesaId) {
			return getRepository().findByTipoDespesaAndDataPagamentoBetween(tipoDespesa, dataInicial, dataFinal,
					pageable);
		}

		// Filtro por data inicial e data final
		if (hasDataInicial && hasDataFinal && !hasTipoDespesaId) {
			return getRepository().findByDataPagamentoBetween(dataInicial, dataFinal, pageable);
		}

		// Filtro por data inicial e tipo de despesa
		if (hasDataInicial && !hasDataFinal && hasTipoDespesaId) {
			return getRepository().findByTipoDespesaAndDataPagamentoGreaterThanEqual(tipoDespesa, dataInicial,
					pageable);
		}

		// Filtro por data final e tipo de despesa
		if (!hasDataInicial && hasDataFinal && hasTipoDespesaId) {
			return getRepository().findByTipoDespesaAndDataPagamentoLessThanEqual(tipoDespesa, dataFinal, pageable);
		}

		// Filtro por data inicial
		if (hasDataInicial && !hasDataFinal && !hasTipoDespesaId) {
			return getRepository().findByDataPagamentoGreaterThanEqual(dataInicial, pageable);
		}

		// Filtro por data final
		if (!hasDataInicial && hasDataFinal && !hasTipoDespesaId) {
			return getRepository().findByDataPagamentoLessThanEqual(dataFinal, pageable);
		}

		// Filtro por tipo de despesa
		if (!hasDataInicial && !hasDataFinal && hasTipoDespesaId) {
			return getRepository().findByTipoDespesa(tipoDespesa, pageable);
		}

		return getRepository().findAll(pageable);
	}

}
