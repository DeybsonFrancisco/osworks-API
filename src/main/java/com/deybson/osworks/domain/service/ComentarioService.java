package com.deybson.osworks.domain.service;

import com.deybson.osworks.domain.model.Comentario;

public interface ComentarioService {

	public Comentario save(Long idOrdemServico, Comentario comentario);
	
	public void remove(Long idComentario);
}
