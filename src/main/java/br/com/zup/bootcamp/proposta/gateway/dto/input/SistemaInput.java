package br.com.zup.bootcamp.proposta.gateway.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SistemaInput {

	@JsonProperty("sistemaResponsavel")
	private String sistemaResponsavel = "PROPOSTA-APP";

}
