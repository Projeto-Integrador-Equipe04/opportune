package br.com.opportune.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.opportune.model.Oportunidade;
import br.com.opportune.repository.OportunidadeRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/oportunidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OportunidadeController {
	
	@Autowired
	private OportunidadeRepository oportunidadeRepository;
	
	@GetMapping
	public ResponseEntity<List<Oportunidade>> getAll(){
		return ResponseEntity.ok(oportunidadeRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> getById(@PathVariable Long id){
		return oportunidadeRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<Oportunidade> post(@Valid @RequestBody Oportunidade oportunidade){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(oportunidadeRepository.save(oportunidade));
	}
	
	@PutMapping
	public ResponseEntity<Oportunidade> put(@Valid @RequestBody Oportunidade oportunidade){
		return oportunidadeRepository.findById(oportunidade.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK)
						.body(oportunidadeRepository.save(oportunidade)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
		
		if(oportunidade.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		oportunidadeRepository.deleteById(id);
	}
}
