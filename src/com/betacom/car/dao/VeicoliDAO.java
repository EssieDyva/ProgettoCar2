package com.betacom.car.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Veicoli;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class VeicoliDAO {

	private SQLManager db = new SQLManager();

	public int insert(String qryName, Veicoli vei) throws Exception {

		Object [] params = new Object[] {
				vei.getTipoVeicolo(),
				vei.getNumeroRuote(),
				vei.getIdTipoAlimentazione(),
				vei.getIdCategoria(),
				vei.getIdColore(),
				vei.getIdMarca(),
				vei.getAnnoProduzione(),
				vei.getModello()
		};

		String query = (SQLConfiguration.getInstance().getQuery(qryName));

		return db.save(query, params, true);
	}

	public int update(Veicoli vei) throws Exception {
		String query = "update veicoli set ";
		String separator = "";
		List<Object> params = new ArrayList<Object>();
		if(vei.getTipoVeicolo() != null) {
			query = query + separator + "tipo_veicolo = ?";
			params.add(vei.getTipoVeicolo());
			separator = " , ";
		}
		if(vei.getNumeroRuote() != null) {
			query = query + separator + "numero_ruote = ?";
			params.add(vei.getNumeroRuote());
			separator = " , ";
		}
		if(vei.getIdTipoAlimentazione() != null) {
			query = query + separator + "tipo_alimentazione = ?";
			params.add(vei.getIdTipoAlimentazione());
			separator = " , ";
		}
		if(vei.getIdCategoria() != null) {
			query = query + separator + "categoria = ?";
			params.add(vei.getIdCategoria());
			separator = " , ";
		}
		if(vei.getIdColore() != null) {
			query = query + separator + "colore = ?";
			params.add(vei.getIdColore());
			separator = " , ";
		}
		if(vei.getIdMarca() != null) {
			query = query + separator + "marca = ?";
			params.add(vei.getIdMarca());
			separator = " , ";
		}
		if(vei.getAnnoProduzione() != null) {
			query = query + separator + "anno_produzione = ?";
			params.add(vei.getAnnoProduzione());
			separator = " , ";
		}
		if(vei.getModello() != null) {
			query = query + separator + "modello = ?";
			params.add(vei.getModello());
			separator = " , ";
		}
		if(vei.getId() == null)
			throw new VeicoliException("ID null");
		query = query + "where id= ?";
		params.add(vei.getId());

		Object[] paramsArray = params.toArray();

		System.out.println("SQL generato:" + query);

		return db.save(query, paramsArray);
	}

	public int delete(Integer id) throws Exception{
		if(id == null) 
			throw new VeicoliException("Id non Ã¨ stato caricato");

		Object[] params = new Object[] {id};
		String query = "delete from veicoli where id_veicoli = ?";

		return db.save(query, params);
	}


	public List<Veicoli> findAll()  throws Exception{
		String query = (SQLConfiguration.getInstance().getQuery("query.veicoli"));
		System.out.println(query);


		List<Map<String, Object>> lV = db.list(query);
		return lV.stream()
				.map(row -> new Veicoli(
						(Integer)row.get("id_veicolo"), 
						(String)row.get("tipo_veicolo"), 
						(Integer)row.get("numero_ruote"), 
						(Integer)row.get("id_alimentazione"),
						(Integer)row.get("id_categoria"),
						(Integer)row.get("id_colore"),
						(Integer)row.get("id_marca"),
						(Integer)row.get("anno_produzione"),
						(String)row.get("modello"))).collect(Collectors.toList());
	}

	public Optional<Veicoli> findById(Integer id) throws Exception {
		String query = SQLConfiguration.getInstance().getQuery("query.veicoli.byId");
		System.out.println(query);
		Object[] params = new Object[] {id};

		Map<String,Object> row = db.get(query, params);
		if(row == null)
			return Optional.empty();

		return Optional.ofNullable(new Veicoli(
				(Integer)row.get("id_veicolo"), 
				(String)row.get("tipo_veicolo"), 
				(Integer)row.get("numero_ruote"), 
				(Integer)row.get("id_alimentazione"),
				(Integer)row.get("id_categoria"),
				(Integer)row.get("id_colore"),
				(Integer)row.get("id_marca"),
				(Integer)row.get("anno_produzione"),
				(String)row.get("modello")
				));
	}
}