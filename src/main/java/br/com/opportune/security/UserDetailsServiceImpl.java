package br.com.opportune.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.opportune.model.Empresa;
import br.com.opportune.repository.EmpresaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Empresa> empresa = empresaRepository.findByEmail(userName);

		if (empresa.isPresent())
			return new UserDetailsImpl(empresa.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);

	}
}
