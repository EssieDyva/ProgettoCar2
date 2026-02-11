package com.betacom.car.services;

import com.betacom.car.dao.SospensioniDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Sospensioni;
import java.util.List;

public class ServiceSospensioni {

	private SospensioniDAO dao = new SospensioniDAO();
	
	public void executeQuery() throws VeicoliException {
		getAllSospensioni();
	}
	
	private void getAllSospensioni() {
		System.out.println(">>>getAllSospensioni<<<");
		
		try {
			List<Sospensioni> lD = dao.findAll();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
}
