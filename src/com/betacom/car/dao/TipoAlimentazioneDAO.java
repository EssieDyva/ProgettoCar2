package com.betacom.car.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.betacom.car.models.TipoAlimentazione;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class TipoAlimentazioneDAO {
	
	private SQLManager db = new SQLManager();
	
	public List<TipoAlimentazione> findAll()  throws Exception{
		String query = (SQLConfiguration.getInstance().getQuery("query.colori"));
		System.out.println(query);


		List<Map<String, Object>> lV = db.list(query);
		return lV.stream()
				.map(row -> new TipoAlimentazione(
						(Integer)row.get("id_alimentazione"), 
						(String)row.get("descrizione"))).collect(Collectors.toList());
	}
}