package com.williamab.desafiopubfuture.service.receita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
 * Testes para {@link TipoReceitaService}.
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
public class TipoReceitaServiceTest {

	@Autowired
	private TipoReceitaService service;

	private final String DESCRICAO = "Tipo de receita Teste Service";

	private Long id = 0L;

	@Test
	@Order(1)
	public void testService() {
		assertNotNull(service);
	}

	@Test
	@Order(2)
	public void testSave() {
		TipoReceitaEntity entity = new TipoReceitaEntity();
		entity.setDescricao(DESCRICAO);

		entity = service.save(entity);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(DESCRICAO, entity.getDescricao());

		id = entity.getId();
	}

	@Test
	@Order(3)
	public void testFindById() {
		TipoReceitaEntity entity = service.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(DESCRICAO, entity.getDescricao());
	}

	@Test
	@Order(4)
	public void testDelete() {
		service.deleteById(id);
		TipoReceitaEntity entity = service.findById(id);
		assertNull(entity);
	}

}
