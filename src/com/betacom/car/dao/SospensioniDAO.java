package com.betacom.car.dao;

import com.betacom.car.models.Sospensioni;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SospensioniDAO {
	
	private SQLManager db = new SQLManager();
	
	public List<Sospensioni> findAll()  throws Exception{
		String query = (SQLConfiguration.getInstance().getQuery("query.sospensioni"));
		System.out.println(query);


		List<Map<String, Object>> lV = db.list(query);
		return lV.stream()
				.map(row -> new Sospensioni(
						(Integer)row.get("id_sospensione"), 
						(String)row.get("descrizione"))).collect(Collectors.toList());
	}
}