package br.com.zup.bootcamp.proposta.dto.input;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import lombok.Data;

@Data
public class SolicitacaoInput {

	@JsonProperty("idProposta")
	private Long idProposta;

	@JsonProperty("documento")
	private String documento;

	@JsonProperty("nome")
	private String nome;

	public SolicitacaoInput(Optional<Proposta> proposta) {
		this.idProposta = proposta.get().getId();
		this.documento = proposta.get().getDocumento();
		this.nome = proposta.get().getNome();
	}

}
