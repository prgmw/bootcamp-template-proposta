package br.com.zup.bootcamp.proposta.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.bootcamp.proposta.domain.data.PropostaDTO;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.service.IPropostaService;

@RunWith(SpringRunner.class)
@WebMvcTest(PropostaController.class)
public class PropostaControllerTest {

	@Autowired
	private MockMvc MockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IPropostaService propostaService;
	
	@Before
	public void setUp() {
		System.out.println("============================= Iniciando Teste =============================");
	}
	
	@After
	public void setDown() {
		System.out.println("=================================== Fim ===================================");
	}

	@Test
	public void obterPropostaSucessoTest() throws Exception {
		Optional<Proposta> retorno = obterPropostaSaida();
		when(propostaService.obterProposta(Mockito.anyLong())).thenReturn(retorno);
		this.MockMvc.perform(get("/proposta/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void incluirPropostaSucessoTest() throws Exception {
		PropostaDTO entrada = PropostaDTO.builder().nome("Usuario Teste").documento("094.246.920-80")
				.email("teste@teste.com.br").endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build();

		Optional<Proposta> retorno = obterPropostaSaida();

		String payload = objectMapper.writeValueAsString(entrada);

		when(propostaService.criarProposta(entrada)).thenReturn(retorno);

		this.MockMvc
				.perform(MockMvcRequestBuilders.post("/proposta").content(payload)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

	}

	@Test
	public void incluirPropostaSemNomeFalhaTest() throws Exception {
		PropostaDTO entrada = PropostaDTO.builder().nome(null).documento("094.246.920-80").email("teste@teste.com.br")
				.endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build();

		Optional<Proposta> retorno = obterPropostaSaida();

		String payload = objectMapper.writeValueAsString(entrada);

		when(propostaService.criarProposta(entrada)).thenReturn(retorno);

		this.MockMvc
				.perform(MockMvcRequestBuilders.post("/proposta").content(payload)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400));

	}

	@Test
	public void incluirPropostaSemDocumentoFalhaTest() throws Exception {
		PropostaDTO entrada = PropostaDTO.builder().nome("Usuario Teste").documento(null).email("teste@teste.com.br")
				.endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build();

		Optional<Proposta> retorno = obterPropostaSaida();

		String payload = objectMapper.writeValueAsString(entrada);

		when(propostaService.criarProposta(entrada)).thenReturn(retorno);

		this.MockMvc
				.perform(MockMvcRequestBuilders.post("/proposta").content(payload)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400));

	}

	@Test
	public void incluirPropostaEmailFalhaTest() throws Exception {
		PropostaDTO entrada = PropostaDTO.builder().nome("Usuario Teste").documento("094.246.920-80").email(null)
				.endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build();

		Optional<Proposta> retorno = obterPropostaSaida();
		String payload = objectMapper.writeValueAsString(entrada);

		when(propostaService.criarProposta(entrada)).thenReturn(retorno);

		this.MockMvc
				.perform(MockMvcRequestBuilders.post("/proposta").content(payload)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400));

	}

	@Test
	public void incluirPropostaSemEnderecoFalhaTest() throws Exception {
		PropostaDTO entrada = PropostaDTO.builder().nome("Usuario Teste").documento("094.246.920-80")
				.email("teste@teste.com.br").endereco(null).salario(new BigDecimal(1000)).build();

		Optional<Proposta> retorno = obterPropostaSaida();

		String payload = objectMapper.writeValueAsString(entrada);

		when(propostaService.criarProposta(entrada)).thenReturn(retorno);

		this.MockMvc
				.perform(MockMvcRequestBuilders.post("/proposta").content(payload)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400));

	}

	@Test
	public void incluirPropostaSalarioNegativoFalhaTest() throws Exception {
		PropostaDTO entrada = PropostaDTO.builder().nome("Usuario Teste").documento("094.246.920-80")
				.email("teste@teste.com.br").endereco("Rua Teste, numero 500").salario(new BigDecimal(-1000)).build();

		Optional<Proposta> retorno = obterPropostaSaida();

		String payload = objectMapper.writeValueAsString(entrada);

		when(propostaService.criarProposta(entrada)).thenReturn(retorno);

		this.MockMvc
				.perform(MockMvcRequestBuilders.post("/proposta").content(payload)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400));

	}

	private Optional<Proposta> obterPropostaSaida() {
		return Optional.ofNullable(Proposta.builder().id(1L).nome("Usuario Teste").documento("094.246.920-80")
				.email("teste@teste.com.br").endereco("Rua Teste, numero 500").salario(new BigDecimal(1000)).build());
	}

}
