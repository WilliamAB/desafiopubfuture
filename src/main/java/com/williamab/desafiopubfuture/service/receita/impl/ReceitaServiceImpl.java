package com.williamab.desafiopubfuture.service.receita.impl;

import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.repository.receita.ReceitaRepository;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;
import com.williamab.desafiopubfuture.service.receita.ReceitaService;

/**
 * Implementação de {@link ReceitaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class ReceitaServiceImpl extends BasicServiceImpl<ReceitaEntity, ReceitaRepository> implements ReceitaService {

}
