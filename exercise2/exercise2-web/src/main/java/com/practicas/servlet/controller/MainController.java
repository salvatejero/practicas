package com.practicas.servlet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.practicas.model.Car;
import com.practicas.services.CarService;

@Controller("mainController")
public class MainController {

	@Autowired
	private CarService carService;
	
	public void mainAction(HttpServletRequest request, HttpServletResponse response) {

		List<Car> cars = carService.getCars(0, 25);
		long count = carService.getTotalCar();

		request.setAttribute("total", count);
		request.setAttribute("cars", cars);
	}

	public void paginacion(HttpServletRequest request, HttpServletResponse response) {

		String page = request.getParameter("page");
		int begin = 0;
		int end = 9;
		if (page != null) {
			begin = (Integer.valueOf(page) + 1 * 10) - 10;
			end = begin + 10;
		}

		List<Car> cars = carService.getCars(begin, end, null);
		long count = carService.getTotalCar();
		request.setAttribute("total", count);
		request.setAttribute("cars", cars);
		request.setAttribute("page", page);

	}

	public void detail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pk = request.getParameter("pk");
		if(pk == null) {
			throw new Exception("Coche no encontrado");
		}
		String redirect = request.getParameter("redirect");
		Car car = carService.getCarByPk(Integer.valueOf(pk));
		request.setAttribute("redirect", redirect);
		request.setAttribute("car", car);

	}
}
