package br.com.opportune.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.opportune.model.Empresa;
import br.com.opportune.model.EmpresaLogin;
import br.com.opportune.repository.EmpresaRepository;
import br.com.opportune.security.JwtService;



@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

	public Optional<Empresa> cadastrarUsuario(Empresa empresa) {

		if (empresaRepository.findByEmail(empresa.getEmail()).isPresent())
			return Optional.empty();

		empresa.setSenha(criptografarSenha(empresa.getSenha()));

		return Optional.of(empresaRepository.save(empresa));
	
	}

	public Optional<Empresa> atualizarUsuario(Empresa empresa) {
		
		if(empresaRepository.findById(empresa.getId()).isPresent()) {

			Optional<Empresa> buscaUsuario = empresaRepository.findByEmail(empresa.getEmail());

			if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != empresa.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			empresa.setSenha(criptografarSenha(empresa.getSenha()));

			return Optional.ofNullable(empresaRepository.save(empresa));
			
		}

		return Optional.empty();
	
	}	

	public Optional<EmpresaLogin> autenticarUsuario(Optional<EmpresaLogin> empresaLogin) {
        
        // Gera o Objeto de autenticação
		var credenciais = new UsernamePasswordAuthenticationToken(empresaLogin.get().getEmail(), empresaLogin.get().getSenha());
		
        // Autentica o Usuario
		Authentication authentication = authenticationManager.authenticate(credenciais);
        
        // Se a autenticação foi efetuada com sucesso
		if (authentication.isAuthenticated()) {

            // Busca os dados do usuário
			Optional<Empresa> empresa = empresaRepository.findByEmail(empresaLogin.get().getEmail());

            // Se o usuário foi encontrado
			if (empresa.isPresent()) {

                // Preenche o Objeto usuarioLogin com os dados encontrados 
				empresaLogin.get().setId(empresa.get().getId());
				empresaLogin.get().setNome(empresa.get().getNome());
				empresaLogin.get().setCnpj(empresa.get().getCpnj());
				empresaLogin.get().setPlano(empresa.get().getPlano());
				empresaLogin.get().setData(empresa.get().getData());
				empresaLogin.get().setToken(gerarToken(empresaLogin.get().getEmail()));
				empresaLogin.get().setSenha("");
				
                 // Retorna o Objeto preenchido
			   return empresaLogin;
			
			}

        } 
            
		return Optional.empty();

    }

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);

	}

	private String gerarToken(String empresa) {
		return "Bearer " + jwtService.generateToken(empresa);
	}

}