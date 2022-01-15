package com.williamab.desafiopubfuture.service.conta.impl;

import org.springframework.stereotype.Service;

import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.repository.conta.ContaRepository;
import com.williamab.desafiopubfuture.service.conta.ContaService;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;

/**
 * Implementação de {@link ContaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class ContaServiceImpl extends BasicServiceImpl<ContaEntity, ContaRepository> implements ContaService {

}
