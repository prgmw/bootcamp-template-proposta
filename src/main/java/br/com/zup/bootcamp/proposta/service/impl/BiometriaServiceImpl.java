package br.com.zup.bootcamp.proposta.service.impl;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.zup.bootcamp.proposta.component.MensagemParametrizada;
import br.com.zup.bootcamp.proposta.domain.model.Biometria;
import br.com.zup.bootcamp.proposta.domain.model.Cartao;
import br.com.zup.bootcamp.proposta.exception.ApiErroException;
import br.com.zup.bootcamp.proposta.repository.BiometriaRepository;
import br.com.zup.bootcamp.proposta.repository.CartaoRepository;
import br.com.zup.bootcamp.proposta.service.IBiometriaService;

@Service
public class BiometriaServiceImpl implements IBiometriaService {

	private final Logger logger = LoggerFactory.getLogger(BiometriaServiceImpl.class);

	@Autowired
	private BiometriaRepository biometriaRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private MensagemParametrizada mensagem;

	@Override
	public Optional<Biometria> cadastrar(Long idCartao, Biometria biometria) {
		Optional<Cartao> cartaoPersistido = cartaoRepository.findById(idCartao);
		if (!cartaoPersistido.isPresent()) {
			logger.error(mensagem.get("proposta.invalida"), idCartao);
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageFormat.format(mensagem.get("proposta.invalida"), idCartao));
		}

		List<Biometria> biometriaAssociada = cartaoPersistido.get().getBiometria().stream()
				.filter(b -> b.getIdentificador().equals(biometria.getIdentificador())).collect(Collectors.toList());

		if (biometriaAssociada.size() > 0) {
			logger.error(mensagem.get("biometria.invalida"), idCartao);
			throw new ApiErroException(HttpStatus.BAD_REQUEST,
					MessageFormat.format(mensagem.get("biometria.invalida"), idCartao));
		}

		Biometria novaBiometria = Biometria.builder()
				.dataCriacao(LocalDateTime.now())
				.identificador(biometria.getIdentificador())
				.cartao(cartaoPersistido.get()).build();

		return Optional.ofNullable(biometriaRepository.save(novaBiometria));
	}

	@Override
	public Optional<Biometria> obter(Long idCartao, Long idBiometria) {
		Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
		List<Biometria> biometriaAssociada = cartao.get().getBiometria().stream()
				.filter(b -> b.getId().equals(idBiometria)).collect(Collectors.toList());
		if (biometriaAssociada.size() > 0) {
			return biometriaRepository.findById(idBiometria);
		}
		return Optional.empty();
	}

}
