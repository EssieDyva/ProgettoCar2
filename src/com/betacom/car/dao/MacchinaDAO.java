package com.betacom.car.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Macchina;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class MacchinaDAO {

	private SQLManager db = new SQLManager();
	
	public int insert(Macchina mac) throws Exception {
		String query = (SQLConfiguration.getInstance().getQuery("update.macchina.insert"));
		System.out.println(query);
		
		Object [] params = new Object[] {
				mac.getIdMacchina(),
				mac.getNumeroPorte(),
				mac.getTarga(),
				mac.getCc()
		};
		
		
		return db.save(query, params);
	}
	
	public int update(Macchina mac) throws Exception {
		String query = "update macchina set ";
		String separator = "";
		List<Object> params = new ArrayList<Object>();
		if(mac.getNumeroPorte() != null) {
			query = query + separator + "porte = ?";
			params.add(mac.getNumeroPorte());
			separator = " , ";
		}
		if(mac.getTarga() != null) {
			query = query + separator + "targa = ?";
			params.add(mac.getTarga());
			separator = " , ";
		}
		if(mac.getCc() != null) {
			query = query + separator + "cilindrata = ?";
			params.add(mac.getCc());
			separator = " , ";
		}
		
		if(mac.getId() == null)
			throw new VeicoliException("ID null");
		query = query + "where id_macchina= ?";
		params.add(mac.getId());
		
		Object[] paramsArray = params.toArray();
		
		System.out.println("SQL generato:" + query);
		
		return db.save(query, paramsArray);
	}
	
	public int delete(Integer id) throws Exception{
		if(id == null) 
			throw new VeicoliException("Id non Ã¨ stato caricato");
		
		Object[] params = new Object[] {id};
		String query = "delete from macchina where id_macchina = ?";
		
		return db.save(query, params);
	}
	
	
	public List<Macchina> findAll()  throws Exception{
		String query = (SQLConfiguration.getInstance().getQuery("query.macchina"));
		System.out.println(query);
		
		
		List<Map<String, Object>> lV = db.list(query);
		return lV.stream()
				.map(row -> new Macchina(
						(Integer)row.get("id_macchina"), 
						(Integer)row.get("porte"), 
						(String)row.get("targa"), 
						(Integer)row.get("cilindrata"))).collect(Collectors.toList());
	}
	
	public Optional<Macchina> findById(Integer id) throws Exception {
		String query = SQLConfiguration.getInstance().getQuery("query.macchina.byId");
		System.out.println(query);
		Object[] params = new Object[] {id};

		Map<String,Object> row = db.get(query, params);
		if(row == null)
			return Optional.empty();

		return Optional.ofNullable(new Macchina(
				(Integer)row.get("id_macchina"), 
				(Integer)row.get("porte"), 
				(String)row.get("targa"), 
				(Integer)row.get("cilindrata")
				));
	}
}