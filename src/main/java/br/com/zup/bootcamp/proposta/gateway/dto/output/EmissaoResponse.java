package br.com.zup.bootcamp.proposta.gateway.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmissaoResponse {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("idProposta")
	private String idProposta;

}
