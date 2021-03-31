package com.deybson.osworks.domain.model.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.deybson.osworks.domain.model.Funcionario;

public class UsuarioSistema extends User{
	
	private static final long serialVersionUID = 1L;
		
	private Funcionario usuario;

	public UsuarioSistema(Funcionario funcionario, Collection<? extends GrantedAuthority> authorities) {
		super(funcionario.getLogin(), funcionario.getSenha(), authorities);
		usuario = funcionario;
	}

	/**
	 * 
	 */


	
}
