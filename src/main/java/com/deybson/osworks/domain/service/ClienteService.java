package com.deybson.osworks.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.deybson.osworks.domain.model.Cliente;


public interface ClienteService{

	Optional<List<Cliente>> findAll();

	Optional<Page<Cliente>> findAll(Pageable page);
	
	Optional<Cliente> findById(Long id);
	
	Optional<Cliente> findByName(String nome);
	
	Cliente save(Cliente cliente);
	
	Cliente update(Long id, Cliente cliente);
	
	void remove(Long id);

}
