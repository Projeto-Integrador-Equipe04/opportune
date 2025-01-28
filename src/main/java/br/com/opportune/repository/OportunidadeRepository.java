package br.com.opportune.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.opportune.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long>{
	
}
