package com.betacom.car.models;

public class Moto extends Veicoli{

	
	private Integer idMoto;
	private String targa;		// deve essere univoco e più corto rispetto a quello per macchina
	private Integer cc;
	
	public Moto(Integer id,String tipoVeicolo, Integer numeroRuote, Integer idAlimentazione, Integer idCategoria,
			Integer idColore, Integer idMarca, Integer annoProduzione, String modello, String targa, Integer cc) {
		super(id, tipoVeicolo, numeroRuote, idAlimentazione, idCategoria, idColore, idMarca, annoProduzione, modello);
		this.targa = targa;
		this.cc = cc;
	}

	public Moto(Integer idMoto, String targa, Integer cc) {
		super();
		this.idMoto = idMoto;
		this.targa = targa;
		this.cc = cc;
	}

	public Integer getIdMoto() {
		return idMoto;
	}



	public void setIdMoto(Integer idMoto) {
		this.idMoto = idMoto;
	}



	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

	@Override
	public String toString() {
		return "Moto [targa=" + targa + ", cc=" + cc + ", getId()=" + getId() + ", getTipoVeicolo()=" + getTipoVeicolo()
				+ ", getNumeroRuote()=" + getNumeroRuote() + ", getTipoAlimentazione()=" + getIdTipoAlimentazione()
				+ ", getCategoria()=" + getIdCategoria() + ", getColore()=" + getIdColore() + ", getMarca()=" + getIdMarca()
				+ ", getAnnoProduzione()=" + getAnnoProduzione() + ", getModello()=" + getModello() + "]";
	}	
	
	
}
