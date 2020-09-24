package br.com.zup.bootcamp.proposta.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.bootcamp.proposta.gateway.IEmissaoCartaoGateway;
import br.com.zup.bootcamp.proposta.gateway.dto.input.SolicitacaoInput;
import br.com.zup.bootcamp.proposta.repository.PropostaRepository;
import br.com.zup.bootcamp.proposta.service.IEmissaoCartaoService;

@Service
public class EmissaoCartaoServiceImpl implements IEmissaoCartaoService {

	@Autowired
	private IEmissaoCartaoGateway emissaoGateway;

	@Autowired
	private PropostaRepository propostaRepository;

	@Override
	@Transactional
	public void emitirCartao() {
		propostaRepository.obterPropostasSemEmissao().forEach(p -> {
			emissaoGateway.emitir(new SolicitacaoInput(Optional.of(p)));
		});
	}

}
