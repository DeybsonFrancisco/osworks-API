package com.deybson.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;



import com.deybson.osworks.domain.model.StatusOrdemServico;

public class OrdemServicoRepresantation {

	private Long Id;
	private  String clienteNome;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getclienteNome() {
		return clienteNome;
	}

	public void setclienteNome(String nome) {
		this.clienteNome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descrcao) {
		this.descricao = descrcao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	
}
