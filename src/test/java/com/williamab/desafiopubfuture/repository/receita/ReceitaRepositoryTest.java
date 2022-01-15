package com.williamab.desafiopubfuture.repository.receita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
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
import com.williamab.desafiopubfuture.model.receita.ReceitaEntity;
import com.williamab.desafiopubfuture.model.receita.TipoReceitaEntity;
import com.williamab.desafiopubfuture.repository.conta.ContaRepository;

/**
 * Testes para {@link ReceitaRepository}.
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
public class ReceitaRepositoryTest {

	@Autowired
	private TipoReceitaRepository tipoReceitaRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ReceitaRepository receitaRepository;

	// Dados da conta
	private final String CONTA_INSTITUICAO_FINANCEIRA = "Instituição financeira Teste";
	private final TipoConta CONTA_TIPO_CONTA = TipoConta.POUPANCA;
	private final Double CONTA_SALDO = 123.45;

	// Dados do tipo de receita
	private final String TIPO_RECEITA_DESCRICAO = "Tipo de receita Teste";

	// Dados da receita
	private final Double RECEITA_VALOR = 12.34;
	private final Date RECEITA_DATA_RECEBIMENTO = new Date();
	private final Date RECEITA_DATA_RECEBIMENTO_ESPERADO = new Date();
	private final String RECEITA_DESCRICAO = "Descrição da receita Teste";

	private Long idReceita = 0L;

	@Test
	@Order(1)
	public void testRepository() {
		assertNotNull(tipoReceitaRepository);
		assertNotNull(contaRepository);
		assertNotNull(receitaRepository);
	}

	@Test
	@Order(2)
	public void testSave() {

		// Cria uma conta para vincular na receita
		ContaEntity conta = new ContaEntity();
		conta.setInstituicaoFinanceira(CONTA_INSTITUICAO_FINANCEIRA);
		conta.setSaldo(CONTA_SALDO);
		conta.setTipoConta(CONTA_TIPO_CONTA);

		conta = contaRepository.save(conta);

		assertNotNull(conta);

		// Cria um tipo de receita para vincular na receita
		TipoReceitaEntity tipoReceita = new TipoReceitaEntity();
		tipoReceita.setDescricao(TIPO_RECEITA_DESCRICAO);

		tipoReceita = tipoReceitaRepository.save(tipoReceita);

		assertNotNull(tipoReceita);

		// Cria a receita
		ReceitaEntity receita = new ReceitaEntity();
		receita.setValor(RECEITA_VALOR);
		receita.setDataRecebimento(RECEITA_DATA_RECEBIMENTO);
		receita.setDataRecebimentoEsperado(RECEITA_DATA_RECEBIMENTO_ESPERADO);
		receita.setDescricao(RECEITA_DESCRICAO);
		receita.setConta(conta);
		receita.setTipoReceita(tipoReceita);

		receita = receitaRepository.save(receita);

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
		Optional<ReceitaEntity> optional = receitaRepository.findById(idReceita);

		assertTrue(optional.isPresent());

		ReceitaEntity entity = optional.get();

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
	public void testDelete() {
		receitaRepository.deleteById(idReceita);
		Optional<ReceitaEntity> optional = receitaRepository.findById(idReceita);
		assertTrue(optional.isEmpty());
	}

}
