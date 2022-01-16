package com.williamab.desafiopubfuture.service.receita.impl;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;
import com.williamab.desafiopubfuture.repository.receita.ReceitaRepository;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;
import com.williamab.desafiopubfuture.service.receita.ReceitaService;
import com.williamab.desafiopubfuture.util.APIUtils;

/**
 * Implementação de {@link ReceitaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class ReceitaServiceImpl extends BasicServiceImpl<ReceitaEntity, ReceitaRepository> implements ReceitaService {

	@Override
	public Double getValorTotal() {
		Double valorTotal = getRepository().sumValorReceitas();
		return valorTotal == null ? 0.0 : valorTotal;
	}

	@Override
	public Page<ReceitaEntity> findByDataRecebimento(Date dataInicial, Date dataFinal) {
		return findByDataRecebimento(dataInicial, dataFinal, 1);
	}

	@Override
	public Page<ReceitaEntity> findByDataRecebimento(Date dataInicial, Date dataFinal, int page) {
		return findByDataRecebimento(dataInicial, dataFinal, page, APIUtils.PAGE_MAX_LIMIT);
	}

	@Override
	public Page<ReceitaEntity> findByDataRecebimento(Date dataInicial, Date dataFinal, int page, int limit) {
		if (dataInicial == null) {
			throw new IllegalArgumentException("Data inicial deve ser informada!");
		}

		if (dataFinal == null) {
			throw new IllegalArgumentException("Data final deve ser informada!");
		}

		if (dataInicial.compareTo(dataFinal) > 0) {
			throw new IllegalArgumentException("Data final deve ser maior que a data inicial!");
		}

		return getRepository().findByDataRecebimentoBetween(dataInicial, dataFinal, APIUtils.createPageable(page, limit));
	}

	@Override
	public Page<ReceitaEntity> findByTipoReceita(Long tipoReceitaId) {
		return findByTipoReceita(tipoReceitaId, 1);
	}

	@Override
	public Page<ReceitaEntity> findByTipoReceita(Long tipoReceitaId, int page) {
		return findByTipoReceita(tipoReceitaId, page, APIUtils.PAGE_MAX_LIMIT);
	}

	@Override
	public Page<ReceitaEntity> findByTipoReceita(Long tipoReceitaId, int page, int limit) {
		TipoReceitaEntity tipoReceita = new TipoReceitaEntity();
		tipoReceita.setId(tipoReceitaId);

		return getRepository().findByTipoReceita(tipoReceita, APIUtils.createPageable(page, limit));
	}

}
