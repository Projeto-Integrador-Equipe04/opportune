package br.com.opportune.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.opportune.model.Plano;
import br.com.opportune.repository.PlanoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plano")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanoController {
	
	@Autowired
	private PlanoRepository planoRepository;
	
	@GetMapping
	public ResponseEntity<List<Plano>> getAll(){
		return ResponseEntity.ok(planoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Plano> getById(@PathVariable Long id){
		return planoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<Plano> post(@Valid @RequestBody Plano plano){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(planoRepository.save(plano));
	}
	
	@PutMapping
	public ResponseEntity<Plano> put(@Valid @RequestBody Plano plano){
		return planoRepository.findById(plano.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK)
						.body(planoRepository.save(plano)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PutMapping("/status")
	public void validar(@Valid @RequestBody Plano plano){
		planoRepository.save(plano);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Plano> oportunidade = planoRepository.findById(id);
		
		if(oportunidade.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		planoRepository.deleteById(id);
	}
}
