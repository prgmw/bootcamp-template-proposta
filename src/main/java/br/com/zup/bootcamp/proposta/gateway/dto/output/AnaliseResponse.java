package br.com.zup.bootcamp.proposta.gateway.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnaliseResponse {

	@JsonProperty("idProposta")
	private String idProposta;

	@JsonProperty("documento")
	private String documento;

	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("resultadoSolicitacao")
	private String resultadoSolicitacao;

}
