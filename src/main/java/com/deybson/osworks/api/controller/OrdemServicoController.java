package com.deybson.osworks.api.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.deybson.osworks.api.model.OrdemServicoInput;
import com.deybson.osworks.api.model.OrdemServicoRepresantation;
import com.deybson.osworks.domain.model.OrdemServico;
import com.deybson.osworks.domain.model.StatusOrdemServico;
import com.deybson.osworks.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordem-servicos")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService service;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<Page<OrdemServicoRepresantation>> list(
			@RequestParam(name="lines", defaultValue="10") int lines,
			@RequestParam(name="page", defaultValue="0") int pages,
			@RequestParam(name="orderBy", defaultValue="id")String order,
			@RequestParam(name="dir", defaultValue="ASC") String direction){
		
		Pageable pagenationQuery = PageRequest.of(pages, lines, Direction.valueOf(direction), order);
		Page<OrdemServicoRepresantation> page = new PageImpl<>(service.findAll(pagenationQuery).get().stream().map(ordemservico -> toModel(ordemservico)).collect(Collectors.toList()));
		return ResponseEntity.status(200).body(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdemServicoRepresantation> findById(@PathVariable Long id){
		OrdemServicoRepresantation ordemServicoRepresantation = toModel(service.findById(id).get());
		return ResponseEntity.status(200).body(ordemServicoRepresantation);
		
	}
	
	@PostMapping
	public ResponseEntity<OrdemServicoRepresantation> save(@Valid @RequestBody OrdemServicoInput ordemServicoInput){
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		return ResponseEntity.status(201).body(toModel(service.save(ordemServico)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrdemServicoRepresantation> update(@Valid @RequestBody OrdemServicoInput ordemServicoInput, @PathVariable Long id){
		OrdemServico ordemServico = toEntity(ordemServicoInput);
		return ResponseEntity.status(200).body(toModel(service.update(id, ordemServico)));
	}
	@PutMapping("/{id}/{status}")
	public ResponseEntity<OrdemServicoRepresantation> updateStatusOrdemServico(
			@PathVariable Long id,
			@PathVariable String status){
		
		String statusUpperCase = status.toUpperCase();
		return ResponseEntity.status(200).body(toModel(service.updateStatus(id, StatusOrdemServico.valueOf(statusUpperCase))));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id){
		service.remove(id);
		return ResponseEntity.status(403).build();
	}
	
	private OrdemServicoRepresantation toModel(OrdemServico ordemServico) {
		return  modelMapper
				.map(ordemServico, OrdemServicoRepresantation.class);
	}
	/*private List<OrdemServicoRepresantation> toCollectionModel(List<OrdemServico> list){
		return list.stream()
				.map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());
	}*/
	
	private OrdemServico toEntity(OrdemServicoInput ordemServicoInput) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
		
	}
}
