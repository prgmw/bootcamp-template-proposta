package br.com.zup.bootcamp.proposta.service;

import java.util.Optional;

import br.com.zup.bootcamp.proposta.domain.model.Restricao;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;

public interface IAvaliacaoRestricaoService {
	
	Restricao obterAvaliacaoRisco(Optional<Proposta> proposta);

}
