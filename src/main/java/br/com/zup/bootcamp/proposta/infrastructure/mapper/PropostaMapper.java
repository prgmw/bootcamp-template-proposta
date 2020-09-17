package br.com.zup.bootcamp.proposta.infrastructure.mapper;

import org.mapstruct.Mapper;

import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.dto.input.PropostaInput;

@Mapper(componentModel="spring")
public interface PropostaMapper {

	PropostaInput propostaToPropostaDTO(Proposta proposta);
	Proposta propostaDTOToProposta(PropostaInput proposta);
	
}
