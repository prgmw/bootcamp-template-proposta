package br.com.zup.bootcamp.proposta.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.bootcamp.proposta.service.IAssociacaoCartaoService;
import br.com.zup.bootcamp.proposta.service.IEmissaoCartaoService;

@Component
public class ProcessamentoEmissoes {

	@Autowired
	private IEmissaoCartaoService emissaoCartaoService;

	@Autowired
	private IAssociacaoCartaoService associacaoCartaoService;

//	@Scheduled(fixedDelayString = "${periodicidade.emissao-cartao}")
//	private void executaEmissao() {
//		emissaoCartaoService.emitirCartao();
//	}
//
//	@Scheduled(fixedDelayString = "${periodicidade.associacao-cartao}")
//	private void executaAssociacao() {
//		associacaoCartaoService.associarCartao();
//	}
}
