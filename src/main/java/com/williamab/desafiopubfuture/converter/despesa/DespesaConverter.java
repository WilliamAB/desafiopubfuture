package com.williamab.desafiopubfuture.converter.despesa;

import com.williamab.desafiopubfuture.converter.BasicConverter;
import com.williamab.desafiopubfuture.dto.model.despesa.DespesaDTO;
import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.model.despesa.DespesaEntity;
import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;

/**
 * Conversor entidade/DTO para despesa.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class DespesaConverter extends BasicConverter<DespesaEntity, DespesaDTO> {

	private static DespesaConverter INSTANCE;

	private DespesaConverter() {
	}

	public static DespesaConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DespesaConverter();
		}
		return INSTANCE;
	}

	@Override
	protected DespesaEntity createEmptyEntity() {
		return new DespesaEntity();
	}

	@Override
	protected DespesaDTO createEmptyDTO() {
		return new DespesaDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(DespesaEntity entity, DespesaDTO dto) {
		dto.setValor(entity.getValor());
		dto.setDataPagamento(entity.getDataPagamento());
		dto.setDataPagamentoEsperado(entity.getDataPagamentoEsperado());
		dto.setContaId(entity.getConta().getId());
		dto.setTipoDespesaId(entity.getTipoDespesa().getId());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(DespesaDTO dto, DespesaEntity entity) {
		entity.setValor(dto.getValor());
		entity.setDataPagamento(dto.getDataPagamento());
		entity.setDataPagamentoEsperado(dto.getDataPagamentoEsperado());

		ContaEntity contaEntity = new ContaEntity();
		contaEntity.setId(dto.getContaId());
		entity.setConta(contaEntity);

		TipoDespesaEntity tipoDespesaEntity = new TipoDespesaEntity();
		tipoDespesaEntity.setId(dto.getTipoDespesaId());
		entity.setTipoDespesa(tipoDespesaEntity);
	}

}
