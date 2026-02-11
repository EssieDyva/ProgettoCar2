package com.betacom.car.services;

import java.util.List;

import com.betacom.car.dao.ColoreDAO;
import com.betacom.car.dao.MarcaDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Colore;
import com.betacom.car.models.Marca;

public class ServiceMarca {

	private MarcaDAO dao = new MarcaDAO();
	
	public void executeQuery() throws VeicoliException {
		getAllMarche();
	}
	
	private void getAllMarche() {
		System.out.println(">>>getAllMarche<<<");
		
		try {
			List<Marca> lD = dao.findAll();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
}
