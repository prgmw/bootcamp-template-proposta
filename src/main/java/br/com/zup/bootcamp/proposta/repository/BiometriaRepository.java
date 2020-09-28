package br.com.zup.bootcamp.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.bootcamp.proposta.domain.model.Biometria;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, Long> {

}
