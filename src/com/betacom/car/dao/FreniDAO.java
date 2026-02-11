package com.betacom.car.dao;

import com.betacom.car.models.Freni;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FreniDAO {
	
	private SQLManager db = new SQLManager();
	
	public List<Freni> findAll()  throws Exception{
		String query = (SQLConfiguration.getInstance().getQuery("query.freni"));
		System.out.println(query);


		List<Map<String, Object>> lV = db.list(query);
		return lV.stream()
				.map(row -> new Freni(
						(Integer)row.get("id_freno"), 
						(String)row.get("descrizione"))).collect(Collectors.toList());
	}
}