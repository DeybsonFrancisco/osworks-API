package com.deybson.osworks.api.controller;


import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deybson.osworks.api.event.CreateResourceEvent;
import com.deybson.osworks.domain.model.Cliente;
import com.deybson.osworks.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<Page<Cliente>> list(@RequestParam(value = "lines", defaultValue = "10") Integer lines,
			@RequestParam(value = "pages", defaultValue = "0") Integer page,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "dir", defaultValue = "ASC") String dir) {

		Pageable pagination = PageRequest.of(page, lines, Direction.valueOf(dir), orderBy);

		Page<Cliente> lista = service.findAll(pagination).get();

		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Optional<Cliente> cliente = service.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.status(200).body(cliente.get());
		}
		return ResponseEntity.status(404).build();
	}

	@GetMapping("/name/{nome}")
	public ResponseEntity<Cliente> findByName(@PathVariable String nome) {
		Optional<Cliente> cliente = service.findByName(nome);
		if (cliente.isPresent()) {
			return ResponseEntity.status(200).body(cliente.get());
		}
		return ResponseEntity.status(404).build();
	}

	@PostMapping
	public ResponseEntity<Cliente> save(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSave = service.save(cliente);
		publisher.publishEvent(new CreateResourceEvent(this, response, "id", clienteSave.getId()));
		return ResponseEntity.status(201).body(clienteSave);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteUpdate = service.update(id, cliente);
		if (clienteUpdate != null) {
			return ResponseEntity.status(200).body(clienteUpdate);
		}
		return ResponseEntity.status(404).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.remove(id);
		return ResponseEntity.status(403).build();
	}

}
