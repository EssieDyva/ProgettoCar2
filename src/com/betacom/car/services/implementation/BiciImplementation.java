package com.betacom.car.services.implementation;

import java.util.Map;

import com.betacom.car.models.Bici;
import com.betacom.car.models.Veicoli;
import com.betacom.car.services.Abstract.AbstractVeicoloImp;

public class BiciImplementation extends AbstractVeicoloImp{

	@Override
	protected Veicoli creaVeicolo(Integer id, String tipoVeicolo, Integer numeroRuote, String tipoAlimentazione, String categoria, String colore, String marca, Integer annoProduzione, String modello, Map<String, String> mappa) {

		System.out.println("inizio crea veicolo bici");

		try {
			Integer numeroMarce=Integer.parseInt(mappa.get("numeroMarce"));
			String tipoFreno = mappa.get("tipoFreno");
			String tipoSospensione = mappa.get("tipoSospensione");
			Boolean isPieghevole = Boolean.parseBoolean("isPieghevole");

			if(numeroRuote.intValue()  <2 || numeroRuote.intValue()>3) {
				System.out.println("Errore: Numero ruote non valido");
				return null;
			}

			return new Bici(id, tipoVeicolo, numeroRuote, tipoAlimentazione, categoria, colore, marca, annoProduzione, modello, numeroMarce, tipoFreno, tipoSospensione, isPieghevole);


		} catch (Exception e) {
			System.out.println("Errore nei dati specifici della Bici: " + e.getMessage());
			return null;
		}
	}
}