package com.betacom.car.singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.utils.SQLManager;

public class SQLConfiguration {
	private static SQLConfiguration instance = null;
	private static Properties prop = new Properties();
	private static Properties queries = new Properties();
	
	private Connection con = null;

	private SQLConfiguration () {}
	
	public static SQLConfiguration getInstance() throws VeicoliException {
		if(instance == null) {
			instance = new SQLConfiguration();
			loadConfiguration();			
		}
		return instance;
	}

	private static void loadConfiguration() throws VeicoliException {
		try {
			InputStream inp = new FileInputStream("src/sql.properties");
			prop.load(inp);
			
			InputStream inpQuery = new FileInputStream("src/query.properties");
			queries.load(inpQuery);

		} catch (IOException e) {
			throw new VeicoliException(e.getMessage());
		}
	}
	
	public String getProperty(String param) {
		return prop.getProperty(param);
	}
	
	public String getQuery(String param) {
		return queries.getProperty(param);
	}
	
	/*
	 * close connection 
	 */
	public void closeConnection() throws VeicoliException {
		try {
			if(!con.isClosed())
				con.close();
			con = null;
		} catch (Exception e) {
			throw new VeicoliException("Errore durante la chiusura della connessione: " + e.getLocalizedMessage());
		}
	}
	
	public Connection getConnection() throws VeicoliException {
		if (con == null) 
			con = new SQLManager().getConnection();
		
		return con;
	}
	
	public void setAutoCommit() throws SQLException{ 
		con.setAutoCommit(true);
	}
	
	public void setTransaction() throws SQLException{ 
		con.setAutoCommit(false);
	}
}
