package com.williamab.desafiopubfuture.converter.receita;

import com.williamab.desafiopubfuture.converter.BasicConverter;
import com.williamab.desafiopubfuture.dto.model.receita.TipoReceitaDTO;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;

/**
 * Conversor entidade/DTO para tipo de receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TipoReceitaConverter extends BasicConverter<TipoReceitaEntity, TipoReceitaDTO> {

	private static TipoReceitaConverter INSTANCE;

	private TipoReceitaConverter() {
	}

	public static TipoReceitaConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TipoReceitaConverter();
		}
		return INSTANCE;
	}

	@Override
	protected TipoReceitaEntity createEmptyEntity() {
		return new TipoReceitaEntity();
	}

	@Override
	protected TipoReceitaDTO createEmptyDTO() {
		return new TipoReceitaDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(TipoReceitaEntity entity, TipoReceitaDTO dto) {
		dto.setDescricao(entity.getDescricao());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(TipoReceitaDTO dto, TipoReceitaEntity entity) {
		entity.setDescricao(dto.getDescricao());
	}

}
