package br.com.zup.bootcamp.proposta.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.bootcamp.proposta.gateway.dto.input.SistemaInput;
import br.com.zup.bootcamp.proposta.gateway.dto.output.BloqueioResponse;

@FeignClient(value = "bloqueio", url = "${bloqueio-api.base-url}")
public interface INotificarBloqueioCartaoGateway {
	
	@RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
	BloqueioResponse notificar(@PathVariable(name = "id") String idCartao, SistemaInput sistema);

}
