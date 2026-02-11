package com.betacom.car.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Moto;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class MotoDAO {
private SQLManager db = new SQLManager();
	
	public int insert(Moto moto) throws Exception {
		
		Object [] params = new Object[] {
				moto.getIdMoto(),
				moto.getTarga(),
				moto.getCc()
		};
		
		String query = (SQLConfiguration.getInstance().getQuery("update.moto.insert"));
		System.out.println(query);
		
		return db.save(query, params, true);
	}
	
	public int update(Moto moto) throws Exception {
		String query = "update moto set ";
		String separator = "";
		List<Object> params = new ArrayList<Object>();
		if(moto.getTarga() != null) {
			query = query + separator + "targa = ?";
			params.add(moto.getTarga());
			separator = " , ";
		}
		if(moto.getCc() != null) {
			query = query + separator + "cilindrata = ?";
			params.add(moto.getCc());
			separator = " , ";
		}
		
		if(moto.getId() == null)
			throw new VeicoliException("ID null");
		query = query + "where id_moto= ?";
		params.add(moto.getId());
		
		Object[] paramsArray = params.toArray();
		
		System.out.println("SQL generato:" + query);
		
		return db.save(query, paramsArray);
	}
	
	public int delete(Integer id) throws Exception{
		if(id == null) 
			throw new VeicoliException("Id non è stato caricato");
		
		Object[] params = new Object[] {id};
		String query = "delete from moto where id_moto = ?";
		
		return db.save(query, params);
	}
	
	public List<Moto> findAll()  throws Exception{
		String query = (SQLConfiguration.getInstance().getQuery("query.moto"));
		System.out.println(query);
		
		
		List<Map<String, Object>> lM = db.list(query);
		return lM.stream()
				.map(row -> new Moto(
						(Integer)row.get("id_moto"), 
						(String)row.get("targa"), 
						(Integer)row.get("cilindrata"))).collect(Collectors.toList());
	}
	
	public Optional<Moto> findById(Integer id) throws Exception {
		String query = SQLConfiguration.getInstance().getQuery("query.moto.byId");
		System.out.println(query);
		Object[] params = new Object[] {id};

		Map<String,Object> row = db.get(query, params);
		if(row == null)
			return Optional.empty();

		return Optional.ofNullable(new Moto(
				(Integer)row.get("id_moto"), 
				(String)row.get("targa"), 
				(Integer)row.get("cilindrata")
				));
	}
}