package br.com.zup.bootcamp.proposta.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zup.bootcamp.proposta.domain.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	@Query("Select p From Proposta p WHERE p.documento=?1")
	public Optional<Collection<Proposta>> obterPropostaPorDocumento(String documento);

}
