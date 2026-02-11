package com.betacom.car.services;

import java.util.List;

import com.betacom.car.dao.CategoriaDAO;
import com.betacom.car.dao.TipoAlimentazioneDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Categoria;
import com.betacom.car.models.TipoAlimentazione;

public class ServiceTipoAlimentazione {

	private TipoAlimentazioneDAO dao = new TipoAlimentazioneDAO();
	
	public void executeQuery() throws VeicoliException {
		getAllTipiAlimentazione();
	}
	
	private void getAllTipiAlimentazione() {
		System.out.println(">>>getAllTipiAlimentazione<<<");
		
		try {
			List<TipoAlimentazione> lD = dao.findAll();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
}
