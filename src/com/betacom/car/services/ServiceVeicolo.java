package com.betacom.car.services;

import java.util.List;
import java.util.Optional;

import com.betacom.car.dao.VeicoliDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class ServiceVeicolo {
	
	private SQLManager db = new SQLManager();
	private VeicoliDAO dao = new VeicoliDAO();
	
	public void executeQuery() throws VeicoliException {
		listTable();
		getAllVeicoli();
		// getVeicoliById(null);
		// insertVeicolo();	
	}

	private void listTable() throws VeicoliException {
		List<String> lT = db.listOfTable("car_db");
		lT.forEach(t -> System.out.println(t));
	}

	private void getAllVeicoli() {
		System.out.println(">>>getAllVeicoli<<<");
		
		try {
			List<Veicoli> lD = dao.findAll();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
	
	private void getVeicoliById(Integer id) {
		System.out.println(">>>getDipendenteById<<<");
		try {
			Optional<Veicoli> dip = dao.findById(id);
			if(dip.isPresent())
				System.out.println(dip.get());
			else {
				System.out.println("Dipendente non trovato: " + id);
			}
		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
}
