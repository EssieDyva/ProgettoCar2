package com.betacom.car.models;

public class Marca {
	private Integer idMarca;
	private String descrizione;
	
	public Marca(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public Marca(Integer idColore, String descrizione) {
		super();
		this.idMarca = idColore;
		this.descrizione = descrizione;
	}

	public Integer getIdColore() {
		return idMarca;
	}
	public void setIdColore(Integer idColore) {
		this.idMarca = idColore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Colore [idColore=" + idMarca + ", descrizione=" + descrizione + "]";
	}
	
}
