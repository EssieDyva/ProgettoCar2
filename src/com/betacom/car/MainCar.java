package com.betacom.car;
import java.util.ArrayList;
import java.util.List;

import com.betacom.car.process.SQLCar;
import com.betacom.car.process.StartCar;

public class MainCar {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		//eventuali parametri dentro una liste
		
		List<String> params = new ArrayList<String>();
		
		System.out.println("inizio");
		
//		StartCar engine= new StartCar();
//		engine.execute("src/FileInVeicoli");
		
		SQLCar engine = new SQLCar();
		engine.execute();
		
		/*
		 * tipo di input format.
		 * add,macchina,marca=fiat,modello=panda,cat=cita,colore=bianca..... 
		 * add,moto,marca=Yamaha,modello=R1,cat=strada,colore=rossa..... 
		 * add,bici......
		 * ...
		 * ...
		 * list 
		 * list macchina/moto/bici 
		 * process: -control dei parametri  --- In caso di error fare un sysout con l'errore
		 * 			-se il controllo va bene carcare l'oggetto dentro una List<Veicoli> commune per tutti veicoli
		 * 
		 * la lista: prevedere un filtro per tipo veicoli
		 * 
		 * 
		 */
		
		//commento Git 18:31 test
		
	}

}
