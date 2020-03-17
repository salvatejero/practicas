package com.practicas.exercise1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.practicas.exercise1.utils.DatabaseJson;

public class CarService {
	
	public static JSONArray getMarcaModelo(int start, int stop) {
		
		// comprobamos los parámetros de entrada
		
		if( start > stop ) {
			return null;
		}
		JSONArray array = DatabaseJson.loadDatabase().getData();
		JSONArray arrayReturn = new JSONArray();
		
		int begin = start;
		if(begin < 0) {
			begin = 0;
		}
		int end = stop;
		// si end es mayor que la longitud, end lo asignamos a la longitud
		if(end <= 0 || end > array.length()) {
			end = array.length();
		}
		
		for(int i = begin; i < end; i++) {
			JSONObject objReturn = new JSONObject();
			JSONObject jObj = array.getJSONObject(i);
			objReturn.put("make", jObj.getJSONObject("Identification").getString("Make"));
			objReturn.put("model", jObj.getJSONObject("Identification").getString("ID"));
			// Añadimos una modificación para permitir automáticos
			objReturn.put("transmission", jObj.getJSONObject("Engine Information").getString("Transmission"));
			objReturn.put("year", jObj.getJSONObject("Identification").getInt("Year"));
			objReturn.put("horsepower", jObj.getJSONObject("Engine Information").getJSONObject("Engine Statistics").getInt("Horsepower"));
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
	
	public static JSONArray getMarcaModeloAutomaticos() {
		
		// comprobamos los parámetros de entrada
		
		JSONArray array = getMarcaModelo(-1, -1);
		JSONArray arrayReturn = new JSONArray();
		
		for(int i = 0; i < array.length(); i++) {
			JSONObject objReturn = new JSONObject();
			JSONObject jObj = array.getJSONObject(i);
			if(jObj.getString("transmission").indexOf("Automatic") >= 0) {
				objReturn.put("make", jObj.getString("make"));
				objReturn.put("model", jObj.getString("model"));
				arrayReturn.put(objReturn);
			}
		}
		
		return arrayReturn;
	}
	
	public static JSONArray getMarcaModeloThanCharacterXIsNaN(int nCharacter) {
		
		// comprobamos los parámetros de entrada
		if (nCharacter < 0) {
			return null;
		}
		
		JSONArray array = getMarcaModelo(-1, -1);
		JSONArray arrayReturn = new JSONArray();
		
		for(int i = 0; i < array.length(); i++) {
			JSONObject objReturn = new JSONObject();
			JSONObject jObj = array.getJSONObject(i);
			String model = jObj.getString("model");
			try {
				@SuppressWarnings("unused")
				Integer iCharacter = Integer.valueOf(""+model.charAt(nCharacter));
				objReturn.put("make", jObj.getString("make"));
				objReturn.put("model", jObj.getString("model"));
				arrayReturn.put(objReturn);
			}catch(NumberFormatException e) {
				//_log.log(Level.INFO, "No number", e);
			}
		}
		
		return arrayReturn;
	}
	
	
	public static JSONArray getMarcaModeloByYearOrderBy(int year, boolean asc) {
		
		if(year < 0) {
			return null;
		}
		
		JSONArray array = getMarcaModelo(-1, -1);
		JSONArray arrayReturn = new JSONArray();
		
		List<JSONObject> listJsonValues = new ArrayList<>();
		
		for(int i = 0; i < array.length(); i++) {
			JSONObject objReturn = new JSONObject();
			JSONObject jObj = array.getJSONObject(i);
			if(jObj.getInt("year") == year) {
				objReturn.put("make", jObj.getString("make"));
				objReturn.put("model", jObj.getString("model"));
				objReturn.put("horsepower", jObj.getInt("horsepower"));
				listJsonValues.add(objReturn);
			}
		}
		
		Collections.sort( listJsonValues, new Comparator<JSONObject>() {
	        //You can change "Name" with "ID" if you want to sort by ID
	        private static final String KEY_NAME = "horsepower";

	        @Override
	        public int compare(JSONObject a, JSONObject b) {

                int valA = a.getInt(KEY_NAME);
                int valB = b.getInt(KEY_NAME);

                int multiplicator = 1;
                if(!asc) {
                	multiplicator = -1;
                }
                
                if(valA < valB) {
                	return multiplicator * -1;
                }else if(valA > valB) {
                	return multiplicator * 1;
                }else {
                	return multiplicator *  (a.getString("model").compareTo(b.getString("model")));
                }
	        }
	    });
		
		for(JSONObject jsonObject: listJsonValues) {
			arrayReturn.put(jsonObject);
		}
		
		return arrayReturn;
	}
	

}
