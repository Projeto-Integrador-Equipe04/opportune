package br.com.opportune.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.opportune.model.Cliente;

public interface  ClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
