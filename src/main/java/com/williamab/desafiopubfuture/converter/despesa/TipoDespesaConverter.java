package com.williamab.desafiopubfuture.converter.despesa;

import com.williamab.desafiopubfuture.converter.BasicConverter;
import com.williamab.desafiopubfuture.dto.model.despesa.TipoDespesaDTO;
import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;

/**
 * Conversor entidade/DTO para tipo de despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class TipoDespesaConverter extends BasicConverter<TipoDespesaEntity, TipoDespesaDTO> {

	private static TipoDespesaConverter INSTANCE;

	private TipoDespesaConverter() {
	}

	public static TipoDespesaConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TipoDespesaConverter();
		}
		return INSTANCE;
	}

	@Override
	protected TipoDespesaEntity createEmptyEntity() {
		return new TipoDespesaEntity();
	}

	@Override
	protected TipoDespesaDTO createEmptyDTO() {
		return new TipoDespesaDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(TipoDespesaEntity entity, TipoDespesaDTO dto) {
		dto.setDescricao(entity.getDescricao());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(TipoDespesaDTO dto, TipoDespesaEntity entity) {
		entity.setDescricao(dto.getDescricao());
	}

}
