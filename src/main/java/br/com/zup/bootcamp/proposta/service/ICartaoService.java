package br.com.zup.bootcamp.proposta.service;

import java.util.Optional;

import br.com.zup.bootcamp.proposta.domain.model.Cartao;

public interface ICartaoService {
	Optional<Cartao> obter(Long id);
}
