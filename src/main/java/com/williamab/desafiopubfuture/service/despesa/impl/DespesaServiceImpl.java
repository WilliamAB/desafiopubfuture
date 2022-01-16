package com.williamab.desafiopubfuture.service.despesa.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.despesa.DespesaEntity;
import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;
import com.williamab.desafiopubfuture.repository.despesa.DespesaRepository;
import com.williamab.desafiopubfuture.service.despesa.DespesaService;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;

/**
 * Implementação de {@link DespesaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class DespesaServiceImpl extends BasicServiceImpl<DespesaEntity, DespesaRepository> implements DespesaService {

	@Override
	public Double getValorTotal() {
		return getRepository().sumValorDespesas();
	}

	@Override
	public Page<DespesaEntity> findByDataPagamento(Date dataInicial, Date dataFinal) {
		return findByDataPagamento(dataInicial, dataFinal, 1);
	}

	@Override
	public Page<DespesaEntity> findByDataPagamento(Date dataInicial, Date dataFinal, int page) {
		return findByDataPagamento(dataInicial, dataFinal, page, 20);
	}

	@Override
	public Page<DespesaEntity> findByDataPagamento(Date dataInicial, Date dataFinal, int page, int limit) {
		if (dataInicial == null) {
			throw new IllegalArgumentException("Data inicial deve ser informada!");
		}

		if (dataFinal == null) {
			throw new IllegalArgumentException("Data final deve ser informada!");
		}

		if (dataInicial.compareTo(dataFinal) > 0) {
			throw new IllegalArgumentException("Data final deve ser maior que a data inicial!");
		}

		return getRepository().findByDataPagamentoBetween(dataInicial, dataFinal, createPageable(page, limit));
	}

	@Override
	public Page<DespesaEntity> findByTipoDespesa(Long tipoDespesaId) {
		return findByTipoDespesa(tipoDespesaId, 1);
	}

	@Override
	public Page<DespesaEntity> findByTipoDespesa(Long tipoDespesaId, int page) {
		return findByTipoDespesa(tipoDespesaId, page, 20);
	}

	@Override
	public Page<DespesaEntity> findByTipoDespesa(Long tipoDespesaId, int page, int limit) {
		TipoDespesaEntity tipoDespesa = new TipoDespesaEntity();
		tipoDespesa.setId(tipoDespesaId);

		return getRepository().findByTipoDespesa(tipoDespesa, createPageable(page, limit));
	}

}
