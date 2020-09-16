package br.com.zup.bootcamp.proposta.infrastructure.mapper;

import org.mapstruct.Mapper;

import br.com.zup.bootcamp.proposta.domain.data.PropostaDTO;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;

@Mapper(componentModel="spring")
public interface PropostaMapper {

	PropostaDTO propostaToPropostaDTO(Proposta proposta);
	Proposta propostaDTOToProposta(PropostaDTO proposta);
	
}
