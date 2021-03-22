package com.deybson.osworks.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deybson.osworks.api.model.ComentarioInput;
import com.deybson.osworks.api.model.ComentarioRepresantation;
import com.deybson.osworks.domain.model.Comentario;
import com.deybson.osworks.domain.service.ComentarioService;

@RestController
@RequestMapping("ordem-servicos/{idOrdemServico}/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<ComentarioRepresantation> save(@PathVariable Long idOrdemServico,
			@RequestBody ComentarioInput comentarioInput){
		Comentario comentario = toEntity(comentarioInput);
		return ResponseEntity.status(201).body(toModel(service.save(idOrdemServico, comentario)));
	}
	
	@DeleteMapping("{idComentario}")
	public ResponseEntity<?> remove(@PathVariable Long idOrdemServico, @PathVariable Long idComentario){
		service.remove(idComentario);
		return ResponseEntity.status(403).build();
	}
	
	
	private Comentario toEntity(ComentarioInput comentarioInput) {
		return modelMapper.map(comentarioInput, Comentario.class);
	}
	
	private ComentarioRepresantation toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioRepresantation.class);
	}
}
