package br.com.zup.bootcamp.proposta.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.bootcamp.proposta.infrastructure.validator.IsCpfCnpjValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "proposta")
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@IsCpfCnpjValid
	@Column(name = "documento")
	private String documento;

	@NotBlank
	@Column(name = "nome")
	private String nome;

	@NotBlank
	@Email
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "endereco")
	private String endereco;

	@NotNull
	@Positive
	@Column(name = "salario")
	private BigDecimal salario;

}
