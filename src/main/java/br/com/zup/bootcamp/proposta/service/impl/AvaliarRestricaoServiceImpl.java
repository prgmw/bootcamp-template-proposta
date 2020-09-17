package br.com.zup.bootcamp.proposta.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.zup.bootcamp.proposta.domain.model.AvaliacaoRestricao;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.dto.input.SolicitacaoInput;
import br.com.zup.bootcamp.proposta.dto.output.AnaliseResponse;
import br.com.zup.bootcamp.proposta.exception.ApiErroException;
import br.com.zup.bootcamp.proposta.gateway.IAnaliseRestricaoGateway;
import br.com.zup.bootcamp.proposta.infrastructure.configuration.MensagemParametrizada;
import br.com.zup.bootcamp.proposta.service.IAvaliacaoRestricaoService;

public class AvaliarRestricaoServiceImpl implements IAvaliacaoRestricaoService {
	
	@Autowired
	private MensagemParametrizada mensagem;
	
	@Autowired
	private IAnaliseRestricaoGateway analiseGateway;

	@Override
	public AvaliacaoRestricao avaliar(Optional<Proposta> proposta) {
		if(!proposta.isPresent()) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, mensagem.get("proposta.estado.invalido"));
		}	
		AnaliseResponse consultar = analiseGateway.consultar(new SolicitacaoInput(proposta));		
		return AvaliacaoRestricao.valueOf(consultar.getResultadoSolicitacao());
	}
}
