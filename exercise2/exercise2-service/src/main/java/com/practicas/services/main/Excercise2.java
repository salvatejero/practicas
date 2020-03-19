package com.practicas.services.main;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.practicas.services.CarService;
import com.practicas.services.data.DatabaseJson;

public class Excercise2 {

	public static void main(String[] args) {

		CarService.getCars(-1, -1, null);

	}

	
	@SuppressWarnings("unused")
	private static void loadData() {
		JSONArray array = DatabaseJson.loadDatabase().getData();

		int length = array.length();
		List<String> listCheck = new ArrayList<>(); 
		for(int i = 0; i < length; i++) {
			int random = new SecureRandom().nextInt(length); 
			while(listCheck.contains(""+random)) {
				random = new SecureRandom().nextInt(length);
			}
			((JSONObject)array.get(i)).put("pk", random);
		}
		
		DatabaseJson.saveFile(array.toString());
		
		for(int i = 0; i < length; i++) {
			System.out.println(((JSONObject)array.get(i)).get("pk"));
		}
	}
	
}
