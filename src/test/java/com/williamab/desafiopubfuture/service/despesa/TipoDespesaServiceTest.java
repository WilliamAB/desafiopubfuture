package com.williamab.desafiopubfuture.service.despesa;

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

import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;

/**
 * Testes para {@link TipoDespesaService}.
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
public class TipoDespesaServiceTest {

	@Autowired
	private TipoDespesaService service;

	private final String DESCRICAO = "Tipo de despesa Teste Service";

	private Long id = 0L;

	@Test
	@Order(1)
	public void testService() {
		assertNotNull(service);
	}

	@Test
	@Order(2)
	public void testSave() {
		TipoDespesaEntity entity = new TipoDespesaEntity();
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
		TipoDespesaEntity entity = service.findById(id);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertEquals(DESCRICAO, entity.getDescricao());
	}

	@Test
	@Order(4)
	public void testDelete() {
		service.deleteById(id);
		TipoDespesaEntity entity = service.findById(id);
		assertNull(entity);
	}

}
