package br.com.zup.bootcamp.proposta.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.bootcamp.proposta.domain.model.Cartao;
import br.com.zup.bootcamp.proposta.repository.CartaoRepository;
import br.com.zup.bootcamp.proposta.service.ICartaoService;

@Service
public class CartaoServiceImpl implements ICartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Transactional
	public Optional<Cartao> obter(Long id) {
		return cartaoRepository.findById(id);
	}

}
