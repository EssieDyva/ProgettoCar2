package com.betacom.car.services;

import java.util.List;

import com.betacom.car.dao.ColoreDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Colore;

public class ServiceColore {

	private ColoreDAO dao = new ColoreDAO();
	
	public void executeQuery() throws VeicoliException {
		getAllColori();
	}
	
	private void getAllColori() {
		System.out.println(">>>getAllColori<<<");
		
		try {
			List<Colore> lD = dao.findAll();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
}
