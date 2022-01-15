package com.williamab.desafiopubfuture.repository.despesa;

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

import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;

/**
 * Testes para {@link TipoDespesaRepository}.
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
public class TipoDespesaRepositoryTest {

	@Autowired
	private TipoDespesaRepository repository;

	private final String DESCRICAO = "Tipo de despesa Teste";

	private Long id = 0L;

	@Test
	@Order(1)
	public void testRepository() {
		assertNotNull(repository);
	}

	@Test
	@Order(2)
	public void testSave() {
		TipoDespesaEntity entity = new TipoDespesaEntity();
		entity.setDescricao(DESCRICAO);

		entity = repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(DESCRICAO, entity.getDescricao());

		id = entity.getId();
	}

	@Test
	@Order(3)
	public void testFindById() {
		Optional<TipoDespesaEntity> optional = repository.findById(id);

		assertTrue(optional.isPresent());

		TipoDespesaEntity entity = optional.get();

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(DESCRICAO, entity.getDescricao());
	}

	@Test
	@Order(4)
	public void testDelete() {
		repository.deleteById(id);
		Optional<TipoDespesaEntity> optional = repository.findById(id);
		assertTrue(optional.isEmpty());
	}

}
