package com.deybson.osworks.domain.service;

import com.deybson.osworks.domain.model.Funcionario;

public interface FuncionarioService {

	Funcionario save(Funcionario funcionario);
	
	void remove(Long id);
}
