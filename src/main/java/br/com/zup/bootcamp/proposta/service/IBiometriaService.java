package br.com.zup.bootcamp.proposta.service;

import java.util.Optional;

import br.com.zup.bootcamp.proposta.domain.model.Biometria;

public interface IBiometriaService {

	public Optional<Biometria> cadastrar(Long idProposta, Biometria biometria);

	public Optional<Biometria> obter(Long idProposta, Long idBiometria);

}
