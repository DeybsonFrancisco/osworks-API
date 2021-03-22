package com.deybson.osworks.api.model;

import java.time.OffsetDateTime;

public class ComentarioRepresantation {

	private String descricao;
	private String ordemServicoId;
	private OffsetDateTime dataEnvio;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getOrdemServicoId() {
		return ordemServicoId;
	}
	public void setOrdemServicoId(String ordemServicoId) {
		this.ordemServicoId = ordemServicoId;
	}
	public OffsetDateTime getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(OffsetDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	
}
