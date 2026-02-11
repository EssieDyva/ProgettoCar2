package com.betacom.car.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.betacom.car.models.Colore;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class ColoreDAO {
	
	private SQLManager db = new SQLManager();
	
	public List<Colore> findAll()  throws Exception{
		String query = (SQLConfiguration.getInstance().getQuery("query.colori"));
		System.out.println(query);


		List<Map<String, Object>> lV = db.list(query);
		return lV.stream()
				.map(row -> new Colore(
						(Integer)row.get("id_colore"), 
						(String)row.get("descrizione"))).collect(Collectors.toList());
	}
}