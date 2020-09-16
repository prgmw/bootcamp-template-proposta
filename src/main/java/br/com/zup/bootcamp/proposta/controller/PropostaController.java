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

import br.com.zup.bootcamp.proposta.domain.data.PropostaDTO;
import br.com.zup.bootcamp.proposta.domain.model.Proposta;
import br.com.zup.bootcamp.proposta.service.IPropostaService;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private IPropostaService propostaService;

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable(name = "id") Long id, HttpServletResponse response) {
		Optional<Proposta> proposta = propostaService.obterProposta(id);
		if (!proposta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(proposta.get());
	}

	@PostMapping
	public ResponseEntity<Proposta> criar(@Valid @RequestBody PropostaDTO propostaDTO, HttpServletResponse response) {
		Optional<Proposta> proposta = propostaService.criarProposta(propostaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(proposta.get().getId())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(proposta.get());
	}

}
