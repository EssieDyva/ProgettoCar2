package com.betacom.car.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.betacom.car.exceptions.VeicoliException;
import com.betacom.car.utils.FileProcess;

public class CarSingleton {

	private static CarSingleton instance = null;

	private Set<String> setTarghe = new HashSet<String>();
	
	Map<String, String[]> controlli = new HashMap<String, String[]>();
	
	private Integer id = 0;
	
	FileProcess utils= new FileProcess();
	
	
	private CarSingleton() {
		loadConstant();
	}

	public static CarSingleton getInstance() {
		if (instance == null) {
			instance = new CarSingleton();
		}
		return instance;
	}
	
	public Integer computeId() {
		return ++id;
	}

	public Boolean checkLoadTarga(String targa) {
		try {
			if (!setTarghe.contains(targa)) {
				setTarghe.add(targa);
				return true;				
			}
			else {
				throw new VeicoliException();
			}
		}
		catch (VeicoliException e) {
			System.out.println("Targa uguale" + e.getMessage());
			return false;
		}
	}
	
	public void loadConstant() {
		FileProcess utils= new FileProcess();
		List<String> cons = new ArrayList<String>();
		cons=utils.readFile("src/FileInSingleton");
		
		cons.forEach(it -> {
			String [] el = it.split("=");
			String [] elP = el[1].split(",");
			controlli.put(el[0], elP);	
		});
	}
	
	public boolean isValidValue(String key, String value) {
		String[] values = controlli.get(key);
		boolean ret = false;
		for (String it:values) {
			if (value.equalsIgnoreCase(it)) {
				ret = true;
				break;
			}			
		}
		return ret;
	}
}
