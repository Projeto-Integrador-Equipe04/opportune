package br.com.opportune.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.opportune.model.Plano;

public interface PlanoRepository extends JpaRepository<Plano, Long>{
	
}
