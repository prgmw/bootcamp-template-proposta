package br.com.zup.bootcamp.proposta.domain.data;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.bootcamp.proposta.infrastructure.validator.IsCpfCnpjValid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropostaDTO {

	@JsonProperty("id")
	private Long id;

	@NotBlank
	@IsCpfCnpjValid
	@JsonProperty("documento")
	private String documento;

	@NotBlank
	@JsonProperty("nome")
	private String nome;

	@NotBlank
	@Email
	@JsonProperty("email")
	private String email;

	@NotBlank
	@JsonProperty("endereco")
	private String endereco;

	@NotNull
	@Positive
	@JsonProperty("salario")
	private BigDecimal salario;

}
