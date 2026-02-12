package com.betacom.car.services;

import java.util.List;
import java.util.Optional;

import com.betacom.car.dao.MotoDAO;
import com.betacom.car.dao.VeicoliDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class ServiceMoto {

	private final SQLManager db;
	private final VeicoliDAO daoV;
	private final MotoDAO daoMoto;

	public ServiceMoto() {
		this.db = new SQLManager();
		this.daoV = new VeicoliDAO();
		this.daoMoto = new MotoDAO();
	}

	public void executeQuery() throws Exception {
		System.out.println("Begin transaction");
		try {
			SQLConfiguration.getInstance().setTransaction();
			// listTable();
			// getAllMoto();
			// getMotoById(null);
			int idVeicolo = createVeicolo();
			createMoto(idVeicolo);

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

	private void getAllMoto() {
		System.out.println(">>>getAllMoto<<<");

		try {
			List<Moto> lM = daoMoto.findAll();
			lM.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}

	private void getMotoById(Integer id) {
		System.out.println(">>>getMotoById<<<");
		try {
			Optional<Moto> moto = daoMoto.findById(id);
			if (moto.isPresent())
				System.out.println(moto.get());
			else {
				System.out.println("Moto non trovata: " + id);
			}
		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}

	private int createVeicolo() throws Exception {
		System.out.println("Insert into veicoli:");
		int num = 0;

		Veicoli vei = new Veicoli(null, null, null, null, null, null, null, null);

		num = daoV.insert("update.veicoli.insert", vei);
		System.out.println("Inserimento veicolo: " + num);

		Optional<Veicoli> v = daoV.findById(num);
		if (v.isEmpty())
			throw new VeicoliException("Veicolo non trovato");
		System.out.println(v.get());

		return num;
	}

	private void createMoto(int idVeicolo) throws Exception {
		System.out.println("insert into Moto*****");
		Moto moto = new Moto(idVeicolo, "TRGMot", 125);

		daoMoto.insert(moto);
		System.out.println("Moto creato");
	}
}
