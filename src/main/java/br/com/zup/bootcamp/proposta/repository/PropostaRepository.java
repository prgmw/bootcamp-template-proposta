package br.com.zup.bootcamp.proposta.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zup.bootcamp.proposta.domain.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	@Query("Select p From Proposta p WHERE p.documento=?1")
	public Collection<Proposta> obterPropostaPorDocumento(String documento);

	@Query("SELECT p FROM Proposta p LEFT JOIN p.emissao e WHERE e.id IS NULL AND p.status = 'ELEGIVEL'")
	public Collection<Proposta> obterPropostasSemEmissao();

}
