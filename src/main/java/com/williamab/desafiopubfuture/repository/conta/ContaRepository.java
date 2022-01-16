package com.williamab.desafiopubfuture.repository.conta;

import org.springframework.data.jpa.repository.Query;

import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.repository.BasicRepository;

/**
 * Repositório para {@link ContaEntity}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public interface ContaRepository extends BasicRepository<ContaEntity> {

	/**
	 * Soma o saldo de todas as contas.
	 * 
	 * @return o valor somado
	 */
	@Query("select sum(c.saldo) from ContaEntity c")
	Double sumSaldoContas();

}
