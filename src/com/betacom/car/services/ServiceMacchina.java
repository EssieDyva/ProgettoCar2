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

	public void executeTransaction() throws VeicoliException {
		System.out.println("Begin transaction");
		try {
			SQLConfiguration.getInstance().setTransaction();
			// listTable();
			// getMacchinaById(null);
			int idVeicolo = createVeicolo();
			createMacchina(idVeicolo);
			
			//getAllMacchine();
			db.commit();			
		} catch (Exception e) {
			System.err.println("Error found: " + e.getMessage());
			System.err.println("Execute rollback...");
			db.rollback();
		}
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
		System.out.println(">>>getMacchinaById<<<");
		try {
			Optional<Macchina> dip = daoM.findById(id);
			if(dip.isPresent())
				System.out.println(dip.get());
			else {
				System.out.println("Macchina non trovato: " + id);
			}
		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}

	private int createVeicolo() throws Exception {
		System.out.println("Insert into veicoli:");
		int num = 0;

		Veicoli vei = new Veicoli("macchina", 4, 2, 2, 3, 1, 2010, "500XL");

		num = daoV.insert("update.veicoli.insert", vei);
		System.out.println("Inserimento veicolo: " + num);

		Optional<Veicoli> v = daoV.findById(num);
		if (v.isEmpty()) throw new VeicoliException("Veicolo non trovato");
		System.out.println(v.get());

		return num;
	}

	private void createMacchina(int idVeicolo) throws Exception {
		System.out.println("insert into Macchina*****");
		Macchina mac = new Macchina(idVeicolo, 5, "SS884ZZ", 1500);

		daoM.insert(mac);
		System.out.println("Macchina creato");
	}
}
