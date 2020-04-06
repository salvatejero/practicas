package com.practicas.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.practicas.services.CarService;
import com.practicas.services.UtilsService;
import com.practicas.servlet.controller.MainController;

public abstract class AbstractServlet extends HttpServlet {

	private static final long serialVersionUID = -2145263435858352687L;
	public CarService carService;
	public UtilsService utilsService;
	public MainController mainController;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		mainController = (MainController)webApplicationContext .getBean("mainController");	
		carService = (CarService)webApplicationContext .getBean("carService");
		utilsService = (UtilsService)webApplicationContext .getBean("utilService");
		
	}

}
