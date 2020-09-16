package br.com.zup.bootcamp.proposta.service.impl;

import java.text.MessageFormat;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.zup.bootcamp.proposta.domain.data.PropostaDTO;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.exception.ApiErroException;
import br.com.zup.bootcamp.proposta.infrastructure.configuration.MensagemParametrizada;
import br.com.zup.bootcamp.proposta.infrastructure.mapper.PropostaMapper;
import br.com.zup.bootcamp.proposta.repository.PropostaRepository;
import br.com.zup.bootcamp.proposta.service.IPropostaService;

@Service
public class PropostaService implements IPropostaService {

	private final Logger logger = LoggerFactory.getLogger(PropostaService.class);

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private MensagemParametrizada mensagem;

	@Autowired
	private PropostaMapper propostaMapper;

	public Optional<Proposta> obterProposta(Long id) {
		return propostaRepository.findById(id);
	}

	public Optional<Proposta> criarProposta(PropostaDTO propostaDTO) {
		Proposta proposta = propostaMapper.propostaDTOToProposta(propostaDTO);
		if (propostaRepository.obterPropostaPorDocumento(propostaDTO.getDocumento()).isPresent()) {
			logger.error(mensagem.get("documento.existente"), proposta.getDocumento());
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageFormat.format(mensagem.get("documento.existente"), proposta.getDocumento()));
		}
		return Optional.ofNullable(propostaRepository.save(proposta));
	}

}
