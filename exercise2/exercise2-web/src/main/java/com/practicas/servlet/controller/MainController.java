package com.practicas.servlet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practicas.model.Car;
import com.practicas.services.CarService;

public class MainController {

	public void mainAction(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<Car> cars = CarService.getCars(0, 9);
		long count = CarService.totalCar;
		
		request.setAttribute("total", count);
		request.setAttribute("cars", cars);
	}
	
	public void paginacion(HttpServletRequest request, HttpServletResponse response) {
		
		String page = request.getParameter("page");
		int begin = 0;
		int end = 9;
		if(page != null) {
			begin = (Integer.valueOf(page)+1 * 10) -10;
			end = begin + 10;
		}
		
		List<Car> cars = CarService.getCars(begin, end);
		long count = CarService.totalCar;
		request.setAttribute("total", count);
		request.setAttribute("cars", cars);
		request.setAttribute("page", page);
		
	}
}
