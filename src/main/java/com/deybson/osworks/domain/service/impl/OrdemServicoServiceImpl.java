package com.deybson.osworks.domain.service.impl;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deybson.osworks.domain.exception.NegocioException;
import com.deybson.osworks.domain.model.Cliente;
import com.deybson.osworks.domain.model.OrdemServico;
import com.deybson.osworks.domain.model.StatusOrdemServico;
import com.deybson.osworks.domain.repository.ClienteRepository;
import com.deybson.osworks.domain.repository.OrdemServicoRepository;
import com.deybson.osworks.domain.service.OrdemServicoService;

@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	public Optional<Page<OrdemServico>> findAll(Pageable page) {
		return Optional.of(repository.findAll(page));
	}

	public Optional<OrdemServico> findById(Long id) {
		OrdemServico ordemServico = repository.findById(id)
				.orElseThrow(() -> new NegocioException("Ordem de serviço não encontrada"));
		return Optional.of(ordemServico);
	}

	public OrdemServico save(OrdemServico ordemservico) {
		Cliente cliente = clienteRepository.findById(ordemservico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("ID cliente não encontrado"));

		ordemservico.setCliente(cliente);
		ordemservico.setStatus(StatusOrdemServico.ABERTA);
		ordemservico.setDataAbertura(OffsetDateTime.now());
		return repository.save(ordemservico);
	}

	public OrdemServico update(Long id, OrdemServico ordemServico) {
		if (!repository.existsById(id)) {
			throw new NegocioException("Ordem de servico não encontrada");
		}
		OrdemServico ordemServicoSave = repository.findById(id).get();
		BeanUtils.copyProperties(ordemServico, ordemServicoSave, "id", "dataAbertura", "dataFinalizacao", "status");
		return repository.save(ordemServicoSave);
	}

	@Override
	public OrdemServico updateStatus(Long id, StatusOrdemServico status) {
		OrdemServico ordemServico = repository.findById(id)
				.orElseThrow(() -> new NegocioException("Ordem de serviço não encontrada"));

		if (ordemServico.getStatus().equals(StatusOrdemServico.FINALIZADA)
				&& !status.equals(StatusOrdemServico.ABERTA)) {
			throw new NegocioException("Ordem de servico ja finalizada");
		}

		ordemServico.setStatus(status);

		if (status.equals(StatusOrdemServico.CANCELADA) || status.equals(StatusOrdemServico.FINALIZADA)) {
			ordemServico.setDataFinalizacao(OffsetDateTime.now());
		} else {
			ordemServico.setDataFinalizacao(null);
		}
		return repository.save(ordemServico);
	}

	public void remove(Long id) {
		if (!repository.existsById(id)) {
			throw new NegocioException("ordem de serviço não encontrada");
		}
		repository.deleteById(id);
	}

}
