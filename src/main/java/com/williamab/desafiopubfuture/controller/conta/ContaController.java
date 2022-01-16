package com.williamab.desafiopubfuture.controller.conta;

import javax.validation.Valid;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williamab.desafiopubfuture.controller.BasicController;
import com.williamab.desafiopubfuture.converter.conta.ContaConverter;
import com.williamab.desafiopubfuture.dto.filter.NoFilterDTO;
import com.williamab.desafiopubfuture.dto.model.conta.ContaDTO;
import com.williamab.desafiopubfuture.dto.request.TransferenciaSaldoRequestDTO;
import com.williamab.desafiopubfuture.dto.response.ResponseDTO;
import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.service.conta.ContaService;

/**
 * Controller com requisições para conta.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@RestController
@RequestMapping("/api/conta")
public class ContaController extends BasicController<ContaEntity, ContaDTO, ContaService, ContaConverter, NoFilterDTO> {

	@Override
	protected ContaConverter getConverter() {
		return ContaConverter.getInstance();
	}

	@Override
	protected Class<? extends BasicController<ContaEntity, ContaDTO, ContaService, ContaConverter, NoFilterDTO>> getControllerClass() {
		return ContaController.class;
	}

	/**
	 * Transfere saldo entre contas.
	 * 
	 * @param transferencia as informações da transferência
	 * @param result        resultado da requisição, para tratamento de erros
	 * @return a resposta com sucesso se a transferência ocorreu sem erros ou a
	 *         resposta com os erros que impediram a execução da transferênaica
	 */
	@PostMapping("/transferir-saldo")
	public ResponseEntity<ResponseDTO<String>> transferirSaldo(@Valid TransferenciaSaldoRequestDTO transferencia,
			BindingResult result) {

		ResponseDTO<String> response = new ResponseDTO<>();

		if (result.hasErrors()) {
			response.setData("Transferência não executada!");
			result.getAllErrors().forEach(e -> response.addError(e.getDefaultMessage()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			getService().transferirSaldo(transferencia.getContaOrigemId(), transferencia.getContaDestinoId(),
					transferencia.getValor());
		} catch (Exception e) {
			response.setData("Transferência não executada!");
			response.addError(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		response.setData("Transferência concluída com sucesso!");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Busca o saldo total das contas.
	 * 
	 * @return a resposta com o saldo total
	 */
	@GetMapping("/saldo-total")
	public ResponseEntity<ResponseDTO<String>> getSaldoTotal() {

		ResponseDTO<String> response = new ResponseDTO<>();

		Double saldoTotal = getService().getSaldoTotal();

		response.setData(String.format("Saldo total das contas: R$ %.2f", saldoTotal));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	protected void createLinks(ContaDTO dto) {
		super.createLinks(dto);
		createGenericMethodLink(WebMvcLinkBuilder.methodOn(ContaController.class).transferirSaldo(null, null), dto);
		createGenericMethodLink(WebMvcLinkBuilder.methodOn(ContaController.class).getSaldoTotal(), dto);
	}

}
