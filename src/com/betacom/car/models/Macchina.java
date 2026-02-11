package com.betacom.car.models;

public class Macchina extends Veicoli{
	
	private Integer idMacchina;
	private Integer numeroPorte;		// 1 - n
	private String targa;			// univoca
	private Integer cc;				// 50, 100, 125, 150
	
	public Macchina() {}
	
	public Macchina(Integer id, String tipoVeicolo, Integer numeroRuote, Integer idAlimentazione, Integer idCategoria,
			Integer idColore, Integer idMarca, Integer annoProduzione, String modello, Integer numeroPorte, String targa, Integer cc) {
		super(id, tipoVeicolo, numeroRuote, idAlimentazione, idCategoria, idColore, idMarca, annoProduzione, modello);
		this.numeroPorte = numeroPorte;
		this.targa = targa;
		this.cc = cc;
	}
	
	public Macchina(Integer idMacchina, Integer numeroPorte, String targa, Integer cc) {
		super();
		this.idMacchina = idMacchina;
		this.numeroPorte = numeroPorte;
		this.targa = targa;
		this.cc = cc;
	}

	public Integer getIdMacchina() {
		return idMacchina;
	}

	public void setIdMacchina(Integer idMacchina) {
		this.idMacchina = idMacchina;
	}

	public Integer getNumeroPorte() {
		return numeroPorte;
	}
	public void setNumeroPorte(Integer numeroPorte) {
		this.numeroPorte = numeroPorte;
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
		return "Macchina [idMacchina=" + idMacchina + ", numeroPorte=" + numeroPorte + ", targa=" + targa + ", cc=" + cc
				+ "]";
	}
	
//	@Override
//	public String generaStringaFiltro() {
//	    return super.generaStringaFiltro() + 
//	           ",numeroPorte=" + numeroPorte + 
//	           ",targa=" + targa + 
//	           ",cc=" + cc;
//	}
}
