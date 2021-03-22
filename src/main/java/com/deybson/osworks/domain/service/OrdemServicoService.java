package com.deybson.osworks.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.deybson.osworks.domain.model.OrdemServico;
import com.deybson.osworks.domain.model.StatusOrdemServico;

public interface OrdemServicoService {

	Optional<Page<OrdemServico>> findAll(Pageable pageable);

	Optional<OrdemServico> findById(Long id);

	OrdemServico save(OrdemServico ordemservico);

	OrdemServico update(Long id, OrdemServico ordemServico);
	
	OrdemServico updateStatus(Long id, StatusOrdemServico status);

	void remove(Long id);

}
