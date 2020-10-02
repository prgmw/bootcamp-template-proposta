package br.com.zup.bootcamp.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.bootcamp.proposta.domain.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
