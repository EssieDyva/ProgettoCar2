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

	public void executeQuery(String tipoVeicolo, Integer numeroRuote, Integer idTipoAlimentazione, Integer idCategoria,
			Integer idColore, Integer idMarca, Integer annoProduzione, String modello, Integer numeroMarce, Integer tipoFreno, Integer tipoSospensione, Boolean isPiegevole) throws Exception {
		System.out.println("Begin transaction");
		try {
			SQLConfiguration.getInstance().setTransaction();
			// listTable();
			// getAllBici();
			// getBiciById(null);
			int idVeicolo = createVeicolo(tipoVeicolo, numeroRuote, idTipoAlimentazione, idCategoria, idColore, idMarca, annoProduzione, modello);
			createBici(idVeicolo, numeroMarce, tipoFreno, tipoSospensione, isPiegevole);

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

	private int createVeicolo(String tipoVeicolo, Integer numeroRuote, Integer idTipoAlimentazione, Integer idCategoria,
			Integer idColore, Integer idMarca, Integer annoProduzione, String modello) throws Exception {
		System.out.println("Insert into veicoli:");
		int num = 0;

		Veicoli vei = new Veicoli(tipoVeicolo, numeroRuote, idTipoAlimentazione, idCategoria, idColore, idMarca, annoProduzione, modello);

		num = daoV.insert("update.veicoli.insert", vei);
		System.out.println("Inserimento veicolo: " + num);

		Optional<Veicoli> v = daoV.findById(num);
		if (v.isEmpty()) throw new VeicoliException("Veicolo non trovato");
		System.out.println(v.get());

		return num;
	}

	private void createBici(int idVeicolo, Integer numeroMarce, Integer tipoFreno, Integer tipoSospensione, Boolean isPiegevole) throws Exception {
		System.out.println("insert into Bici*****");
		Bici bici = new Bici(idVeicolo, numeroMarce, tipoFreno, tipoSospensione, isPiegevole);

		daoBici.insert(bici);
		System.out.println("Bici creato");
	}
}