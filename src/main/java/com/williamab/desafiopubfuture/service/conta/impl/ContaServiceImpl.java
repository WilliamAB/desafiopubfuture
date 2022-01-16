package com.williamab.desafiopubfuture.service.conta.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.williamab.desafiopubfuture.exception.ContaNotFoundException;
import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.repository.conta.ContaRepository;
import com.williamab.desafiopubfuture.service.conta.ContaService;
import com.williamab.desafiopubfuture.service.impl.BasicServiceImpl;

/**
 * Implementação de {@link ContaService}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@Service
public class ContaServiceImpl extends BasicServiceImpl<ContaEntity, ContaRepository> implements ContaService {

	@Override
	public Double getSaldoTotal() {
		return getRepository().sumSaldoContas();
	}

	@Override
	@Transactional
	public void transferirSaldo(Long idContaOrigem, Long idContaDestino, Double valor) {

		if (valor <= 0.0) {
			throw new IllegalArgumentException("Valor da transferência deve ser maior que zero!");
		}

		if (idContaOrigem.equals(idContaDestino)) {
			throw new IllegalArgumentException("Conta de destino não pode ser a mesma que a de destino!");
		}

		ContaEntity contaOrigem = findById(idContaOrigem);

		if (contaOrigem == null) {
			throw new ContaNotFoundException(idContaOrigem);
		}

		ContaEntity contaDestino = findById(idContaDestino);

		if (contaDestino == null) {
			throw new ContaNotFoundException(idContaDestino);
		}

		Double saldoContaOrigem = contaOrigem.getSaldo() - valor;
		contaOrigem.setSaldo(saldoContaOrigem);

		Double saldoContaDestino = contaDestino.getSaldo() + valor;
		contaDestino.setSaldo(saldoContaDestino);

		save(contaOrigem);
		save(contaDestino);
	}

}
