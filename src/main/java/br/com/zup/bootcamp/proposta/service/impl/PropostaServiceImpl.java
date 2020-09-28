package br.com.zup.bootcamp.proposta.service.impl;

import java.text.MessageFormat;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.zup.bootcamp.proposta.component.MensagemParametrizada;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.domain.model.Restricao;
import br.com.zup.bootcamp.proposta.exception.ApiErroException;
import br.com.zup.bootcamp.proposta.repository.PropostaRepository;
import br.com.zup.bootcamp.proposta.service.IAvaliacaoRestricaoService;
import br.com.zup.bootcamp.proposta.service.IPropostaService;

@Service
public class PropostaServiceImpl implements IPropostaService {

	private final Logger logger = LoggerFactory.getLogger(PropostaServiceImpl.class);

	@Autowired
	private IAvaliacaoRestricaoService avaliacaoService;

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private MensagemParametrizada mensagem;

	@Transactional
	public Optional<Proposta> obterProposta(Long id) {
		return propostaRepository.findById(id);
	}

	@Transactional
	public Optional<Proposta> criarProposta(Proposta proposta) {
		if (!propostaRepository.obterPropostaPorDocumento(proposta.getDocumento()).isEmpty()) {
			logger.error(mensagem.get("proposta.documento.existente"), proposta.getDocumento());
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageFormat.format(mensagem.get("proposta.documento.existente"), proposta.getDocumento()));
		}

		propostaRepository.save(proposta);

		Restricao resultadoAvaliacao = avaliacaoService.obterAvaliacaoRisco(Optional.of(proposta));
		proposta.definirStatus(Optional.ofNullable(resultadoAvaliacao));

		return Optional.ofNullable(propostaRepository.save(proposta));
	}

}
