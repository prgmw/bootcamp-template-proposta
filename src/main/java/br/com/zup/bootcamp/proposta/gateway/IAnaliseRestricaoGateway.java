package br.com.zup.bootcamp.proposta.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.bootcamp.proposta.gateway.dto.input.SolicitacaoInput;
import br.com.zup.bootcamp.proposta.gateway.dto.output.AnaliseResponse;

@FeignClient(value = "analise", url = "${analise-api.base-url}")
public interface IAnaliseRestricaoGateway {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao", consumes = "application/json")
	AnaliseResponse consultar(SolicitacaoInput solicitacao);

}
