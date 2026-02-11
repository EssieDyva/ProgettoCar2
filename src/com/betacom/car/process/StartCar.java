package com.betacom.car.process;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Veicoli;
import com.betacom.car.services.implementation.BiciImplementation;
import com.betacom.car.services.implementation.MacchinaImplementation;
import com.betacom.car.services.implementation.MotoImplementation;
import com.betacom.car.services.interfaces.VeicoliInt;
import com.betacom.car.utils.FileProcess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StartCar {

	public void execute(String path) throws Exception{

		System.out.println("inizio startcar");

		List<String> params = new ArrayList<String>();
		Map<String, VeicoliInt> mappaImp = new HashMap<String, VeicoliInt>();
		Map<Integer, Veicoli> mappaVei=new HashMap<Integer, Veicoli>();
		mappaImp.put("macchina", new MacchinaImplementation());
		mappaImp.put("moto", new MotoImplementation());
		mappaImp.put("bici", new BiciImplementation());

		FileProcess utils= new FileProcess();
		params=utils.readFile(path);
		params.forEach(riga -> {

			String[]elementi=riga.split(",");
			Veicoli v=null;

			if (mappaImp.containsKey(elementi[1])) {
				try {
					VeicoliInt ex = mappaImp.get(elementi[1]);
					v=ex.execute(elementi);

					if(v != null) {
						System.out.println("Test " + v.toString());
						mappaVei.put(v.getId(), v);
					}
				} catch (VeicoliException e) {
					System.out.println("Errore applicativo:" + e.getMessage());

				} catch (Exception e) {
					System.out.println("Abnomal end:" + e.getMessage());
					e.printStackTrace();
				}

			} else {
				System.out.println("veicolo non previsto");
			}

			try {
				if (v != null) {
					System.out.println(v.toString());
					mappaVei.put(v.getId(), v);}
				else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Errore in startCar");
			}
		});

		List<String> paramFileFiltro = utils.readFile("src/FileFiltro");
		Map<String, String> mappaCriteri = new HashMap<>();

		if (!paramFileFiltro.isEmpty()) {
			Arrays.stream(paramFileFiltro.get(0).split(","))
			.forEach(p -> {
				String[] coppia = p.split("=");
				if (coppia.length == 2) {
					mappaCriteri.put(coppia[0].trim(), coppia[1].trim());
				}
			});
		}
		Gson json = new GsonBuilder().setPrettyPrinting().create();

//		mappaVei.values().stream()
//		.filter(i -> {
//			if (mappaCriteri.isEmpty()) return true;
//			String infoVeicolo = i.generaStringaFiltro(); 
//			return mappaCriteri.entrySet().stream()
//					.allMatch(c -> infoVeicolo.contains(c.getKey() + "=" + c.getValue()));
//		})
//		.forEach(i -> {
//			String jsonString = json.toJson(i);
//			System.out.println("--- Veicolo formato JSON ---");
//			System.out.println(jsonString);
//
//			utils.writeFile("src/FileOutJson", jsonString, true);
//
//			//	    	Type type = new TypeToken<List<>>() {}.getType(); // retrieve correct type
//			//			List<ObjectJson> lUser1 = json.fromJson(jsonString, type);
//		});

		/*
		String filtro="2009";
		stampa(filtro, mappaVei);
		String filePath = "src/FileOut.txt";
		utils.writeFile(filePath, mappaVei, true);
		mappaVei.entrySet().stream()
		.filter(i -> i.getValue().getColore().equals(filtro) || filtro == null)
		.filter(i -> i.getValue().getTipoAlimentazione().equals(filtro) || filtro == null)
		.filter(i -> String.valueOf(i.getValue().getAnnoProduzione()).equals(filtro) || filtro == null)
		.filter(i -> i.getValue().getMarca().equals(""))
		.forEach(i -> System.out.println(i.getValue().toString()));
		 */
	}

	public void stampa(String filtro, Map<Integer, Veicoli> mappaVei) {
		for (Entry<Integer, Veicoli> it:mappaVei.entrySet()) {
			if(it.getValue().getTipoVeicolo().equalsIgnoreCase(filtro))
				System.out.println("Stampa con filtro -- key: " + it.getKey() + " value: " + it.getValue().toString());
		}
	}
}
