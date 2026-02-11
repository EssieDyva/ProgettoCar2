package com.betacom.car.services.implementation;

import java.util.Map;

import com.betacom.car.models.Moto;
import com.betacom.car.models.Veicoli;
import com.betacom.car.services.Abstract.AbstractVeicoloImp;
import com.betacom.car.singleton.CarSingleton;

public class MotoImplementation extends AbstractVeicoloImp{
     @Override
     protected Veicoli creaVeicolo(Integer id, String tipoVeicolo, Integer numeroRuote, String tipoAlimentazione, String categoria, String colore, String marca, Integer annoProduzione, String modello, Map<String, String> mappa) {

         System.out.println("Inizio creazione Moto...");

         try {

             String targa = mappa.get("targa");
             Integer cc = Integer.parseInt(mappa.get("cc")); 

             Boolean checkTarga = CarSingleton.getInstance().checkLoadTarga(targa);
             if (!checkTarga) {
                 System.out.println("Moto scartata: targa già esistente.");
                 return null;
             }

             if (numeroRuote < 2 || numeroRuote > 3) {
                 System.out.println("Moto scartata: numero ruote non valido.");
                 return null;
             }

             return new Moto(id, tipoVeicolo, numeroRuote, tipoAlimentazione, categoria, colore, marca, annoProduzione, modello, targa, cc);

         } catch (Exception e) {
             System.out.println("Errore dati Moto: " + e.getMessage());
             return null;
         }
     }

}