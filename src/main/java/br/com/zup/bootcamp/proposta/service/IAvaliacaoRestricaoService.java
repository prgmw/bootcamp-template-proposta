package br.com.zup.bootcamp.proposta.service;

import java.util.Optional;

import br.com.zup.bootcamp.proposta.domain.model.AvaliacaoRestricao;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;

public interface IAvaliacaoRestricaoService {
	
	AvaliacaoRestricao avaliar(Optional<Proposta> proposta);

}
