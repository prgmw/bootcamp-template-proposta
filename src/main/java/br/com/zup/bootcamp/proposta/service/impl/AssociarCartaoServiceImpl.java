package br.com.zup.bootcamp.proposta.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.bootcamp.proposta.domain.model.Emissao;
import br.com.zup.bootcamp.proposta.gateway.IEmissaoCartaoGateway;
import br.com.zup.bootcamp.proposta.gateway.dto.output.EmissaoResponse;
import br.com.zup.bootcamp.proposta.repository.PropostaRepository;
import br.com.zup.bootcamp.proposta.service.IAssociacaoCartaoService;

@Service
public class AssociarCartaoServiceImpl implements IAssociacaoCartaoService {

	@Autowired
	private IEmissaoCartaoGateway emissaoGateway;
	
	@Autowired
	private PropostaRepository propostaRepository;

	@Override
	@Transactional
	public void associarCartao() {
		propostaRepository.obterPropostasSemEmissao().forEach(p -> {
			EmissaoResponse emitido = emissaoGateway.obter(p.getId() + "");
			Emissao emissao = Emissao.builder().emissaoId(emitido.getId()).proposta(p).build();
			p.setEmissao(emissao);
			propostaRepository.save(p);
		});
		
	}

}
