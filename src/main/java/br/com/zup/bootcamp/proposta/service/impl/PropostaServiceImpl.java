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
import br.com.zup.bootcamp.proposta.converter.mapper.PropostaMapper;
import br.com.zup.bootcamp.proposta.domain.model.Restricao;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.exception.ApiErroException;
import br.com.zup.bootcamp.proposta.gateway.dto.input.PropostaInput;
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

	@Autowired
	private PropostaMapper propostaMapper;

	@Transactional
	public Optional<Proposta> obterProposta(Long id) {
		return propostaRepository.findById(id);
	}

	@Transactional
	public Optional<Proposta> criarProposta(PropostaInput propostaDTO) {
		Proposta proposta = propostaMapper.propostaDTOToProposta(propostaDTO);
		if (propostaRepository.obterPropostaPorDocumento(propostaDTO.getDocumento()).isPresent()) {
			logger.error(mensagem.get("documento.existente"), proposta.getDocumento());
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageFormat.format(mensagem.get("documento.existente"), proposta.getDocumento()));
		}
		
		propostaRepository.saveAndFlush(proposta);
		
		Restricao resultadoAvaliacao = avaliacaoService.obterAvaliacaoRisco(Optional.of(proposta));
		proposta.definirStatus(Optional.ofNullable(resultadoAvaliacao));
		
		return Optional.ofNullable(propostaRepository.save(proposta));
	}

}
