package com.williamab.desafiopubfuture.service.conta;

import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.service.BasicService;

/**
 * Serviço de manutenção dos dados de {@link ContaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface ContaService extends BasicService<ContaEntity> {

	/**
	 * Retorna o resultado da soma do saldo das contas.
	 * 
	 * @return o valor total
	 */
	Double getSaldoTotal();
	
	/**
	 * Transfere saldo entre contas. O valor transferido deve ser positivo maior que
	 * zero.
	 * 
	 * @param idContaOrigem  o id da conta de origem
	 * @param idContaDestino o id da conta de destino
	 * @param valor          o valor da transferência
	 */
	void transferirSaldo(Long idContaOrigem, Long idContaDestino, Double valor);

}
