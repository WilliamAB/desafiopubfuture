package com.williamab.desafiopubfuture.service.despesa.impl;

import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.despesa.DespesaEntity;
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

}
