package com.williamab.desafiopubfuture.converter;

import com.williamab.desafiopubfuture.dto.model.BasicDTO;
import com.williamab.desafiopubfuture.model.BasicEntity;

/**
 * Conversor base para as os conversores de entidade e DTO da aplicação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public abstract class BasicConverter<E extends BasicEntity, D extends BasicDTO<D, E>> {

	/**
	 * Converte uma entidade em um DTO.
	 * 
	 * @param entity a entidade a ser convertida
	 * @return o DTO como resultado da conversão
	 */
	public D convertEntityToDTO(E entity) {
		D dto = createEmptyDTO();

		dto.setId(entity.getId());

		convertFieldsFromEntityToDTO(entity, dto);

		return dto;
	}

	/**
	 * Converte um DTO em uma entidade.
	 * 
	 * @param dto o DTO a ser convertido
	 * @return a entidade como resultado da conversão
	 */
	public E convertDTOToEntity(D dto) {
		E entity = createEmptyEntity();

		entity.setId(dto.getId());

		convertFieldsFromDTOToEntity(dto, entity);

		return entity;
	}

	/**
	 * Cria uma instância da entidade, sem nenhum valor atribuído aos campos.
	 * 
	 * @return uma instância da entidade
	 */
	protected abstract E createEmptyEntity();

	/**
	 * Cria uma instância do DTO, sem nenhum valor atribuído aos campos.
	 * 
	 * @return uma instância do DTO
	 */
	protected abstract D createEmptyDTO();

	/**
	 * Converte os atributos da entidade para o DTO. O id já é convertido, não há
	 * necessidade de converter.
	 * 
	 * @param entity a entidade com as informações que serão copiadas para o DTO
	 * @param dto    o DTO que receberá as informações da entidade
	 */
	protected abstract void convertFieldsFromEntityToDTO(E entity, D dto);

	/**
	 * Converte os atributos do DTO para a entidade. O id já é convertido, não há
	 * necessidade de converter.
	 * 
	 * @param dto    o DTO com as informações que serão copiadas para a entidade
	 * @param entity a entidade que receberá as informações do DTO
	 */
	protected abstract void convertFieldsFromDTOToEntity(D dto, E entity);

}
