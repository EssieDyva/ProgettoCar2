package com.betacom.car.services;

import java.util.List;
import java.util.Optional;

import com.betacom.car.dao.BiciDAO;
import com.betacom.car.dao.VeicoliDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Bici;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class ServiceBici {

	private final SQLManager db;
	private final VeicoliDAO daoV;
	private final BiciDAO daoBici;

	public ServiceBici() {
		this.db = new SQLManager();
		this.daoV = new VeicoliDAO();
		this.daoBici = new BiciDAO();
	}

	public void executeQuery() throws Exception {
		System.out.println("Begin transaction");
		try {
			SQLConfiguration.getInstance().setTransaction();
			// listTable();
			// getAllBici();
			// getBiciById(null);
			int idVeicolo = createVeicolo();
			createBici(idVeicolo);

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

	private void getAllBici() {
		System.out.println(">>>getAllBici<<<");

		try {
			List<Bici> lM = daoBici.findAll();
			lM.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}

	private void getBiciById(Integer id) {
		System.out.println(">>>getBiciById<<<");
		try {
			Optional<Bici> bici = daoBici.findById(id);
			if (bici.isPresent())
				System.out.println(bici.get());
			else {
				System.out.println("Bici non trovata: " + id);
			}
		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}

	private int createVeicolo() throws Exception {
		System.out.println("Insert into veicoli:");
		int num = 0;

		Veicoli vei = new Veicoli("bici", 2, 3, 2, 1, 2, 2014, "April");

		num = daoV.insert("update.veicoli.insert", vei);
		System.out.println("Inserimento veicolo: " + num);

		Optional<Veicoli> v = daoV.findById(num);
		if (v.isEmpty()) throw new VeicoliException("Veicolo non trovato");
		System.out.println(v.get());

		return num;
	}

	private void createBici(int idVeicolo) throws Exception {
		System.out.println("insert into Bici*****");
		Bici bici = new Bici(idVeicolo, 6, 1, 2, true);

		daoBici.insert(bici);
		System.out.println("Bici creato");
	}
}