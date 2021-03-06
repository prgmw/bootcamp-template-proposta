package br.com.zup.bootcamp.proposta.controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.bootcamp.proposta.converter.mapper.PropostaMapper;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.gateway.dto.input.PropostaInput;
import br.com.zup.bootcamp.proposta.service.IPropostaService;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	private IPropostaService propostaService;

	@Autowired
	private PropostaMapper propostaMapper;

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable(name = "id") Long idProposta, HttpServletResponse response) {
		Optional<Proposta> proposta = propostaService.obterProposta(idProposta);
		if (!proposta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(proposta.get());
	}

	@PostMapping
	public ResponseEntity<Proposta> criar(@Valid @RequestBody PropostaInput propostaDTO, HttpServletResponse response) {
		Proposta proposta = propostaMapper.propostaDTOToProposta(propostaDTO);
		Optional<Proposta> propostaSaved = propostaService.criarProposta(proposta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(propostaSaved.get().getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(propostaSaved.get());
	}

}
