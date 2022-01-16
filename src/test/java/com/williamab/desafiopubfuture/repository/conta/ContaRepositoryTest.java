package com.williamab.desafiopubfuture.repository.conta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.williamab.desafiopubfuture.model.conta.ContaEntity;
import com.williamab.desafiopubfuture.model.conta.TipoConta;

/**
 * Testes para {@link ContaRepository}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ContaRepositoryTest {

	@Autowired
	private ContaRepository repository;

	private final String INSTITUICAO_FINANCEIRA = "Instituição financeira Teste 1";
	private final Double SALDO = 1234.56;
	private final TipoConta TIPO_CONTA = TipoConta.CONTA_CORRENTE;

	private Long id = 0L;

	@Test
	@Order(1)
	public void testRepository() {
		assertNotNull(repository);
	}

	@Test
	@Order(2)
	public void testSave() {
		ContaEntity entity = new ContaEntity();
		entity.setInstituicaoFinanceira(INSTITUICAO_FINANCEIRA);
		entity.setSaldo(SALDO);
		entity.setTipoConta(TIPO_CONTA);

		entity = repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(INSTITUICAO_FINANCEIRA, entity.getInstituicaoFinanceira());
		assertEquals(SALDO, entity.getSaldo());
		assertEquals(TIPO_CONTA, entity.getTipoConta());

		id = entity.getId();
	}

	@Test
	@Order(3)
	public void testFindById() {
		Optional<ContaEntity> optional = repository.findById(id);

		assertTrue(optional.isPresent());

		ContaEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(INSTITUICAO_FINANCEIRA, entity.getInstituicaoFinanceira());
		assertEquals(SALDO, entity.getSaldo());
		assertEquals(TIPO_CONTA, entity.getTipoConta());
	}

	@Test
	@Order(4)
	public void testSumSaldoContas() {
		Double saldoTotal = repository.sumSaldoContas();
		assertTrue(saldoTotal >= SALDO);
	}

	@Test
	@Order(5)
	public void testDelete() {
		repository.deleteById(id);
		Optional<ContaEntity> optional = repository.findById(id);
		assertTrue(optional.isEmpty());
	}

}
