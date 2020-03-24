package com.practicas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practicas.services.CarService;
import com.practicas.servlet.controller.MainController;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//super.doGet(request, response);
		String action = request.getParameter("action");
		MainController controller = new MainController();
		if(action == null && !"".equals(action)) {
			controller.mainAction(request, response);
		}else if("paginacion".equals(action)) {
			controller.paginacion(request, response);
			
		}
		/*int begin = 0;
		int end = 10;
		
		else {
			page = "1";
		}
		String filterByMakes = request.getParameter("make");
		List<Car> cars = new ArrayList<>();
		long count = 0;
		if(filterByMakes != null && !filterByMakes.equals("")) {
			Predicate<Car> p = new Predicate<Car>() {

				@Override
				public boolean test(Car t) {
					return t.getIdentification().getMake().equals(filterByMakes);
				}
				
			};
			cars = CarService.getCars(begin, end, p);
			count = CarService.getCarsCount(p);
		}else {
			
		}*/
		
		//request.setAttribute("make", filterByMakes);
		
		request.setAttribute("years", CarService.getCarsYears());
		request.setAttribute("makes", CarService.getCarsMakes());
		
		request.getRequestDispatcher("./index.jsp").forward(request, response);;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		super.doPost(request, response);
	}

	
}
