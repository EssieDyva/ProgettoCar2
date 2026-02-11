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
			listTable();
			getAllMoto();
			// getMotoById(null);
			int idVeicolo = createVeicolo();
			createMoto(idVeicolo);
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
				if(moto.isPresent())
					System.out.println(moto.get());
				else {
					System.out.println("Moto non trovata: " + id);
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
		
		private void createMoto(int idVeicolo) throws Exception {
			System.out.println("insert into Moto*****");
			Moto moto = new Moto(idVeicolo, "TRGMot", 125);

			int idMoto = daoMoto.insert(moto);
			System.out.println("Moto creato");

			Optional<Moto> row = daoMoto.findById(idMoto);
			if(row.isEmpty()) throw new VeicoliException("Moto non trovata");
			System.out.println(row.get());
		}
	}

