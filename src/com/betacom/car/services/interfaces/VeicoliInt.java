package com.betacom.car.services.interfaces;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Veicoli;

public interface VeicoliInt {
	Veicoli execute(String[] lS) throws VeicoliException;
}


	