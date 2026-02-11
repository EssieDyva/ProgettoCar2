package com.betacom.car.services.implementation;

import java.util.Map;

import com.betacom.car.models.Macchina;
import com.betacom.car.models.Veicoli;
import com.betacom.car.services.Abstract.AbstractVeicoloImp;
import com.betacom.car.singleton.CarSingleton;

public class MacchinaImplementation extends AbstractVeicoloImp {

    @Override
    protected Veicoli creaVeicolo(Integer id, String tipoVeicolo, Integer numeroRuote, String tipoAlimentazione, String categoria, String colore, String marca, Integer annoProduzione, String modello, Map<String, String> mappa) {
        
        System.out.println("inizio crea veicolo macchina");
        
        try {
            Integer numeroPorte = Integer.parseInt(mappa.get("numeroPorte"));
            String targa = mappa.get("targa");
            Integer cc = Integer.parseInt(mappa.get("cc"));
            
            Boolean checkTarga = CarSingleton.getInstance().checkLoadTarga(targa);
            
            if (!checkTarga) {
                System.out.println("Errore: Targa già presente o non valida");
                return null;
            }
            
            if (numeroRuote != 4) {
                System.out.println("Errore: Una macchina deve avere 4 ruote");
                return null;
            }
            
            if (numeroPorte < 3 || numeroPorte > 5) {
                System.out.println("Errore: Numero porte non valido");
                return null;
            }
            
            return new Macchina(id, tipoVeicolo, numeroRuote, tipoAlimentazione, categoria, colore, marca, annoProduzione, modello, numeroPorte, targa, cc);

        } catch (Exception e) {
            System.out.println("Errore nei dati specifici della Macchina: " + e.getMessage());
            return null;
        }
    }
}