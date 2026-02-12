package com.betacom.car.process;

import com.betacom.car.services.ServiceBici;
import com.betacom.car.services.ServiceMacchina;
import com.betacom.car.services.ServiceMoto;
import com.betacom.car.singleton.SQLConfiguration;

public class SQLCar {
	public boolean execute() throws Exception {
		System.out.println("Begin SQLCar");
		
		try {
			
			SQLConfiguration.getInstance().getConnection();
			System.out.println("Connection with db");
			// new ServiceVeicolo().executeQuery();
			// new ServiceMacchina().executeTransaction();
			// new ServiceMoto().executeQuery();
			// new ServiceBici().executeQuery();
			// new ServiceQuery().executeQuery();
			// new ServicesUpdate().executeUpdate();
			// new ServicesTransaction().executeTransaction();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
