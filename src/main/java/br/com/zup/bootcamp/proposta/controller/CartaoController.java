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

import br.com.zup.bootcamp.proposta.converter.mapper.BiometriaMapper;
import br.com.zup.bootcamp.proposta.domain.model.Biometria;
import br.com.zup.bootcamp.proposta.domain.model.Cartao;
import br.com.zup.bootcamp.proposta.gateway.dto.input.BiometriaInput;
import br.com.zup.bootcamp.proposta.service.IBiometriaService;
import br.com.zup.bootcamp.proposta.service.ICartaoService;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

	@Autowired
	private ICartaoService cartaoService;
	
	@Autowired
	private IBiometriaService biometriaService;
	
	@Autowired
	private BiometriaMapper biometriaMapper;

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorCodigo(@PathVariable(name = "id") Long idCartao, HttpServletResponse response) {
		Optional<Cartao> cartao = cartaoService.obter(idCartao);
		if (!cartao.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartao.get());
	}

	@PostMapping("/{cartaoId}/biometria")
	public ResponseEntity<Biometria> criarBiometria(@PathVariable(name = "cartaoId") Long idCartao,
			@Valid @RequestBody BiometriaInput biometriaDTO, HttpServletResponse response) {
		Optional<Cartao> cartao = cartaoService.obter(idCartao);
		if (!cartao.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Optional<Biometria> biometriaPersistida = biometriaService.cadastrar(idCartao,
				biometriaMapper.biometriaDTOToBiometria(biometriaDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{biometriaId}")
				.buildAndExpand(idCartao, biometriaPersistida.get().getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(biometriaPersistida.get());
	}

	@GetMapping("/{cartaoId}/biometria/{biometriaId}")
	public ResponseEntity<?> buscarBiometria(@PathVariable(name = "cartaoId") Long idCartao,
			@PathVariable(name = "biometriaId") Long idBiometria, HttpServletResponse response) {
		Optional<Biometria> biometria = biometriaService.obter(idCartao, idBiometria);
		if (!biometria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(biometria.get());
	}
}
