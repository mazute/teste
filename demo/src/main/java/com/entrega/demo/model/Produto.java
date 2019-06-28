package com.entrega.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nome;
	private Double estoque;
	private Double quantVendidaCurtoPrazo;
	private Double quantVendidaLongoPrazo;
	private Double custo;
	private boolean visualizar;
	@ManyToOne
	private Medida medida;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getEstoque() {
		return estoque;
	}
	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}
	public Double getQuantVendidaCurtoPrazo() {
		return quantVendidaCurtoPrazo;
	}
	public void setQuantVendidaCurtoPrazo(Double quantVendidaCurtoPrazo) {
		this.quantVendidaCurtoPrazo = quantVendidaCurtoPrazo;
	}
	public Double getQuantVendidaLongoPrazo() {
		return quantVendidaLongoPrazo;
	}
	public void setQuantVendidaLongoPrazo(Double quantVendidaLongoPrazo) {
		this.quantVendidaLongoPrazo = quantVendidaLongoPrazo;
	}
	public Double getCusto() {
		return custo;
	}
	public void setCusto(Double custo) {
		this.custo = custo;
	}
	public boolean isVisualizar() {
		return visualizar;
	}
	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}
	public Medida getMedida() {
		return medida;
	}
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
	
}
