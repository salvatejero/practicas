package com.practicas.servlet;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.practicas.model.Car;
import com.practicas.model.CarImage;


@WebServlet(name = "UpdateCarServlet", urlPatterns = { "/update" })
@MultipartConfig(location="/tmp", 
	fileSizeThreshold=1024*1024,
	maxFileSize=1024*1024*5, 
	maxRequestSize=1024*1024*5*5
)

public class UpdateCarServlet extends AbstractServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String engineType = request.getParameter("enginetype");
		String driveline = request.getParameter("driveline");
		String name = request.getParameter("name");
		String pk = request.getParameter("pk");
		
		
		String redirect = request.getParameter("redirect");
		//Part pPepe = request.getPart("pepe");
		
		List<Part> fileParts = request.getParts().stream().filter(part -> part.getName().contains("image") && part.getSize() > 0).collect(Collectors.toList());
		List<CarImage> cImages = new ArrayList<>();
		Car c = carService.getCarByPk(Integer.valueOf(pk));		
		for(Part p: fileParts) {
			byte[] bytes = IOUtils.toByteArray(p.getInputStream());
			String nameImage = p.getSubmittedFileName();
			CarImage cImage = new CarImage();
			cImage.setImage(bytes);
			cImage.setName(nameImage);
			cImages.add(cImage);
		}
		
		
		// Validator
		if(name != null) {
			if(!cImages.isEmpty()) {
				c.setCarImages(cImages);
				
			}
			c = carService.update(c);
			if (1 > 0) {
				c = carService.getCarByPk(Integer.valueOf(pk));	
				if(!cImages.isEmpty()) {
					c.setCarImages(cImages);
					c = carService.update(c);
				}
			}
		}
		
		
		
		
		try {
			mainController.detail(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("executed", "ok");
		
		
		// Validar 
		
		
		/*Optional<Car> carOp = CarService.getCarByPk(Integer.valueOf(pk));
		
		if(carOp.isPresent()) {
			
			Car car = carOp.get();
			
			car.getEngineinformation().setDriveline(driveline);
			car.getEngineinformation().setEnginetype(engineType);
			
			int contador = 0;
			for(Car c :DatabaseJson.loadDatabase().getDataParsed()) {
				if(c.getPk() == Integer.valueOf(pk)) {
					DatabaseJson.loadDatabase().getDataParsed().set(contador, c);
				}
				contador ++;
			}
		}*/
		
		request.getRequestDispatcher("./detail.jsp").forward(request, response);;
	
	}
	
	
	public static String decodeValue(String value) {
		String result = "";
		try{
	    	result = java.net.URLDecoder.decode(value, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
		    // not going to happen - value came from JDK's own StandardCharsets
		}
		return result;
	}

}
