package com.williamab.desafiopubfuture.service.receita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

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
import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;
import com.williamab.desafiopubfuture.service.conta.ContaService;

/**
 * Testes para {@link ReceitaService}.
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
public class ReceitaServiceTest {

	@Autowired
	private TipoReceitaService tipoReceitaService;

	@Autowired
	private ContaService contaService;

	@Autowired
	private ReceitaService receitaService;

	// Dados da conta
	private final String CONTA_INSTITUICAO_FINANCEIRA = "Instituição financeira Teste Service";
	private final TipoConta CONTA_TIPO_CONTA = TipoConta.POUPANCA;
	private final Double CONTA_SALDO = 123.45;

	// Dados do tipo de receita
	private final String TIPO_RECEITA_DESCRICAO = "Tipo de receita Teste Service";

	// Dados da receita
	private final Double RECEITA_VALOR = 12.34;
	private final Date RECEITA_DATA_RECEBIMENTO = new Date();
	private final Date RECEITA_DATA_RECEBIMENTO_ESPERADO = new Date();
	private final String RECEITA_DESCRICAO = "Descrição da receita Teste Service";

	private Long idReceita = 0L;

	@Test
	@Order(1)
	public void testService() {
		assertNotNull(tipoReceitaService);
		assertNotNull(contaService);
		assertNotNull(receitaService);
	}

	@Test
	@Order(2)
	public void testSave() {

		// Cria uma conta para vincular na receita
		ContaEntity conta = new ContaEntity();
		conta.setInstituicaoFinanceira(CONTA_INSTITUICAO_FINANCEIRA);
		conta.setSaldo(CONTA_SALDO);
		conta.setTipoConta(CONTA_TIPO_CONTA);

		conta = contaService.save(conta);

		assertNotNull(conta);

		// Cria um tipo de receita para vincular na receita
		TipoReceitaEntity tipoReceita = new TipoReceitaEntity();
		tipoReceita.setDescricao(TIPO_RECEITA_DESCRICAO);

		tipoReceita = tipoReceitaService.save(tipoReceita);

		assertNotNull(tipoReceita);

		// Cria a receita
		ReceitaEntity receita = new ReceitaEntity();
		receita.setValor(RECEITA_VALOR);
		receita.setDataRecebimento(RECEITA_DATA_RECEBIMENTO);
		receita.setDataRecebimentoEsperado(RECEITA_DATA_RECEBIMENTO_ESPERADO);
		receita.setDescricao(RECEITA_DESCRICAO);
		receita.setConta(conta);
		receita.setTipoReceita(tipoReceita);

		receita = receitaService.save(receita);

		assertNotNull(receita);
		assertNotNull(receita.getId());
		assertNotNull(receita.getConta());
		assertNotNull(receita.getTipoReceita());
		assertEquals(RECEITA_VALOR, receita.getValor());
		assertEquals(RECEITA_DATA_RECEBIMENTO, receita.getDataRecebimento());
		assertEquals(RECEITA_DATA_RECEBIMENTO_ESPERADO, receita.getDataRecebimentoEsperado());
		assertEquals(RECEITA_DESCRICAO, receita.getDescricao());
		assertEquals(conta.getId(), receita.getConta().getId());
		assertEquals(tipoReceita.getId(), receita.getTipoReceita().getId());

		idReceita = receita.getId();
	}

	@Test
	@Order(3)
	public void testFindById() {
		ReceitaEntity entity = receitaService.findById(idReceita);

		assertNotNull(entity);
		assertNotNull(entity.getId());
		assertNotNull(entity.getConta());
		assertNotNull(entity.getTipoReceita());
		assertNotNull(entity.getConta().getId());
		assertNotNull(entity.getTipoReceita().getId());
		assertEquals(RECEITA_VALOR, entity.getValor());
		assertEquals(RECEITA_DATA_RECEBIMENTO, entity.getDataRecebimento());
		assertEquals(RECEITA_DATA_RECEBIMENTO_ESPERADO, entity.getDataRecebimentoEsperado());
		assertEquals(RECEITA_DESCRICAO, entity.getDescricao());
	}

	@Test
	@Order(4)
	public void testValorTotal() {
		Double valorTotal = receitaService.getValorTotal();
		assertTrue(valorTotal >= RECEITA_VALOR);
	}

	@Test
	@Order(5)
	public void testDelete() {
		receitaService.deleteById(idReceita);
		ReceitaEntity entity = receitaService.findById(idReceita);
		assertNull(entity);
	}

}
