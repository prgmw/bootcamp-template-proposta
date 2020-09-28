package br.com.zup.bootcamp.proposta.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.zup.bootcamp.proposta.validator.IsBase64;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Entity
@Table(name = "biometria")
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "proposta_id")
	private Proposta proposta;

	@IsBase64
	@Column(name = "identificador")
	private String identificador;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	public Biometria() {
		dataCriacao = LocalDateTime.now();
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

}
