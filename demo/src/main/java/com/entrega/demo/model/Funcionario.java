package com.entrega.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Funcionario extends Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false, length=10)
	@NotEmpty(message = "Senha é obrigatoria")
	private String senha;
	
	@Column(length=20)
	@NotEmpty(message = "Cargo é obrigatorio")
	private String cargo;
	
	private String filial;
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getFilial() {
		return filial;
	}
	public void setFilial(String filial) {
		this.filial = filial;
	}
	
}
