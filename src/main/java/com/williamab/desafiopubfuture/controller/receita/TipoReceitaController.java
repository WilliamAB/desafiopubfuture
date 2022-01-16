package com.williamab.desafiopubfuture.controller.receita;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williamab.desafiopubfuture.controller.BasicController;
import com.williamab.desafiopubfuture.converter.receita.TipoReceitaConverter;
import com.williamab.desafiopubfuture.dto.filter.NoFilterDTO;
import com.williamab.desafiopubfuture.dto.model.receita.TipoReceitaDTO;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;
import com.williamab.desafiopubfuture.service.receita.TipoReceitaService;

/**
 * Controller com requisições para tipo de receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@RestController
@RequestMapping("/api/tipo-receita")
public class TipoReceitaController
		extends BasicController<TipoReceitaEntity, TipoReceitaDTO, TipoReceitaService, TipoReceitaConverter, NoFilterDTO> {

	@Override
	protected TipoReceitaConverter getConverter() {
		return TipoReceitaConverter.getInstance();
	}

	@Override
	protected Class<? extends BasicController<TipoReceitaEntity, TipoReceitaDTO, TipoReceitaService, TipoReceitaConverter, NoFilterDTO>> getControllerClass() {
		return TipoReceitaController.class;
	}

}
