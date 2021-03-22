package com.deybson.osworks.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deybson.osworks.domain.exception.NegocioException;
import com.deybson.osworks.domain.model.Cliente;
import com.deybson.osworks.domain.repository.ClienteRepository;
import com.deybson.osworks.domain.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Optional<List<Cliente>> findAll() {
		return Optional.of(repository.findAll());
	}

	public Optional<Page<Cliente>> findAll(Pageable page) {
		return Optional.of(repository.findAll(page));
	}

	public Optional<Cliente> findById(Long id) {
		return repository.findById(id);
	}

	public Cliente save(Cliente cliente) {
		Optional<Cliente> clienteExist = repository.findByNome(cliente.getNome());
		if (clienteExist.isPresent()) {
			throw new NegocioException("Já existe um cliente cadastrado com esse nome");
		} else {
			return repository.save(cliente);
		}

	}

	public Cliente update(Long id, Cliente cliente) {
		if (repository.existsById(id)) {
			Optional<Cliente> clienteSave = repository.findById(id);
			BeanUtils.copyProperties(cliente, clienteSave.get(), "id");
			return repository.save(clienteSave.get());
		}
		return null;
	}

	public void remove(Long id) {
		if (!repository.existsById(id))
			throw new NegocioException("Cliente não existe");

		repository.deleteById(id);
	}

	public Optional<Cliente> findByName(String nome) {
		return repository.findByNome(nome);
	}

}
