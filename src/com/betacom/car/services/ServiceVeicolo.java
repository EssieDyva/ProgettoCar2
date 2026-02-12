package com.betacom.car.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.betacom.car.dao.VeicoliDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Veicoli;
import com.betacom.car.utils.SQLManager;

public class ServiceVeicolo {
	
	private SQLManager db = new SQLManager();
	private VeicoliDAO dao = new VeicoliDAO();
	
	public void executeQuery() throws VeicoliException {
		getAllVeicoli();
		// getVeicoliById(null);
		// insertVeicolo();	
		// updateVeicolo(1);
	}

	private void listTable() throws VeicoliException {
		List<String> lT = db.listOfTable("car_db");
		lT.forEach(t -> System.out.println(t));
	}

	private void getAllVeicoli() {
		System.out.println(">>>getAllVeicoli<<<");
		
		try {
			List<String> lD = dao.findAllVeicoli();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
	
	private void getVeicoliById(Integer id) {
		System.out.println(">>>getVeicoliById<<<");
		try {
			Optional<Veicoli> dip = dao.findById(id);
			if(dip.isPresent())
				System.out.println(dip.get());
			else {
				System.out.println("Veicolo non trovato: " + id);
			}
		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
	
	private void updateVeicolo(int id) {
		System.out.println("Update veicolo:");
		Veicoli vei = new Veicoli();
		vei.setModello("Modello modificato");
		
		try {
			System.out.println("numero di righe modifcate: " + dao.update(vei));
			Optional<Veicoli> row = dao.findById(id);
			if (row.isPresent())
				System.out.println(row.get());
		} catch (Exception e) {
			System.err.println("inserimento fallito: " + e.getMessage());
		}
	}
}
