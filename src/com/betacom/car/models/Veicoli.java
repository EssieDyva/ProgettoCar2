package com.betacom.car.models;

public class Veicoli {
	private Integer id; 					// id univoco del record
	private String tipoVeicolo;
	private Integer numeroRuote; 		// 1-4 dipende del tipo veicolo
	private Integer idTipoAlimentazione; 	// benzina, diesel, elettrica, ibrida
	private Integer idCategoria;			// strada, fuori strada, suv, città, sportiva
	private Integer idColore;				// qualsiasi colore
	private Integer idMarca;				// fiat, ford, lancia, renault, yamaha, bianchi, ecc...
	private Integer annoProduzione;
	private String modello;				// fiat 500, lancia ypsilon, renault renegade, ecc...
	
	public Veicoli() {}

    public Veicoli(String tipoVeicolo, Integer numeroRuote, Integer idTipoAlimentazione, Integer idCategoria,
			Integer idColore, Integer idMarca, Integer annoProduzione, String modello) {
		super();
		this.tipoVeicolo = tipoVeicolo;
		this.numeroRuote = numeroRuote;
		this.idTipoAlimentazione = idTipoAlimentazione;
		this.idCategoria = idCategoria;
		this.idColore = idColore;
		this.idMarca = idMarca;
		this.annoProduzione = annoProduzione;
		this.modello = modello;
	}

	public Veicoli(Integer id, String tipoVeicolo, Integer numeroRuote, Integer idTipoAlimentazione,
			Integer idCategoria, Integer idColore, Integer idMarca, Integer annoProduzione, String modello) {
		super();
		this.id = id;
		this.tipoVeicolo = tipoVeicolo;
		this.numeroRuote = numeroRuote;
		this.idTipoAlimentazione = idTipoAlimentazione;
		this.idCategoria = idCategoria;
		this.idColore = idColore;
		this.idMarca = idMarca;
		this.annoProduzione = annoProduzione;
		this.modello = modello;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoVeicolo() {
        return tipoVeicolo;
    }

    public void setTipoVeicolo(String tipoVeicolo) {
        this.tipoVeicolo = tipoVeicolo;
    }

    public Integer getNumeroRuote() {
        return numeroRuote;
    }

    public void setNumeroRuote(Integer numeroRuote) {
        this.numeroRuote = numeroRuote;
    }

    public Integer getIdTipoAlimentazione() {
        return idTipoAlimentazione;
    }

    public void setIdTipoAlimentazione(Integer idTipoAlimentazione) {
        this.idTipoAlimentazione = idTipoAlimentazione;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdColore() {
        return idColore;
    }

    public void setIdColore(Integer idColore) {
        this.idColore = idColore;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getAnnoProduzione() {
        return annoProduzione;
    }

    public void setAnnoProduzione(Integer annoProduzione) {
        this.annoProduzione = annoProduzione;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

	@Override
	public String toString() {
		return "Veicoli [id=" + id + ", tipoVeicolo=" + tipoVeicolo + ", numeroRuote=" + numeroRuote
				+ ", idTipoAlimentazione=" + idTipoAlimentazione + ", idCategoria=" + idCategoria + ", idColore="
				+ idColore + ", idMarca=" + idMarca + ", annoProduzione=" + annoProduzione + ", modello=" + modello
				+ "]";
	}
	
}
