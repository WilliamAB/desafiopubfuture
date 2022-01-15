package com.williamab.desafiopubfuture.repository.receita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;

/**
 * Testes para {@link TipoReceitaRepository}.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class TipoReceitaRepositoryTest {

	@Autowired
	private TipoReceitaRepository repository;

	private final Long ID_1 = 1L;
	private final String DESCRICAO_1 = "Tipo de receita Teste 1";
	private final String DESCRICAO_ATUALIZADA_1 = "Tipo de receita Teste 1 atualizada";

	private final String DESCRICAO_2 = "Tipo de receita Teste 2";

	@Test
	@Order(1)
	public void testRepository() {
		assertNotNull(repository);
	}

	@Test
	@Order(2)
	public void testSave1() {
		TipoReceitaEntity entity = new TipoReceitaEntity();
		entity.setId(ID_1);
		entity.setDescricao(DESCRICAO_1);

		repository.save(entity);

		assertNotNull(entity);
		assertEquals(ID_1, entity.getId());
		assertEquals(DESCRICAO_1, entity.getDescricao());
	}

	@Test
	@Order(3)
	public void testSave2() {
		TipoReceitaEntity entity = new TipoReceitaEntity();
		entity.setDescricao(DESCRICAO_2);

		repository.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(DESCRICAO_2, entity.getDescricao());
	}

	@Test
	@Order(4)
	public void testFindById() {
		Optional<TipoReceitaEntity> optional = repository.findById(ID_1);
		assertTrue(optional.isPresent());

		TipoReceitaEntity entity = optional.get();

		assertNotNull(entity);
		assertEquals(ID_1, entity.getId());
		assertEquals(DESCRICAO_1, entity.getDescricao());
	}

	@Test
	@Order(5)
	public void testUpdate() {
		TipoReceitaEntity entity = new TipoReceitaEntity();
		entity.setId(ID_1);
		entity.setDescricao(DESCRICAO_ATUALIZADA_1);

		repository.save(entity);

		assertNotNull(entity);
		assertEquals(ID_1, entity.getId());
		assertEquals(DESCRICAO_ATUALIZADA_1, entity.getDescricao());
	}

	@Test
	@Order(6)
	public void testDelete() {
		repository.deleteById(ID_1);
		Optional<TipoReceitaEntity> optional = repository.findById(ID_1);
		assertTrue(optional.isEmpty());
	}

	@AfterAll
	public void finish() {
		repository.deleteAll();
	}

}
