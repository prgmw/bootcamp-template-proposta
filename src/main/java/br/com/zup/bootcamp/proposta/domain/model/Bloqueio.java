package br.com.zup.bootcamp.proposta.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "bloqueio")
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;

	@Embedded
	private InformacoesAcesso informacoes;

	@Builder.Default
	@Column(name = "notificado")
	@Enumerated(EnumType.STRING)
	private SimNao notificado = SimNao.N;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private BloqueadoDesbloqueado status;

	@Column(name = "data_acao")
	private LocalDateTime dataAcao;

	public void setNotificado(SimNao notificao) {
		this.notificado = notificao;
	}

	public void setProposta(Cartao cartao) {
		this.cartao = cartao;
	}

}
