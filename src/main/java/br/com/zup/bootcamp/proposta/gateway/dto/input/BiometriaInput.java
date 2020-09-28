package br.com.zup.bootcamp.proposta.gateway.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.bootcamp.proposta.validator.IsBase64;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BiometriaInput {

	@JsonProperty("id")
	private Long id;

	@IsBase64
	@JsonProperty("identificador")
	private String identificador;

}
