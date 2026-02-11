package com.betacom.car.services.Abstract;

import java.util.HashMap;
import java.util.Map;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Veicoli;
import com.betacom.car.services.interfaces.VeicoliInt;
import com.betacom.car.singleton.CarSingleton;

public abstract class AbstractVeicoloImp implements VeicoliInt {

	@Override
	public Veicoli execute(String[] lS) throws VeicoliException {

		System.out.println("Inizio veicoloAbs execute...");

		if (lS == null || lS.length < 2) {
			throw new VeicoliException("Input non valido o riga incompleta");
		}

		Map<String, String> mappa = null;
		mappa = loadMap(lS);

		try {
			Integer id = CarSingleton.getInstance().computeId();

			if (controlParams(mappa)) {  
				return creaVeicolo(id, mappa.get("tipoVeicolo"),
						mappa.get("numeroRuote") != null ? Integer.parseInt(mappa.get("numeroRuote")) : 0,
						mappa.get("tipoAlimentazione"), mappa.get("categoria"), mappa.get("colore"), mappa.get("marca"),
						mappa.get("annoProduzione") != null ? Integer.parseInt(mappa.get("annoProduzione")) : 0,
						mappa.get("modello"), mappa);
			} else {
				return null;
			}

		} catch (NumberFormatException e) {
			throw new VeicoliException("Errore nel formato dei numeri");
		} catch (Exception e) {
			throw new VeicoliException(e.getMessage());
		}
	}

	protected Boolean controlParams(Map<String, String> params) throws Exception {

		try {
			int nRuote = Integer.parseInt(params.get("numeroRuote"));
			if (nRuote == 0) {
				throw new Exception("Ruote " + nRuote + " nRuote sbagliato");
			}
		} catch (NumberFormatException e) {
			throw new Exception("ruote non numeriche");
		}

		try {
			int anno = Integer.parseInt(params.get("annoProduzione"));
			if (anno < 2000 || anno > 2026) {
				throw new Exception("Anno " + anno + " fuori range consentito (2000-2026)");
			}
		} catch (NumberFormatException e) {
			throw new Exception("Anno produzione non numerico");
		}

		CheckParamSingleton(params);
		return true;
	}

	protected Map<String, String> loadMap(String[] lS) {

		Map<String, String> mappa = new HashMap<String, String>();

		for (Integer i = 2; i < lS.length; i++) {
			String[] elem = lS[i].split("=");
			if (elem.length == 2) {
				mappa.put(elem[0].trim(), elem[1].trim());
			}
		}
		return mappa;
	}

	protected void CheckParamSingleton(Map<String, String> params) throws Exception {
		CheckValid("alim", "tipoAlimentazione", params);
		CheckValid("cat", "categoria", params);
		CheckValid("colore", "colore", params);
		CheckValid("marca", "marca", params);
	}

	protected void CheckValid(String s, String stringMap, Map<String, String> params) throws Exception {

		if (!CarSingleton.getInstance().isValidValue(s, params.get(stringMap)))
			throw new Exception(s + " " + params.get("s") + " non valida");
	}

	protected abstract Veicoli creaVeicolo(Integer id, String tipoVeicolo, Integer numeroRuote,
			String tipoAlimentazione, String categoria, String colore, String marca, Integer annoProduzione,
			String modello, Map<String, String> mappa);
	
	
}