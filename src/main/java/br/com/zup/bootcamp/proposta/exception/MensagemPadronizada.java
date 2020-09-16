package br.com.zup.bootcamp.proposta.exception;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensagemPadronizada {

	private Collection<String> mensagens;

}
