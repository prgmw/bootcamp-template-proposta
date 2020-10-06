package br.com.zup.bootcamp.proposta.service.impl;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.zup.bootcamp.proposta.component.MensagemParametrizada;
import br.com.zup.bootcamp.proposta.domain.model.BloqueadoDesbloqueado;
import br.com.zup.bootcamp.proposta.domain.model.Bloqueio;
import br.com.zup.bootcamp.proposta.domain.model.Cartao;
import br.com.zup.bootcamp.proposta.domain.model.InformacoesAcesso;
import br.com.zup.bootcamp.proposta.domain.model.SimNao;
import br.com.zup.bootcamp.proposta.exception.ApiErroException;
import br.com.zup.bootcamp.proposta.gateway.INotificarBloqueioCartaoGateway;
import br.com.zup.bootcamp.proposta.gateway.dto.input.SistemaInput;
import br.com.zup.bootcamp.proposta.gateway.dto.output.BloqueioResponse;
import br.com.zup.bootcamp.proposta.repository.BloqueioRepository;
import br.com.zup.bootcamp.proposta.service.IBloqueioService;
import br.com.zup.bootcamp.proposta.service.ICartaoService;

@Service
public class BloqueioServiceImpl implements IBloqueioService {
	
	private final Logger logger = LoggerFactory.getLogger(BiometriaServiceImpl.class);
	
	@Autowired
	private ICartaoService cartaoService;
	
	@Autowired
	private INotificarBloqueioCartaoGateway bloqueioGateway;
	
	@Autowired
	private BloqueioRepository bloqueioRepository;
	
	@Autowired
	private MensagemParametrizada mensagem;

	@Override
	public Optional<Bloqueio> cadastrarBloqueio(Optional<Long> idCartao, Optional<String> agenteUsuario, Optional<String> ip) {
		Optional<Cartao> cartao = cartaoService.obter(idCartao.get());
		if (!cartao.isPresent()) {
			logger.error(mensagem.get("bloqueio.cartao.invalido"), idCartao.get());
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageFormat.format(mensagem.get("bloqueio.cartao.invalido"), idCartao.get()));
		}
		
		InformacoesAcesso informacao = InformacoesAcesso.builder()
				.ip(ip.get())
				.agenteUsuario(agenteUsuario.get())
				.build();
		
		Bloqueio bloqueioPesistir = Bloqueio.builder()
				.cartao(cartao.get())
				.status(BloqueadoDesbloqueado.BLOQUEADO)
				.dataAcao(LocalDateTime.now())
				.informacoes(informacao)
				.build();
		
		return Optional.ofNullable(bloqueioRepository.save(bloqueioPesistir));
	}

	@Override
	public void notificarBloqueio() {
		bloqueioRepository.obterCartoesSemNotificao().forEach( b -> {
			BloqueioResponse bloqueio = bloqueioGateway.notificar(b.getCartao().getCartao(), new SistemaInput());
			if(BloqueadoDesbloqueado.BLOQUEADO == bloqueio.getResultado()) {
				b.setNotificado(SimNao.S);
				bloqueioRepository.save(b);
			}
		});
		
	}

}
