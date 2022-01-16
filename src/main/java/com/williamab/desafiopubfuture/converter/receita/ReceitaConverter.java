package com.williamab.desafiopubfuture.converter.receita;

import com.williamab.desafiopubfuture.converter.BasicConverter;
import com.williamab.desafiopubfuture.dto.model.receita.ReceitaDTO;
import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;

/**
 * Conversor entidade/DTO para receita.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class ReceitaConverter extends BasicConverter<ReceitaEntity, ReceitaDTO> {

	private static ReceitaConverter INSTANCE;

	private ReceitaConverter() {
	}

	public static ReceitaConverter getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ReceitaConverter();
		}
		return INSTANCE;
	}

	@Override
	protected ReceitaEntity createEmptyEntity() {
		return new ReceitaEntity();
	}

	@Override
	protected ReceitaDTO createEmptyDTO() {
		return new ReceitaDTO();
	}

	@Override
	protected void convertFieldsFromEntityToDTO(ReceitaEntity entity, ReceitaDTO dto) {
		dto.setValor(entity.getValor());
		dto.setDescricao(entity.getDescricao());
		dto.setDataRecebimento(entity.getDataRecebimento());
		dto.setDataRecebimentoEsperado(entity.getDataRecebimentoEsperado());
		dto.setContaId(entity.getConta().getId());
		dto.setTipoReceitaId(entity.getTipoReceita().getId());
	}

	@Override
	protected void convertFieldsFromDTOToEntity(ReceitaDTO dto, ReceitaEntity entity) {
		entity.setValor(dto.getValor());
		entity.setDescricao(dto.getDescricao());
		entity.setDataRecebimento(dto.getDataRecebimento());
		entity.setDataRecebimentoEsperado(dto.getDataRecebimentoEsperado());

		ContaEntity contaEntity = new ContaEntity();
		contaEntity.setId(dto.getContaId());
		entity.setConta(contaEntity);

		TipoReceitaEntity tipoReceitaEntity = new TipoReceitaEntity();
		tipoReceitaEntity.setId(dto.getTipoReceitaId());
		entity.setTipoReceita(tipoReceitaEntity);
	}

}
