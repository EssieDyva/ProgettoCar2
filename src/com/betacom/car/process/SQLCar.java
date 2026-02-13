package com.betacom.car.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.car.utils.FileProcess;

import com.betacom.car.services.ServiceBici;
import com.betacom.car.services.ServiceMacchina;
import com.betacom.car.services.ServiceMoto;
import com.betacom.car.services.ServiceVeicolo;
import com.betacom.car.singleton.SQLConfiguration;

public class SQLCar {
	public boolean execute() throws Exception {
	    System.out.println("Begin SQLCar");
	    
	    FileProcess fp = new FileProcess();
	    List<String> insF = fp.readFile("src/FileInVeicoli"); 
	    
	    try {
	        SQLConfiguration.getInstance().getConnection();
	        System.out.println("Connection with db established");
	        
	        for (String riga : insF) {
	            if (!riga.startsWith("add")) continue;

	            Map<String, String> c = parseRiga(riga);
	            String tipo = c.get("tipoVeicolo");
	            
	            if (tipo == null) continue;
	            
	            System.out.println("Elaborazione tipo: " + tipo);

	            switch(tipo) {
	            case "macchina":
	                new ServiceMacchina().executeTransaction(
	                    tipo, 
	                    Integer.parseInt(c.get("numeroRuote")),
	                    Integer.parseInt(c.get("tipoAlimentazione")),
	                    Integer.parseInt(c.get("categoria")),
	                    Integer.parseInt(c.get("colore")),
	                    Integer.parseInt(c.get("marca")),
	                    Integer.parseInt(c.get("annoProduzione")),
	                    c.get("modello"),
	                    Integer.parseInt(c.get("numeroPorte")),
	                    c.get("targa"),
	                    Integer.parseInt(c.get("cc"))
	                );
	                break;

	            case "moto":
	                new ServiceMoto().executeQuery(
	                    tipo, 
	                    Integer.parseInt(c.get("numeroRuote")),
	                    Integer.parseInt(c.get("tipoAlimentazione")),
	                    Integer.parseInt(c.get("categoria")),
	                    Integer.parseInt(c.get("colore")),
	                    Integer.parseInt(c.get("marca")),
	                    Integer.parseInt(c.get("annoProduzione")),
	                    c.get("modello"),
	                    c.get("targa"),
	                    Integer.parseInt(c.get("cc"))
	                );
	                break;

	            case "bici":
	                new ServiceBici().executeQuery(
	                    tipo, 
	                    Integer.parseInt(c.get("numeroRuote")),
	                    Integer.parseInt(c.get("tipoAlimentazione")),
	                    Integer.parseInt(c.get("categoria")),
	                    Integer.parseInt(c.get("colore")),
	                    Integer.parseInt(c.get("marca")),
	                    Integer.parseInt(c.get("annoProduzione")),
	                    c.get("modello"),
	                    Integer.parseInt(c.get("numeroMarce")),
	                    Integer.parseInt(c.get("tipoFreno")),
	                    Integer.parseInt(c.get("tipoSospensione")),
	                    Boolean.parseBoolean(c.get("pieghevole"))
	                );
	                break;
	            }
	        }

	        new ServiceVeicolo().executeQuery();
	        
	    } catch (Exception e) {
	        System.err.println("Errore durante l'esecuzione: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return true;
	}

//		new ServiceMacchina().executeTransaction("macchina", 4, 2, 2, 3, 1, 2010, "500XL", 5, "SS884ZZ", 1500);
//		new ServiceMoto().executeQuery("moto", 2, 1, 2, 3, 2, 2009, "RMX", "TRGMot", 125);
//		new ServiceBici().executeQuery("bici", 2, 3, 2, 1, 2, 2014, "April", 6, 1, 2, true);
//		new ServiceQuery().executeQuery();
//		new ServicesUpdate().executeUpdate();
//		new ServicesTransaction().executeTransaction();
//		new ServiceVeicolo().executeQuery();
			
	private Map<String, String> parseRiga(String riga) {
        Map<String, String> mappa = new HashMap<>();
        String[] pezzi = riga.split(",");
        for (int i = 1; i < pezzi.length; i++) {
            String[] kv = pezzi[i].split("=");
            if (kv.length == 2) mappa.put(kv[0], kv[1]);
        }
        return mappa;
    }
}
