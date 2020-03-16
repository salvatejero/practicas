package com.practicas.exercise1.service;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.practicas.exercise1.utils.DatabaseJson;

public class CarService {
	
	public static JSONArray getMarcaModelo(int start, int stop) {
		
		// comprobamos los parámetros de entrada
		
		if( start < 0 || start >= stop ) {
			return null;
		}
		JSONArray array = DatabaseJson.loadDatabase().getData();
		JSONArray arrayReturn = new JSONArray();
		
		int begin = start;
		int end = stop;
		// si end es mayor que la longitud, end lo asignamos a la longitud
		if(end == 0 || end > array.length()) {
			end = array.length();
		}
		
		for(int i = begin; i < end; i++) {
			JSONObject objReturn = new JSONObject();
			JSONObject jObj = array.getJSONObject(i);
			objReturn.put("make", jObj.getJSONObject("Identification").getString("Make"));
			objReturn.put("model", jObj.getJSONObject("Identification").getString("ID"));
			arrayReturn.put(objReturn);
		}
		
		return arrayReturn;
	}
	
	/**
	 * Devuelve un listado de Marca y Modelo con más caballos {horsepower}
	 * Usando Iterator
	 * @param count Número de elementos
	 * @param horsepower Control
	 * @return
	 */
	public static JSONArray getMarcaModeloGTHorsePower(int count, int horsepower) {
		
		if (horsepower < 0) {
			return null;
		}
		JSONArray array = DatabaseJson.loadDatabase().getData();
		JSONArray arrayReturn = new JSONArray();
		Iterator<Object> iterator = array.iterator();
		while(iterator.hasNext() && arrayReturn.length() < count) {
			JSONObject jObj = (JSONObject) iterator.next();
			if(jObj.getJSONObject("Engine Information").getJSONObject("Engine Statistics").getInt("Horsepower") > 150) {
				JSONObject objReturn = new JSONObject();
				objReturn.put("make", jObj.getJSONObject("Identification").getString("Make"));
				objReturn.put("model", jObj.getJSONObject("Identification").getString("ID"));
		    	arrayReturn.put(objReturn);
		    }
		}
		return arrayReturn;
	}
	
	/**
	 * Devuelve un listado de Marca y Modelo con más caballos {horsepower}
	 * Usando FOR
	 * @param count Número de elementos
	 * @param horsepower Control
	 * @return
	 */
	public static JSONArray getMarcaModeloGTHorsePower2(int count, int horsepower) {
		
		if (horsepower < 0) {
			return null;
		}
		JSONArray array = DatabaseJson.loadDatabase().getData();
		JSONArray arrayReturn = new JSONArray();
		for(int i=0; i<array.length() || i < count; i++) {
			JSONObject jObj = (JSONObject) array.get(i);
			if(jObj.getJSONObject("Engine Information").getJSONObject("Engine Statistics").getInt("Horsepower") > 150) {
		    	JSONObject objReturn = new JSONObject();
				objReturn.put("make", jObj.getJSONObject("Identification").getString("Make"));
				objReturn.put("model", jObj.getJSONObject("Identification").getString("ID"));
		    	arrayReturn.put(objReturn);
		    }
		}
			
		return arrayReturn;
	}
	
	
	/**
	 * Devuelve un listado de Marca y Modelo con más caballos {horsepower}
	 * Usando forEach
	 * @param count Número de elementos
	 * @param horsepower Control
	 * @return
	 */
	public static JSONArray getMarcaModeloGTHorsePower3(int count, int horsepower) {
	
		if (horsepower < 0) {
			return null;
		}
		JSONArray array = DatabaseJson.loadDatabase().getData();
		JSONArray arrayReturn = new JSONArray();
		array.forEach(item -> {
			JSONObject jObj = (JSONObject) item;
		    if(jObj.getJSONObject("Engine Information").getJSONObject("Engine Statistics").getInt("Horsepower") > horsepower) {
		    	JSONObject objReturn = new JSONObject();
				objReturn.put("make", jObj.getJSONObject("Identification").getString("Make"));
				objReturn.put("model", jObj.getJSONObject("Identification").getString("ID"));
		    	arrayReturn.put(objReturn);
		    }
		    if(arrayReturn.length() >= count) {
		    	return;
		    }
		});
		
		return arrayReturn;
	}
	
	

}
