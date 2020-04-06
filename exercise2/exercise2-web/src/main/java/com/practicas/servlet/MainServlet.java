package com.practicas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.practicas.services.UtilsService;
import com.practicas.servlet.controller.MainController;

@WebServlet(name = "MainServlet", 
	urlPatterns = { "/cars" }, 
	initParams = @WebInitParam(name = "location", value = "Hola"),
	loadOnStartup = 1)
public class MainServlet extends AbstractServlet {

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
		
		request.setAttribute("years", utilsService.getCarsYears());
		request.setAttribute("makes", utilsService.getCarsMakes());
		
		request.getRequestDispatcher(dispatcher).forward(request, response);;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		super.doPost(request, response);
	}

	
}
