package com.williamab.desafiopubfuture.controller.receita;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williamab.desafiopubfuture.controller.BasicController;
import com.williamab.desafiopubfuture.converter.receita.ReceitaConverter;
import com.williamab.desafiopubfuture.dto.filter.receita.ReceitaFilterDTO;
import com.williamab.desafiopubfuture.dto.model.receita.ReceitaDTO;
import com.williamab.desafiopubfuture.dto.response.ResponseDTO;
import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.service.receita.ReceitaService;

/**
 * Controller com requisições para receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@RestController
@RequestMapping("/api/receita")
public class ReceitaController
		extends BasicController<ReceitaEntity, ReceitaDTO, ReceitaService, ReceitaConverter, ReceitaFilterDTO> {

	@Override
	protected ReceitaConverter getConverter() {
		return ReceitaConverter.getInstance();
	}

	@Override
	protected Class<? extends BasicController<ReceitaEntity, ReceitaDTO, ReceitaService, ReceitaConverter, ReceitaFilterDTO>> getControllerClass() {
		return ReceitaController.class;
	}

	/**
	 * Busca o valor total das receitas.
	 * 
	 * @return a resposta com o valor total
	 */
	@GetMapping("/total")
	public ResponseEntity<ResponseDTO<String>> getTotalReceitas() {
		ResponseDTO<String> response = new ResponseDTO<>();

		Double valorTotal = getService().getValorTotal();

		response.setData(String.format("Valor total das receitas: R$ %.2f", valorTotal));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	protected Page<ReceitaEntity> filterEntities(ReceitaFilterDTO filter, int page, int limit) {

		Page<ReceitaEntity> pageEntities = getService().findWithFilter(filter.getDataInicial(), filter.getDataFinal(),
				filter.getTipoReceitaId(), page, limit);

		return pageEntities;
	}

	@Override
	protected void createLinks(ReceitaDTO dto) {
		super.createLinks(dto);
		createGenericMethodLink(WebMvcLinkBuilder.methodOn(ReceitaController.class).getTotalReceitas(), dto);
		createGenericMethodLink(WebMvcLinkBuilder.methodOn(ReceitaController.class).getWithFilter(null, 0, 0), dto);
	}

}
