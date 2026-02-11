package com.betacom.car.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.models.Bici;
import com.betacom.car.singleton.SQLConfiguration;
import com.betacom.car.utils.SQLManager;

public class BiciDAO {
		
			private SQLManager db = new SQLManager();
			
			public int insert(Bici bic) throws Exception {
				
				Object [] params = new Object[] {
						bic.getIdBici(),
						bic.getNumeroMarce(),
						bic.getTipoFreno(),
						bic.getTipoSospensione(),
						bic.getIsPiegevole(),
				};
				
				String query = (SQLConfiguration.getInstance().getQuery("update.bici.insert"));
				System.out.println(query);
				
				return db.save(query, params, true);
			}
			
			public int update(Bici bic) throws Exception {
				String query = "update bici set ";
				String separator = "";
				List<Object> params = new ArrayList<Object>();
				if(bic.getNumeroMarce() != null) {
					query = query + separator + "numeroMarce = ?";
					params.add(bic.getNumeroMarce());
					separator = " , ";
				}
				if(bic.getTipoFreno() != null) {
					query = query + separator + "tipoFreno = ?";
					params.add(bic.getTipoFreno());
					separator = " , ";
				}
				if(bic.getTipoSospensione() != null) {
					query = query + separator + "tipoSospensione = ?";
					params.add(bic.getTipoSospensione());
					separator = " , ";
				}
				if(bic.getIsPiegevole() != null) {
					query = query + separator + "isPiegevole = ?";
					params.add(bic.getIsPiegevole());
					separator = " , ";
				}
				if(bic.getId() == null)
					throw new VeicoliException("ID null");
				query = query + "where id_bici= ?";
				params.add(bic.getId());
				
				Object[] paramsArray = params.toArray();
				
				System.out.println("SQL generato:" + query);
				
				return db.save(query, paramsArray);
			}
			
			public int delete(Integer id) throws Exception{
				if(id == null) 
					throw new VeicoliException("Id non Ã¨ stato caricato");
				
				Object[] params = new Object[] {id};
				String query = "delete from bici where id_bici = ?";
				
				return db.save(query, params);
			}
			
			
			public List<Bici> findAll()  throws Exception{
				String query = (SQLConfiguration.getInstance().getQuery("query.bici"));
				System.out.println(query);
				
				
				List<Map<String, Object>> lV = db.list(query);
				return lV.stream()
						.map(row -> new Bici(
								(Integer)row.get("id_bici"), 
								(Integer)row.get("numeroMarce"), 
								(Integer)row.get("tipoFreno"),
								(Integer)row.get("tipoSospensione"),
								(Boolean)row.get("isPiegevole"))).collect(Collectors.toList());
			}
			
			public Optional<Bici> findById(Integer id) throws Exception {
				String query = SQLConfiguration.getInstance().getQuery("query.bici.byId");
				System.out.println(query);
				Object[] params = new Object[] {id};

				Map<String,Object> row = db.get(query, params);
				if(row == null)
					return Optional.empty();

				return Optional.ofNullable(new Bici(
						(Integer)row.get("id_bici"), 
						(Integer)row.get("numeroMarce"), 
						(Integer)row.get("tipoFreno"),
						(Integer)row.get("tipoSospensione"),
						(Boolean)row.get("isPiegevole")
						));
			}
	}