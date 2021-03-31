package com.deybson.osworks.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deybson.osworks.domain.exception.NegocioException;
import com.deybson.osworks.domain.model.Funcionario;
import com.deybson.osworks.domain.repository.FuncionarioRepository;
import com.deybson.osworks.domain.service.FuncionarioService;

@Service
public class FucionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Override
	public Funcionario save(Funcionario funcionario) {
		funcionario.setLogin(funcionario.getCargo()
											.toString()
											.concat("-")
											.concat(funcionario.getNome()));
		System.out.println(funcionario.getSenha());
		funcionario.setSenha(encodeSenha(funcionario.getSenha()));
		return repository.save(funcionario);
	}

	@Override
	public void remove(Long id) {
		if (!repository.existsById(id)) {
			throw new NegocioException("Funcionario não existe");
		}
		
		repository.deleteById(id);
	}
	
	private String encodeSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncode = encoder.encode(senha);
		return senhaEncode;
	}


}
