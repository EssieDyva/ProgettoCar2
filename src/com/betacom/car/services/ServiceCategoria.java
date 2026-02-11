package com.betacom.car.services;

import java.util.List;

import com.betacom.car.dao.CategoriaDAO;
import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Categoria;

public class ServiceCategoria {

	private CategoriaDAO dao = new CategoriaDAO();
	
	public void executeQuery() throws VeicoliException {
		getAllCategorie();
	}
	
	private void getAllCategorie() {
		System.out.println(">>>getAllCategorie<<<");
		
		try {
			List<Categoria> lD = dao.findAll();
			lD.forEach(d -> System.out.println(d));

		} catch (Exception e) {
			System.err.println("Error found " + e.getMessage());
		}
	}
}
