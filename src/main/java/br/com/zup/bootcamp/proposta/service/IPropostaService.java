package br.com.zup.bootcamp.proposta.service;

import java.util.Optional;

import br.com.zup.bootcamp.proposta.domain.model.Proposta;

public interface IPropostaService {

	Optional<Proposta> obterProposta(Long id);

	Optional<Proposta> criarProposta(Proposta proposta);

}
