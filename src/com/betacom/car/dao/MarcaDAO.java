package com.betacom.car.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.betacom.car.models.Colore;
import com.betacom.car.models.Marca;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class MarcaDAO {
	
	private SQLManager db = new SQLManager();
	
	public List<Marca> findAll()  throws Exception{
		String query = (SQLConfiguration.getInstance().getQuery("query.marche"));
		System.out.println(query);


		List<Map<String, Object>> lV = db.list(query);
		return lV.stream()
				.map(row -> new Marca(
						(Integer)row.get("id_marca"), 
						(String)row.get("descrizione"))).collect(Collectors.toList());
	}
}