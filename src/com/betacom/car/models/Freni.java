package com.betacom.car.models;

public class Freni {
	private Integer idFreni;
	private String descrizione;
	
	public Freni(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public Freni(Integer idFreni, String descrizione) {
		super();
		this.idFreni = idFreni;
		this.descrizione = descrizione;
	}

	public Integer getIdFreni() {
		return idFreni;
	}
	public void setIdFreni(Integer idFreni) {
		this.idFreni = idFreni;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Colore [idFreni=" + idFreni + ", descrizione=" + descrizione + "]";
	}
	
}
