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
import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.exception.ApiErroException;
import br.com.zup.bootcamp.proposta.repository.BiometriaRepository;
import br.com.zup.bootcamp.proposta.repository.PropostaRepository;
import br.com.zup.bootcamp.proposta.service.IBiometriaService;

@Service
public class BiometriaServiceImpl implements IBiometriaService {

	private final Logger logger = LoggerFactory.getLogger(BiometriaServiceImpl.class);

	@Autowired
	private BiometriaRepository biometriaRepository;

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private MensagemParametrizada mensagem;

	@Override
	public Optional<Biometria> cadastrar(Long idProposta, Biometria biometria) {
		Optional<Proposta> propostaSalva = propostaRepository.findById(idProposta);
		if (!propostaSalva.isPresent()) {
			logger.error(mensagem.get("proposta.invalida"), idProposta);
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageFormat.format(mensagem.get("proposta.invalida"), idProposta));
		}

		List<Biometria> biometriaAssociada = propostaSalva.get().getBiometria().stream()
				.filter(b -> b.getIdentificador().equals(biometria.getIdentificador())).collect(Collectors.toList());

		if (biometriaAssociada.size() > 0) {
			logger.error(mensagem.get("proposta.biometria.invalida"), idProposta);
			throw new ApiErroException(HttpStatus.BAD_REQUEST,
					MessageFormat.format(mensagem.get("proposta.biometria.invalida"), idProposta));
		}

		Biometria novaBiometria = Biometria.builder()
				.dataCriacao(LocalDateTime.now())
				.identificador(biometria.getIdentificador())
				.proposta(propostaSalva.get()).build();

		return Optional.ofNullable(biometriaRepository.save(novaBiometria));
	}

	@Override
	public Optional<Biometria> obter(Long idProposta, Long idBiometria) {
		Optional<Proposta> proposta = propostaRepository.findById(idProposta);
		List<Biometria> biometriaAssociada = proposta.get().getBiometria().stream()
				.filter(b -> b.getId().equals(idBiometria)).collect(Collectors.toList());
		if (biometriaAssociada.size() > 0) {
			return biometriaRepository.findById(idBiometria);
		}
		return Optional.empty();
	}

}
