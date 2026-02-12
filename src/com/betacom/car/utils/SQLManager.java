package com.betacom.car.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.singleton.SQLConfiguration;

public class SQLManager {
	public Connection getConnection() throws VeicoliException {
		Connection con = null;

		try {
			Class.forName(SQLConfiguration.getInstance().getProperty("driver"));
			con = DriverManager.getConnection(
					SQLConfiguration.getInstance().getProperty("url"),
					SQLConfiguration.getInstance().getProperty("user"),
					SQLConfiguration.getInstance().getProperty("pwd")
					);

		} catch (Exception e) {
			throw new VeicoliException(e.getMessage());
		}

		return con;
	}

	/*
	 * Table list
	 */
	public List<String> listOfTable(String dbName) throws VeicoliException{

		List<String> lT = new ArrayList<String>();
		try {
			DatabaseMetaData dbMD = SQLConfiguration.getInstance().getConnection().getMetaData();

			ResultSet  res = dbMD.getTables(dbName, null, null, null);			
			// build del result			
			while(res.next()) {
				lT.add(res.getString("TABLE_name"));
			}

		} catch (SQLException e) {
			throw new VeicoliException(e.getMessage());
		}
		return lT;

	}

	/*
	 * query without parameters
	 */
	public List<Map<String, Object>> list(String query) throws VeicoliException {

		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query);
			ResultSet res = cmd.executeQuery();

			return resultsetToList(res);
		} catch (SQLException e) {
			throw new VeicoliException(e.getMessage());
		}
	}

	/*
	 * query with parameters
	 */
	public List<Map<String, Object>> list(String query, Object[] params) throws VeicoliException {

		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query); // compile query
			cmd = createSet(cmd, params);

			ResultSet res = cmd.executeQuery();

			return resultsetToList(res);
		} catch (SQLException e) {
			throw new VeicoliException(e.getMessage());
		}
	}

	/*
	 * query with single result object
	 */
	public Map<String, Object> get(String query, Object[] param) throws VeicoliException {

		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query);
			cmd = createSet(cmd, param);
			ResultSet res = cmd.executeQuery();

			return resultsetToMap(res);
		} catch (SQLException e) {
			throw new VeicoliException(e.getMessage());
		}
	}

	/*
	 * Count with parameters
	 */
	public Long count(String query, Object[] param) throws VeicoliException {
		try {
			String queryCount = "select count(*) as numero from ( " + query + " ) as numero";

			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(queryCount);
			cmd = createSet(cmd, param);
			ResultSet res = cmd.executeQuery();
			res.next();

			return res.getLong("numero");

		} catch (SQLException e) {
			throw new VeicoliException(e.getMessage());
		}
	}

	/*
	 * save whithout primary key
	 */
	public int save(String query, Object[] param) throws VeicoliException {
		int ret = 0;

		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query);
			cmd = createSet(cmd, param);

			ret = cmd.executeUpdate();

		} catch (Exception e) {
			throw new VeicoliException(e.getMessage());
		}


		return ret;
	}

	/*
	 * save with primary key
	 */
	public int save(String query, Object[] param, boolean pk) throws VeicoliException {
		int ret = 0;

		try {
			PreparedStatement cmd = SQLConfiguration.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			cmd = createSet(cmd, param);

			ret = cmd.executeUpdate();

			ResultSet generatedKeys = cmd.getGeneratedKeys();
			if (generatedKeys.next())
				ret = generatedKeys.getInt(1);
			else 
				throw new VeicoliException("Generated key non valida");
			
		} catch (Exception e) {
			throw new VeicoliException(e.getMessage());
		}


		return ret;
	}

	/*
	 * load parameters into PreparedStatement
	 */
	private PreparedStatement createSet(PreparedStatement cmd, Object[] params) throws SQLException {
		int idx = 1;
		for (Object o:params) {
			cmd.setObject(idx++, o);
		}
		return cmd;
	}


	/*
	 * transform result set into map list
	 */
	public List<Map<String, Object>> resultsetToList(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();	// retrieve ResultSet metadata
		int columns = md.getColumnCount();			// retrieve table number of columns

		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();

		while(rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			for(int i=1; i<= columns;i++) {
				row.put(md.getColumnLabel(i), rs.getObject(i));
			}
			rows.add(row);
		}
		return rows;
	}

	/*
	 * transform resultSet in map
	 */
	private Map<String, Object> resultsetToMap(ResultSet rs) throws SQLException{
		ResultSetMetaData md = rs.getMetaData();	// retrieve ResultSet metadata
		int columns = md.getColumnCount();			// retrieve table number of columns

		if(!rs.next()) return null;

		Map<String, Object> row = new HashMap<String, Object>();
		for(int i=1; i<= columns;i++) {
			row.put(md.getColumnLabel(i), rs.getObject(i));
		}
		return row;
	}
	
	/*
	 * commit
	 */
	public void commit() throws VeicoliException {
		try {
			SQLConfiguration.getInstance().getConnection().commit();
		} catch (SQLException e) {
			throw new VeicoliException();
		}
	}
	
	/*
	 * rollback
	 */
	public void rollback() throws VeicoliException {
		try {
			SQLConfiguration.getInstance().getConnection().rollback();
		} catch (SQLException e) {
			throw new VeicoliException();
		}
	}
}
