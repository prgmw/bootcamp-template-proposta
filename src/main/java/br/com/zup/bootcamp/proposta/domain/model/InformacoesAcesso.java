package br.com.zup.bootcamp.proposta.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class InformacoesAcesso {
	
	@Column(name = "ip")
	private String ip;

	@Column(name = "agente_usuario")
	private String agenteUsuario;

}
