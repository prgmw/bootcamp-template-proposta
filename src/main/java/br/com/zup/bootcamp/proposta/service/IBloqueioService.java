package br.com.zup.bootcamp.proposta.service;

import java.util.Optional;

import br.com.zup.bootcamp.proposta.domain.model.Bloqueio;

public interface IBloqueioService {

	public Optional<Bloqueio> cadastrarBloqueio(Optional<Long> idCartao, Optional<String> agenteUsuario, Optional<String> ip);

	public void notificarBloqueio();

}
