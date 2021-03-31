package com.deybson.osworks.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deybson.osworks.api.event.CreateResourceEvent;
import com.deybson.osworks.api.model.FuncionarioInput;
import com.deybson.osworks.api.model.FuncionarioRepresantation;
import com.deybson.osworks.domain.model.Funcionario;
import com.deybson.osworks.domain.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<FuncionarioRepresantation> save(@Valid @RequestBody FuncionarioInput funcionarioInput, HttpServletResponse response){
		Funcionario funcionarioSave = service.save(toEntity(funcionarioInput));
		publisher.publishEvent(new CreateResourceEvent(this, response, "id", funcionarioSave.getId()));
		return ResponseEntity.status(201).body(toModel(funcionarioSave));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.remove(id);
		return ResponseEntity.status(200).build();
	}
	
	private Funcionario toEntity(FuncionarioInput funcionarioInput) {
		return modelMapper.map(funcionarioInput, Funcionario.class);
	}
	private FuncionarioRepresantation toModel(Funcionario funcionario) {
		return modelMapper.map(funcionario, FuncionarioRepresantation.class);
	}
}
