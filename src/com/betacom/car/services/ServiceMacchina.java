package com.betacom.car.services;

import java.util.List;
import java.util.Optional;

import com.betacom.car.dao.MacchinaDAO;
import com.betacom.car.dao.VeicoliDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class ServiceMacchina {
	
	private final SQLManager db;
	private final VeicoliDAO daoV;
	private final MacchinaDAO daoM;
	
	public ServiceMacchina() {
		this.db = new SQLManager();
		this.daoV = new VeicoliDAO();
		this.daoM = new MacchinaDAO();
	}
	
	public void executeQuery() throws Exception {
		listTable();
		getAllMacchine();
		// getMacchinaById(null);
		int idVeicolo = createVeicolo();
		createMacchina(idVeicolo);
	}

	private void listTable() throws VeicoliException {
		List<String> lT = db.listOfTable("car_db");
		lT.forEach(t -> System.out.println(t));
	}

	private void getAllMacchine() {
		System.out.println(">>>getAllMacchine<<<");
		
		try {
			List<Macchina> lD = daoM.findAll();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
	
	private void getMacchinaById(Integer id) {
		System.out.println(">>>getDipendenteById<<<");
		try {
			Optional<Macchina> dip = daoM.findById(id);
			if(dip.isPresent())
				System.out.println(dip.get());
			else {
				System.out.println("Dipendente non trovato: " + id);
			}
		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
	
	private int createVeicolo() throws VeicoliException {
		System.out.println("Insert into veicoli:");
		System.out.println(SQLConfiguration.getInstance().getQuery("update.veicoli.insert"));
		
		int num = 0;
		
		Veicoli vei = new Veicoli(null, null, null, null, null, null, null, null);
		
		try {
			num = daoV.insert("update.veicoli.insert", vei);
			System.out.println("Inserimento veicolo: " + num);
			
			List<Veicoli> lC = daoV.findAll();
			lC.forEach(c -> System.out.println(c));
			return num;
		} catch (Exception e) {
			System.err.println("inserimento fallito: " + num);
		}
		return num;
	}
	
	private void createMacchina(int idVeicolo) throws Exception {
		System.out.println("insert into RapportoCliente*****");
		Macchina mac = new Macchina(idVeicolo, 5, "ASDFGR", 125);

		int idRapporto = daoM.insert(mac);
		System.out.println("Rapporto cliente creato");

		Optional<Macchina> row = daoM.findById(idRapporto);
		if(row.isEmpty()) throw new VeicoliException("Macchina non trovata");
		System.out.println(row.get());
	}
}
