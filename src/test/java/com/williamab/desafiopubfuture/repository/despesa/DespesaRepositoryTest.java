package com.williamab.desafiopubfuture.repository.despesa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
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
import com.williamab.desafiopubfuture.model.despesa.DespesaEntity;
import com.williamab.desafiopubfuture.model.despesa.TipoDespesaEntity;
import com.williamab.desafiopubfuture.repository.conta.ContaRepository;

/**
 * Testes para {@link DespesaRepository}.
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
public class DespesaRepositoryTest {

	@Autowired
	private TipoDespesaRepository tipoDespesaRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private DespesaRepository despesaRepository;

	// Dados da conta
	private final Long CONTA_ID = 1L;
	private final String CONTA_INSTITUICAO_FINANCEIRA = "Instituição financeira Teste";
	private final TipoConta CONTA_TIPO_CONTA = TipoConta.POUPANCA;
	private final Double CONTA_SALDO = 123.45;

	// Dados do tipo de despesa
	private final Long TIPO_DESPESA_ID = 1L;
	private final String TIPO_DESPESA_DESCRICAO = "Tipo de despesa Teste";

	// Dados da despesa
	private final Double DESPESA_VALOR = 12.34;
	private final Date DESPESA_DATA_PAGAMENTO = new Date();
	private final Date DESPESA_DATA_PAGAMENTO_ESPERADO = new Date();

	@Test
	@Order(1)
	public void testRepository() {
		assertNotNull(tipoDespesaRepository);
		assertNotNull(contaRepository);
		assertNotNull(despesaRepository);
	}

	@Test
	@Order(2)
	public void testSave() {

		// Cria uma conta para vincular na despesa
		ContaEntity conta = new ContaEntity();
		conta.setId(CONTA_ID);
		conta.setInstituicaoFinanceira(CONTA_INSTITUICAO_FINANCEIRA);
		conta.setSaldo(CONTA_SALDO);
		conta.setTipoConta(CONTA_TIPO_CONTA);

		contaRepository.save(conta);

		assertNotNull(conta);

		// Cria um tipo de despesa para vincular na despesa
		TipoDespesaEntity tipoDespesa = new TipoDespesaEntity();
		tipoDespesa.setId(TIPO_DESPESA_ID);
		tipoDespesa.setDescricao(TIPO_DESPESA_DESCRICAO);

		tipoDespesaRepository.save(tipoDespesa);

		assertNotNull(tipoDespesa);

		// Cria a despesa
		DespesaEntity despesa = new DespesaEntity();
		despesa.setValor(DESPESA_VALOR);
		despesa.setDataPagamento(DESPESA_DATA_PAGAMENTO);
		despesa.setDataPagamentoEsperado(DESPESA_DATA_PAGAMENTO_ESPERADO);
		despesa.setConta(conta);
		despesa.setTipoDespesa(tipoDespesa);

		despesaRepository.save(despesa);

		assertNotNull(despesa);
		assertNotNull(despesa.getId());
		assertNotNull(despesa.getConta());
		assertNotNull(despesa.getTipoDespesa());
		assertEquals(DESPESA_VALOR, despesa.getValor());
		assertEquals(DESPESA_DATA_PAGAMENTO, despesa.getDataPagamento());
		assertEquals(DESPESA_DATA_PAGAMENTO_ESPERADO, despesa.getDataPagamentoEsperado());
		assertEquals(conta.getId(), despesa.getConta().getId());
		assertEquals(tipoDespesa.getId(), despesa.getTipoDespesa().getId());
	}

	@Test
	@Order(3)
	public void testFind() {
		List<DespesaEntity> despesas = despesaRepository.findAll();

		assertFalse(despesas.isEmpty());
		assertEquals(1, despesas.size());

		DespesaEntity despesa = despesas.get(0);

		assertNotNull(despesa);
		assertNotNull(despesa.getId());
		assertNotNull(despesa.getConta());
		assertNotNull(despesa.getTipoDespesa());
		assertNotNull(despesa.getConta().getId());
		assertNotNull(despesa.getTipoDespesa().getId());
		assertEquals(DESPESA_VALOR, despesa.getValor());
		assertEquals(DESPESA_DATA_PAGAMENTO, despesa.getDataPagamento());
		assertEquals(DESPESA_DATA_PAGAMENTO_ESPERADO, despesa.getDataPagamentoEsperado());
	}

	@Test
	@Order(4)
	public void testDelete() {
		List<DespesaEntity> despesas = despesaRepository.findAll();

		assertFalse(despesas.isEmpty());
		assertEquals(1, despesas.size());

		DespesaEntity despesa = despesas.get(0);

		despesaRepository.deleteById(despesa.getId());

		Optional<DespesaEntity> optional = despesaRepository.findById(despesa.getId());
		assertTrue(optional.isEmpty());
	}

	@AfterAll
	public void finish() {
		despesaRepository.deleteAll();
		tipoDespesaRepository.deleteAll();
		contaRepository.deleteAll();
	}

}
