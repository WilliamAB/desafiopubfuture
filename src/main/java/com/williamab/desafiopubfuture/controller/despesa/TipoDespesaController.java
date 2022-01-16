package com.williamab.desafiopubfuture.controller.despesa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williamab.desafiopubfuture.controller.BasicController;
import com.williamab.desafiopubfuture.converter.despesa.TipoDespesaConverter;
import com.williamab.desafiopubfuture.dto.filter.NoFilterDTO;
import com.williamab.desafiopubfuture.dto.model.despesa.TipoDespesaDTO;
import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;
import com.williamab.desafiopubfuture.service.despesa.TipoDespesaService;

/**
 * Controller com requisições para tipo de despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@RestController
@RequestMapping("/api/tipo-despesa")
public class TipoDespesaController
		extends BasicController<TipoDespesaEntity, TipoDespesaDTO, TipoDespesaService, TipoDespesaConverter, NoFilterDTO> {

	@Override
	protected TipoDespesaConverter getConverter() {
		return TipoDespesaConverter.getInstance();
	}

	@Override
	protected Class<? extends BasicController<TipoDespesaEntity, TipoDespesaDTO, TipoDespesaService, TipoDespesaConverter, NoFilterDTO>> getControllerClass() {
		return TipoDespesaController.class;
	}

}
