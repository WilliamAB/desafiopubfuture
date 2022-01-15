package com.williamab.desafiopubfuture.repository.conta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
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

	// Dados para o testSave1
	private final Long ID_1 = 1L;
	private final String INSTITUICAO_FINANCEIRA_1 = "Instituição financeira Teste 1";
	private final String INSTITUICAO_FINANCEIRA_ATUALIZADA_1 = "Instituição financeira Teste 1 atualizada";
	private final Double SALDO_1 = 1234.56;
	private final TipoConta TIPO_CONTA_1 = TipoConta.CONTA_CORRENTE;

	// Dados para o testSave2
	private final Double SALDO_2 = 0.0;
	private final TipoConta TIPO_CONTA_2 = TipoConta.CARTEIRA;	

	@Test
	@Order(1)
	public void testRepository() {
		assertNotNull(repository);
	}

	@Test
	@Order(2)
	public void testSave1() {
		ContaEntity entity = new ContaEntity();
		entity.setId(ID_1);
		entity.setInstituicaoFinanceira(INSTITUICAO_FINANCEIRA_1);
		entity.setSaldo(SALDO_1);
		entity.setTipoConta(TIPO_CONTA_1);

		repository.save(entity);

		assertNotNull(entity);
		assertEquals(ID_1, entity.getId());
		assertEquals(INSTITUICAO_FINANCEIRA_1, entity.getInstituicaoFinanceira());
		assertEquals(SALDO_1, entity.getSaldo());
		assertEquals(TIPO_CONTA_1, entity.getTipoConta());
	}

	@Test
	@Order(3)
	public void testSave2() {
		ContaEntity entity = new ContaEntity();
		entity.setSaldo(SALDO_2);
		entity.setTipoConta(TIPO_CONTA_2);

		repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertNull(entity.getInstituicaoFinanceira());
		assertEquals(SALDO_1, entity.getSaldo());
		assertEquals(TIPO_CONTA_1, entity.getTipoConta());
	}

	@Test
	@Order(4)
	public void testFindById() {
		Optional<ContaEntity> optional = repository.findById(ID_1);
		assertTrue(optional.isPresent());

		ContaEntity entity = optional.get();

		assertNotNull(entity);
		assertEquals(ID_1, entity.getId());
		assertEquals(INSTITUICAO_FINANCEIRA_1, entity.getInstituicaoFinanceira());
		assertEquals(SALDO_1, entity.getSaldo());
		assertEquals(TIPO_CONTA_1, entity.getTipoConta());
	}

	@Test
	@Order(5)
	public void testUpdate() {
		ContaEntity entity = new ContaEntity();
		entity.setId(ID_1);
		entity.setInstituicaoFinanceira(INSTITUICAO_FINANCEIRA_ATUALIZADA_1);

		repository.save(entity);

		assertNotNull(entity);
		assertEquals(ID_1, entity.getId());
		assertEquals(INSTITUICAO_FINANCEIRA_ATUALIZADA_1, entity.getInstituicaoFinanceira());
	}

	@Test
	@Order(6)
	public void testDelete() {
		repository.deleteById(ID_1);
		Optional<ContaEntity> optional = repository.findById(ID_1);
		assertTrue(optional.isEmpty());
	}

	@AfterAll
	public void finish() {
		repository.deleteAll();
	}

}
