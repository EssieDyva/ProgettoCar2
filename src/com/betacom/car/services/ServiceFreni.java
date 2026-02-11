package com.betacom.car.services;

import com.betacom.car.dao.FreniDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Freni;
import java.util.List;

public class ServiceFreni {

	private FreniDAO dao = new FreniDAO();
	
	public void executeQuery() throws VeicoliException {
		getAllFreni();
	}
	
	private void getAllFreni() {
		System.out.println(">>>getAllFreni<<<");
		
		try {
			List<Freni> lD = dao.findAll();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
}
