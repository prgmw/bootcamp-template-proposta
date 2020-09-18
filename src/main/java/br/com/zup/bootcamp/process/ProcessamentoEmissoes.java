package br.com.zup.bootcamp.process;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProcessamentoEmissoes {

	@Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
	private void executaOperacao() {
		
	}
}
