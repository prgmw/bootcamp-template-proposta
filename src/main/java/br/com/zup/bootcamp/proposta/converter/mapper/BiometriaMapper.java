package br.com.zup.bootcamp.proposta.converter.mapper;

import org.mapstruct.Mapper;

import br.com.zup.bootcamp.proposta.domain.model.Biometria;
import br.com.zup.bootcamp.proposta.gateway.dto.input.BiometriaInput;

@Mapper(componentModel = "spring")
public interface BiometriaMapper {

	BiometriaInput biometriaToBiometriaDTO(Biometria biometria);

	Biometria biometriaDTOToBiometria(BiometriaInput biometria);

}
