package com.betacom.car.models;

public class TipoAlimentazione {
	private Integer idTipoAlimentazione;
	private String descrizione;
	
	public TipoAlimentazione(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public TipoAlimentazione(Integer idColore, String descrizione) {
		super();
		this.idTipoAlimentazione = idColore;
		this.descrizione = descrizione;
	}

	public Integer getIdColore() {
		return idTipoAlimentazione;
	}
	public void setIdColore(Integer idColore) {
		this.idTipoAlimentazione = idColore;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Colore [idColore=" + idTipoAlimentazione + ", descrizione=" + descrizione + "]";
	}
	
}
