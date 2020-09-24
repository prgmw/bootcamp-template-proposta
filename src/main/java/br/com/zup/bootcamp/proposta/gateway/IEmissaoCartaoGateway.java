package br.com.zup.bootcamp.proposta.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.bootcamp.proposta.gateway.dto.input.SolicitacaoInput;
import br.com.zup.bootcamp.proposta.gateway.dto.output.EmissaoResponse;

@FeignClient(value = "emissao", url = "${emissao-api.base-url}")
public interface IEmissaoCartaoGateway {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes", consumes = "application/json")
	EmissaoResponse emitir(SolicitacaoInput solicitacao);
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/cartoes?idProposta={idProposta}", consumes = "application/json")
	EmissaoResponse obter(@PathVariable(name = "idProposta") String idProposta);

}
