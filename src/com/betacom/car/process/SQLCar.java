package com.betacom.car.process;

import com.betacom.car.services.ServiceBici;
import com.betacom.car.services.ServiceMacchina;
import com.betacom.car.services.ServiceMoto;
import com.betacom.car.services.ServiceVeicolo;
import com.betacom.car.singleton.SQLConfiguration;

public class SQLCar {
	public boolean execute() throws Exception {
		System.out.println("Begin SQLCar");
		
		try {
			
			SQLConfiguration.getInstance().getConnection();
			System.out.println("Connection with db");
			new ServiceMacchina().executeTransaction("macchina", 4, 2, 2, 3, 1, 2010, "500XL", 5, "SS884ZZ", 1500);
			new ServiceMoto().executeQuery("moto", 2, 1, 2, 3, 2, 2009, "RMX", "TRGMot", 125);
			new ServiceBici().executeQuery("bici", 2, 3, 2, 1, 2, 2014, "April", 6, 1, 2, true);
			// new ServiceQuery().executeQuery();
			// new ServicesUpdate().executeUpdate();
			// new ServicesTransaction().executeTransaction();
			new ServiceVeicolo().executeQuery();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
