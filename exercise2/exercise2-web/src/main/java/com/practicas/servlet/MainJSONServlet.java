package com.practicas.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.gson.Gson;
import com.practicas.model.PaginaCompleta;
import com.practicas.services.UtilsService;
import com.practicas.servlet.controller.MainController;

@WebServlet(name = "MainJSONServlet", 
	urlPatterns = { "/carsJSON" }, 
	initParams = @WebInitParam(name = "location", value = "Hola"),
	loadOnStartup = 1)
public class MainJSONServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String location = getInitParameter("location");
		
		//super.doGet(request, response);
		String action = request.getParameter("action");
		String dispatcher = "./index.jsp";
		if(action == null && !"".equals(action)) {
			mainController.mainAction(request, response);
		}else if("paginacion".equals(action)) {
			mainController.paginacion(request, response);
			
		}else if("detail".equals(action)) {
			try {
				mainController.detail(request, response);
				dispatcher = "./detail.jsp";
			} catch (Exception e) {
				request.setAttribute("message", e.getMessage());
				dispatcher = "./error.jsp";
			}
			
		}
		
		PaginaCompleta pagina = new PaginaCompleta();
		
		
		Map<String, List<?>> filters = new HashMap<String, List<?>>();
		
		filters.put("makes", utilsService.getCarsMakes());
		filters.put("years", utilsService.getCarsYears());
		pagina.setFilters(filters);
		pagina.setCars(carService.getCars());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        Gson gson = new Gson();
        out.print(gson.toJson(pagina));
        out.flush();   
		
		
		request.setAttribute("years", utilsService.getCarsYears());
		request.setAttribute("makes", utilsService.getCarsMakes());
		
		request.getRequestDispatcher(dispatcher).forward(request, response);;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		super.doPost(request, response);
	}

	
}
