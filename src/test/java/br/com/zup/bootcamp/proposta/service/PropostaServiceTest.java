//package br.com.zup.bootcamp.proposta.service;
//
//import static org.mockito.Mockito.when;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Optional;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import br.com.zup.bootcamp.proposta.component.MensagemParametrizada;
//import br.com.zup.bootcamp.proposta.domain.model.Proposta;
//import br.com.zup.bootcamp.proposta.exception.ApiErroException;
//import br.com.zup.bootcamp.proposta.repository.PropostaRepository;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@PropertySource("classpath:messages.properties")
//public class PropostaServiceTest {
//
//	@MockBean
//	private PropostaRepository propostaRepo;
//
//	@Autowired
//	private IPropostaService propostaService;
//	
//	@Autowired
//	private MensagemParametrizada mensagem;
//
//	@Before
//	public void setUp() {
//		System.out.println("============================= Iniciando Teste =============================");
//	}
//
//	@After
//	public void setDown() {
//		System.out.println("=================================== Fim ===================================");
//	}
//
//	@Test
//	public void criarPropostaSucessoTest() {
//		Proposta entrada = Proposta.builder().nome("Usuario Teste").documento("094.246.920-80")
//				.email("teste@teste.com.br").endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build();
//
//		Proposta proposta = Proposta.builder().id(1L).nome("Usuario Teste").documento("094.246.920-80")
//				.email("teste@teste.com.br").endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build();
//
//		
//		when(propostaRepo.obterPropostaPorDocumento(Mockito.anyString())).thenReturn(Collections.emptyList());
//		when(propostaRepo.save(Mockito.anyObject())).thenReturn(proposta);
//
//		Optional<Proposta> saida = propostaService.criarProposta(entrada);
//
//		Assert.assertNotNull(saida.get());
//		Assert.assertEquals(entrada.getDocumento(), saida.get().getDocumento());
//
//	}
//
//	@Test(expected = ApiErroException.class)
//	public void criarPropostaExistenteComFalhaTest() {
//		Proposta entrada = Proposta.builder().nome("Usuario Teste").documento("094.246.920-80")
//				.email("teste@teste.com.br").endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build();
//
//		
//		Collection<Proposta> retorno = obterPropostaSaida();
//		when(propostaRepo.obterPropostaPorDocumento(Mockito.anyString())).thenReturn(retorno);
//		when(mensagem.get("proposta.documento.existente")).thenReturn("Mensagem");
//
//		propostaService.criarProposta(entrada);
//
//		Assert.fail();
//
//	}
//
//	private Collection<Proposta> obterPropostaSaida() {
//		Collection<Proposta> retorno = new ArrayList<Proposta>();
//		retorno.add(Proposta.builder().id(1L).nome("Usuario Teste").documento("094.246.920-80")
//				.email("teste@teste.com.br").endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build());
//		return retorno;
//	}
//
//}
