package com.deybson.osworks.domain.service.impl.security;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deybson.osworks.domain.model.Funcionario;
import com.deybson.osworks.domain.model.security.UsuarioSistema;
import com.deybson.osworks.domain.repository.FuncionarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private FuncionarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		System.out.println("kfkfkfkfkfkjdjdhfjh");
		Funcionario funcionario = repository.findByLogin(login).get();
		System.out.println(funcionario.getNome());
		System.out.println(funcionario.getSenha());
		return new UsuarioSistema(funcionario, new HashSet<>());
	}

}
