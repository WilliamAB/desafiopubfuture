package com.williamab.desafiopubfuture.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.williamab.desafiopubfuture.converter.BasicConverter;
import com.williamab.desafiopubfuture.dto.filter.FilterDTO;
import com.williamab.desafiopubfuture.dto.model.BasicDTO;
import com.williamab.desafiopubfuture.dto.response.ResponseDTO;
import com.williamab.desafiopubfuture.dto.response.ResponsePageableDTO;
import com.williamab.desafiopubfuture.model.BasicEntity;
import com.williamab.desafiopubfuture.service.BasicService;

/**
 * Controller base para os controllers da aplicação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 * @param <E> uma entidade que estenda {@link BasicEntity}
 * @param <D> um DTO (Data Transfer Object) que estenda {@link BasicDTO}
 * @param <S> um serviço que estenda {@link BasicService}
 * @param <C> um conversor que estenda {@link BasicConverter}
 * @param <F> um filtro que estenda {@link FilterDTO}
 */
public abstract class BasicController<E extends BasicEntity, D extends BasicDTO<D>, S extends BasicService<E>, C extends BasicConverter<E, D>, F extends FilterDTO> {

	@Autowired
	private S service;

	/**
	 * Retorna uma instância do conversor de entidade/DTO.
	 * 
	 * @return um conversor que estende {@link BasicConverter}
	 */
	protected abstract C getConverter();

	/**
	 * Retorna o serviço para utilização das classes herdeiras.
	 * 
	 * @return um serviço que estende {@link BasicService}
	 */
	protected final S getService() {
		return service;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
	}

	/**
	 * Retorna os registros conforme paginação. Se nenhuma página for informada
	 * busca a primeira.
	 * 
	 * @param page  o número da página a ser buscada
	 * @param limit o limite de registros a ser buscado
	 * @return o resultado da busca
	 */
	@GetMapping
	public ResponseEntity<ResponseDTO<List<D>>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit) {

		ResponsePageableDTO<List<D>> response = new ResponsePageableDTO<>();

		// Busca as entidades utilizando os filtros
		Page<E> pageEntities = service.findByPage(page, limit);

		List<D> dtos = new ArrayList<>();

		// Transfere as entidades para a lista de DTOs, por meio da conversão
		pageEntities.forEach(e -> dtos.add(getConverter().convertEntityToDTO(e)));

		// Cria os links para os DTOs
		dtos.forEach(d -> createSelfLink(d));

		// Atualiza a resposta com os dados
		response.setData(dtos);
		response.addPageInfo(pageEntities);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/filter")
	public ResponseEntity<ResponseDTO<List<D>>> getWithFilter(@Valid F filterDto,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit) {

		ResponsePageableDTO<List<D>> response = new ResponsePageableDTO<>();

		// Busca as entidades utilizando os filtros
		Page<E> pageEntities = filterEntities(filterDto, page, limit);

		List<D> dtos = new ArrayList<>();

		// Transfere as entidades para a lista de DTOs, por meio da conversão
		pageEntities.forEach(e -> dtos.add(getConverter().convertEntityToDTO(e)));

		// Cria os links para os DTOs
		dtos.forEach(d -> createSelfLink(d));

		// Atualiza a resposta com os dados
		response.setData(dtos);
		response.addPageInfo(pageEntities);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Retorna um registro pelo id.
	 * 
	 * @param id o id do registro
	 * @return a resposta com a entidade encontrada ou a resposta com erro se não
	 *         encontrar
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO<D>> getById(@PathVariable Long id) {

		ResponseDTO<D> response = new ResponseDTO<>();

		// Busca a entidade pelo id
		E entity = service.findById(id);

		// Se não encontrar a entidade retorna com 404
		if (entity == null) {
			response.addError("Registro não encontrado!");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		// Converte a entidade em DTO
		D dto = getConverter().convertEntityToDTO(entity);

		// Cria os links para o DTO
		createLinks(dto);

		// Atualiza a resposta com os dados
		response.setData(dto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Salva uma entidade, seja inclusão ou alteração.
	 * 
	 * @param dto    o DTO com as informações a serem salvas
	 * @param result resultado da requisição, para tratamento de erros
	 * @return a resposta com a entidade salva se não ocorrer erros ou a resposta
	 *         com erros
	 */
	@PostMapping
	public ResponseEntity<ResponseDTO<D>> save(@Valid D dto, BindingResult result) {

		ResponseDTO<D> response = new ResponseDTO<>();

		// Verifica se a requisição gerou erros e faz o tratamento
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.addError(e.getDefaultMessage()));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		// Converte o DTO da requisição em uma entidade
		E entity = getConverter().convertDTOToEntity(dto);

		// Salva a entidade
		entity = service.save(entity);

		// Converte a entidade salva em um novo DTO
		D savedDTO = getConverter().convertEntityToDTO(entity);

		// Cria o link genérico para o DTO
		createLinks(savedDTO);

		// Atualiza a resposta com os dados
		response.setData(savedDTO);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * Deleta uma entidade a partir do id.
	 * 
	 * @param id o id da entidade a ser deletada
	 * @return a resposta com sucesso se deletada ou erro se não encontrar
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO<String>> delete(@PathVariable Long id) {

		ResponseDTO<String> response = new ResponseDTO<>();

		// Busca a entidade pelo id
		E entity = service.findById(id);

		// Se não encontrar a entidade retorna com 404
		if (entity == null) {
			response.addError("Registro não encontrado!");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		// Deleta a entidade pelo id
		service.deleteById(entity.getId());

		// Atualiza a resposta com uma mensagem de sucesso
		response.setData("Registro excluído com sucesso!");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Retorna a classe do controller.
	 * 
	 * @return um {@link Class}
	 */
	protected abstract Class<? extends BasicController<E, D, S, C, F>> getControllerClass();

	/**
	 * Busca as entidades utilizando os filtros repassados na requisição.
	 * 
	 * @param filter os filtros da requisição
	 * @param page   o número da página a ser buscada
	 * @param limit  o limite de registros a ser buscado
	 * @return
	 */
	protected Page<E> filterEntities(F filter, int page, int limit) {
		return service.findByPage(page, limit);
	}

	/**
	 * Cria um link para adicionar na resposta da requisição.
	 * 
	 * @param dto o DTO onde será criado o link
	 */
	protected final void createGenericLink(D dto) {
		Link link = WebMvcLinkBuilder.linkTo(getControllerClass()).withSelfRel();

		dto.add(link);
	}

	/**
	 * Cria um link a partir de um método para adicionar na resposta da requisição.
	 * 
	 * @param dto o DTO onde será criado o link
	 */
	protected void createGenericMethodLink(Object method, D dto) {
		Link link = WebMvcLinkBuilder.linkTo(method).withSelfRel();
		dto.add(link);
	}

	/**
	 * Cria um link para adicionar na resposta da requisição.
	 * 
	 * @param dto o DTO onde será criado o link
	 */
	protected final void createSelfLink(D dto) {
		Link link = WebMvcLinkBuilder.linkTo(getControllerClass()).slash(dto.getId()).withSelfRel();

		dto.add(link);
	}

	/**
	 * Cria os links para um DTO. Pode ser sobrescrito para customização.
	 * 
	 * @param dto o DTO que receberá os links
	 */
	protected void createLinks(D dto) {
		createGenericLink(dto);
	}

}
