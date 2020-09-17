package br.com.zup.bootcamp.proposta.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.bootcamp.proposta.dto.input.SolicitacaoInput;
import br.com.zup.bootcamp.proposta.dto.output.AnaliseResponse;

@FeignClient("api")
public interface IAnaliseRestricaoGateway {
	
	@RequestMapping(method = RequestMethod.POST, value = "/solicitacao", consumes = "application/json")
    AnaliseResponse consultar(SolicitacaoInput solicitacao);

}
