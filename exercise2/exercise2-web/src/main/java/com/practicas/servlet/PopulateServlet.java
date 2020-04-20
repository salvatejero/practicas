package com.practicas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.practicas.model.Car;
import com.practicas.services.data.DatabaseJson;

@WebServlet(name = "LoginServlet", 
	urlPatterns = { "", "/populate" })
public class PopulateServlet extends AbstractServlet {

	private static final long serialVersionUID = -1720688734823865429L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONArray array = DatabaseJson.loadDatabase().getData();
		for(int i=0 ; i < array.length(); i++) {
			JSONObject json = array.getJSONObject(i);
			Car c = new Car();
			
		}

	}

}
