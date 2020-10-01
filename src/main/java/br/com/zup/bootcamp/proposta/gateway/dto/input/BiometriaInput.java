package br.com.zup.bootcamp.proposta.gateway.dto.input;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.bootcamp.proposta.validator.IsBase64;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BiometriaInput {

	@JsonProperty("id")
	private Long id;

	@NotBlank
	@IsBase64
	@JsonProperty("identificador")
	private String identificador;

}
