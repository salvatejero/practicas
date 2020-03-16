package com.practicas.exercise1.service;

import org.json.JSONArray;
import org.json.JSONObject;

import com.practicas.exercise1.utils.DatabaseJson;

public class CarService {
	
	public static JSONArray getMarcaModelo(int start, int stop) {
		
		JSONArray array = DatabaseJson.loadDatabase().getData();
		
		JSONArray arrayReturn = new JSONArray();
		
		int begin = start;
		int end = stop;
		if(end == 0) {
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

}
