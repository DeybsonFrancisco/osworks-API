package com.deybson.osworks.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class FuncionarioInput {

	@NotBlank
	@NotNull
	private String nome;
	
	@NotBlank
	@NotNull
	private String cargo;
	
	@NotBlank
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
