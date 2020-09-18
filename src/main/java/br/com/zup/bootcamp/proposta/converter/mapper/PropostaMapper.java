package br.com.zup.bootcamp.proposta.converter.mapper;

import org.mapstruct.Mapper;

import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.gateway.dto.input.PropostaInput;

@Mapper(componentModel="spring")
public interface PropostaMapper {

	PropostaInput propostaToPropostaDTO(Proposta proposta);
	Proposta propostaDTOToProposta(PropostaInput proposta);
	
}
