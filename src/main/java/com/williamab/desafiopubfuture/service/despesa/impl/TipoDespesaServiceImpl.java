package com.williamab.desafiopubfuture.service.despesa.impl;

import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;
import com.williamab.desafiopubfuture.repository.despesa.TipoDespesaRepository;
import com.williamab.desafiopubfuture.service.despesa.TipoDespesaService;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;

/**
 * Implementação de {@link TipoDespesaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class TipoDespesaServiceImpl extends BasicServiceImpl<TipoDespesaEntity, TipoDespesaRepository>
		implements TipoDespesaService {

}
