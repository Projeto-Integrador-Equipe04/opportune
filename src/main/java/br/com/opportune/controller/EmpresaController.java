package br.com.opportune.controller;

import java.util.List;
import java.util.Optional;

import br.com.opportune.model.EmpresaLogin;
import br.com.opportune.service.EmpresaService;
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

import br.com.opportune.model.Empresa;
import br.com.opportune.repository.EmpresaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> getAll() {
        return ResponseEntity.ok(empresaRepository.findAll());
    }
    
    @GetMapping("/{id}")
   public ResponseEntity<Empresa> getById(@PathVariable Long id) {
    	return empresaRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    	
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Optional<Empresa>> post(@Valid @RequestBody Empresa empresa) {
        return ResponseEntity.status(HttpStatus.CREATED)
        		.body(empresaService.cadastrarUsuario(empresa));
    }

    @PostMapping("/logar")
    public ResponseEntity<EmpresaLogin> autenticarEmpresa(@RequestBody Optional<EmpresaLogin> empresaLogin){

        return empresaService.autenticarUsuario(empresaLogin)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


    @PutMapping("/atualizar")
    public ResponseEntity<Empresa> put(@Valid @RequestBody Empresa empresa) {
        return empresaRepository.findById(empresa.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                		.body(empresaRepository.save(empresa)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Empresa> usuario = empresaRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }   
        empresaRepository.deleteById(id);
    }
    
    
    
}
