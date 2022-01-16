package com.williamab.desafiopubfuture.converter.conta;

import com.williamab.desafiopubfuture.converter.BasicConverter;
import com.williamab.desafiopubfuture.dto.model.conta.ContaDTO;
import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.model.conta.TipoConta;

/**
 * Conversor entidade/DTO para conta.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class ContaConverter extends BasicConverter<ContaEntity, ContaDTO> {

	private static ContaConverter INSTANCE;

	private ContaConverter() {
	}

	public static ContaConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ContaConverter();
		}
		return INSTANCE;
	}

	@Override
	protected ContaEntity createEmptyEntity() {
		return new ContaEntity();
	}

	@Override
	protected ContaDTO createEmptyDTO() {
		return new ContaDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(ContaEntity entity, ContaDTO dto) {
		dto.setInstituicaoFinanceira(entity.getInstituicaoFinanceira());
		dto.setSaldo(entity.getSaldo());
		dto.setTipoConta(entity.getTipoConta().name());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(ContaDTO dto, ContaEntity entity) {
		entity.setInstituicaoFinanceira(dto.getInstituicaoFinanceira());
		entity.setSaldo(dto.getSaldo());
		entity.setTipoConta(TipoConta.valueOf(TipoConta.class, dto.getTipoConta()));
	}

}
