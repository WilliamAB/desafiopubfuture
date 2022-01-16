package com.williamab.desafiopubfuture.controller.despesa;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williamab.desafiopubfuture.controller.BasicController;
import com.williamab.desafiopubfuture.converter.despesa.DespesaConverter;
import com.williamab.desafiopubfuture.dto.filter.despesa.DespesaFilterDTO;
import com.williamab.desafiopubfuture.dto.model.despesa.DespesaDTO;
import com.williamab.desafiopubfuture.dto.response.ResponseDTO;
import com.williamab.desafiopubfuture.model.despesa.DespesaEntity;
import com.williamab.desafiopubfuture.service.despesa.DespesaService;

/**
 * Controller com requisições para despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@RestController
@RequestMapping("/api/despesa")
public class DespesaController
		extends BasicController<DespesaEntity, DespesaDTO, DespesaService, DespesaConverter, DespesaFilterDTO> {

	@Override
	protected DespesaConverter getConverter() {
		return DespesaConverter.getInstance();
	}

	@Override
	protected Class<? extends BasicController<DespesaEntity, DespesaDTO, DespesaService, DespesaConverter, DespesaFilterDTO>> getControllerClass() {
		return DespesaController.class;
	}

	/**
	 * Busca o valor total das despesas.
	 * 
	 * @return a resposta com o valor total
	 */
	@GetMapping("/total")
	public ResponseEntity<ResponseDTO<String>> getTotalDespesas() {
		ResponseDTO<String> response = new ResponseDTO<>();

		Double valorTotal = getService().getValorTotal();

		response.setData(String.format("Valor total das despesas: R$ %.2f", valorTotal));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	protected Page<DespesaEntity> filterEntities(DespesaFilterDTO filter, int page, int limit) {

		Page<DespesaEntity> pageEntities = getService().findWithFilter(filter.getDataInicial(), filter.getDataFinal(),
				filter.getTipoDespesaId(), page, limit);

		return pageEntities;
	}

	@Override
	protected void createLinks(DespesaDTO dto) {
		super.createLinks(dto);
		createGenericMethodLink(WebMvcLinkBuilder.methodOn(DespesaController.class).getTotalDespesas(), dto);
		createGenericMethodLink(WebMvcLinkBuilder.methodOn(DespesaController.class).getWithFilter(null, 0, 0), dto);
	}

}
