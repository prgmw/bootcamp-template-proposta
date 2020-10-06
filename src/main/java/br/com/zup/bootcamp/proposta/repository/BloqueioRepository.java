package br.com.zup.bootcamp.proposta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zup.bootcamp.proposta.domain.model.Bloqueio;

@Repository
public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
	
	@Query("Select b From Bloqueio b WHERE b.notificado = 'N'")
	List<Bloqueio> obterCartoesSemNotificao();

}
