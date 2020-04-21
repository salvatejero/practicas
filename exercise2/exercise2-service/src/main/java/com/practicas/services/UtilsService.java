package com.practicas.services;

import java.util.List;

import com.practicas.model.DriveLine;
import com.practicas.model.FuelType;
import com.practicas.model.Make;
import com.practicas.model.Transmission;

public interface UtilsService {

	public List<Make> getCarsMakes();
	
	public List<Integer> getCarsYears();
	
	public Make saveMake(Make m);
	
	public FuelType saveFuelType(FuelType f);
	
	public DriveLine saveDriveLine(DriveLine d);
	
	public Transmission saveTransmission(Transmission t);
}
