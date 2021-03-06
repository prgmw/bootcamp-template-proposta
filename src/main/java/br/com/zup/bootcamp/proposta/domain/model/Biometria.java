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
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;

	@IsBase64
	@Column(name = "identificador")
	private String identificador;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	public void setProposta(Cartao cartao) {
		this.cartao = cartao;
	}

}
