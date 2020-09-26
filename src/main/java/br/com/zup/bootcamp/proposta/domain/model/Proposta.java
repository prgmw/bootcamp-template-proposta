package br.com.zup.bootcamp.proposta.domain.model;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.zup.bootcamp.proposta.validator.IsCpfCnpjValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@JsonManagedReference
	@OneToOne(mappedBy = "proposta", cascade = CascadeType.ALL)
	private Emissao emissao;
	
	public void setEmissao(Emissao emissao) {
		this.emissao = emissao;
	}

	public void definirStatus(Optional<Restricao> avaliacao) {
		this.status = Status.NAO_ELEGIVEL;
		if (avaliacao.get() == Restricao.SEM_RESTRICAO) {
			this.status = Status.ELEGIVEL;
		}
	}
}
