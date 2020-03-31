package com.practicas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practicas.services.CarService;
import com.practicas.servlet.controller.MainController;

@WebServlet(name = "MainServlet", 
	urlPatterns = { "/cars" }, 
	initParams = @WebInitParam(name = "location", value = "Hola"),
	loadOnStartup = 1)
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String location = getInitParameter("location");
		
		//super.doGet(request, response);
		String action = request.getParameter("action");
		String dispatcher = "./index.jsp";
		MainController controller = new MainController();
		if(action == null && !"".equals(action)) {
			controller.mainAction(request, response);
		}else if("paginacion".equals(action)) {
			controller.paginacion(request, response);
			
		}else if("detail".equals(action)) {
			try {
				controller.detail(request, response);
				dispatcher = "./detail.jsp";
			} catch (Exception e) {
				request.setAttribute("message", e.getMessage());
				dispatcher = "./error.jsp";
			}
			
		}
		
		request.setAttribute("years", CarService.getCarsYears());
		request.setAttribute("makes", CarService.getCarsMakes());
		
		request.getRequestDispatcher(dispatcher).forward(request, response);;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		super.doPost(request, response);
	}

	
}
