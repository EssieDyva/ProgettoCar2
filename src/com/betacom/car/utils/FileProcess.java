package com.betacom.car.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.betacom.car.models.Veicoli;

public class FileProcess {

	public List<String> readFile(String path) {
		List<String> r = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line = reader.readLine();
			while (line != null) {
				r.add(line);
				line = reader.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public void writeFile(String path, Map<Integer, Veicoli> mappaVei, boolean mode) {
		BufferedWriter o = null;

		try {
			o = new BufferedWriter(new FileWriter(path, mode));

			for (Entry<Integer, Veicoli> it : mappaVei.entrySet()) {
				o.write("Stampa con filtro -- key: " + it.getKey() + " value: " + it.getValue().toString());
				o.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				o.flush();
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeFile(String path, String veicolo, boolean mode) {
		BufferedWriter o = null;

		try {
			o = new BufferedWriter(new FileWriter(path, mode));

			o.write("Stampa JSON: " + veicolo);
			o.newLine();


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				o.flush();
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}