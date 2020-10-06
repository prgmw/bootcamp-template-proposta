package br.com.zup.bootcamp.proposta.gateway.dto.output;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.bootcamp.proposta.domain.model.BloqueadoDesbloqueado;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BloqueioResponse {
	
	@JsonProperty("resultado")
	@Enumerated(EnumType.STRING)
	private BloqueadoDesbloqueado resultado;

}
