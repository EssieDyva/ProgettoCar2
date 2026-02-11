package com.betacom.car.models;

public class Sospensioni {
	private Integer idSospensioni;
	private String descrizione;
	
	public Sospensioni(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public Sospensioni(Integer idSospensioni, String descrizione) {
		super();
		this.idSospensioni = idSospensioni;
		this.descrizione = descrizione;
	}

	public Integer getIdSospensioni() {
		return idSospensioni;
	}
	public void setIdSospensioni(Integer idSospensioni) {
		this.idSospensioni = idSospensioni;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Colore [idSospensioni=" + idSospensioni + ", descrizione=" + descrizione + "]";
	}
	
}
