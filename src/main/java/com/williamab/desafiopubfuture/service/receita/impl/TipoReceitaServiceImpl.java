package com.williamab.desafiopubfuture.service.receita.impl;

import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;
import com.williamab.desafiopubfuture.repository.receita.TipoReceitaRepository;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;
import com.williamab.desafiopubfuture.service.receita.TipoReceitaService;

/**
 * Implementação de {@link TipoReceitaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class TipoReceitaServiceImpl extends BasicServiceImpl<TipoReceitaEntity, TipoReceitaRepository>
		implements TipoReceitaService {

}
