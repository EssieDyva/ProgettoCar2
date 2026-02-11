package com.betacom.car.models;

public class Categoria {
	private Integer idCategoria;
	private String descrizione;
	
	public Categoria(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public Categoria(Integer idColore, String descrizione) {
		super();
		this.idCategoria = idColore;
		this.descrizione = descrizione;
	}

	public Integer getIdColore() {
		return idCategoria;
	}
	public void setIdColore(Integer idColore) {
		this.idCategoria = idColore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Colore [idColore=" + idCategoria + ", descrizione=" + descrizione + "]";
	}
	
}
