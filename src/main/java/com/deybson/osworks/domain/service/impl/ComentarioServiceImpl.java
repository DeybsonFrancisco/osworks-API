package com.deybson.osworks.domain.service.impl;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deybson.osworks.domain.exception.NegocioException;
import com.deybson.osworks.domain.model.Comentario;
import com.deybson.osworks.domain.model.OrdemServico;
import com.deybson.osworks.domain.repository.ComentarioRepository;
import com.deybson.osworks.domain.repository.OrdemServicoRepository;
import com.deybson.osworks.domain.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	private ComentarioRepository repository;

	@Autowired
	private OrdemServicoRepository repositoryOrdemServico;

	@Override
	public Comentario save(Long idOrdemServico, Comentario comentario) {
		OrdemServico ordemServico = repositoryOrdemServico.findById(idOrdemServico)
				.orElseThrow(() -> new NegocioException("ordem de servico não encontrada"));

		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setOrdemServico(ordemServico);
		return repository.save(comentario);
	}

	@Override
	public void remove(Long idComentario) {
		if (!repository.existsById(idComentario))
			throw new NegocioException("Comentario não existe");

		repository.deleteById(idComentario);
	}

}
