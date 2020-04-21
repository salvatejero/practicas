package com.practicas.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.dao.DriveLineDao;
import com.practicas.dao.FuelTypeDao;
import com.practicas.dao.MakeDao;
import com.practicas.dao.TransmissionDao;
import com.practicas.model.DriveLine;
import com.practicas.model.FuelType;
import com.practicas.model.Make;
import com.practicas.model.Transmission;
import com.practicas.services.UtilsService;

@Service("utilService")
public class UtilServiceImpl implements UtilsService {

	@Autowired
	private TransmissionDao transmissionDao;
	
	@Autowired
	private MakeDao makeDao;

	@Autowired
	private FuelTypeDao fuelTypeDao;
	
	@Autowired
	private DriveLineDao driveLineDao;
	
	/**
	 * Obtiene las marcas distintas de los coches
	 * 
	 * @return
	 */
	public List<Make> getCarsMakes() {

		return makeDao.findMakes();
	}

	/**
	 * Obtiene los años distintos de los vehículos
	 * 
	 * @return
	 */
	public List<Integer> getCarsYears() {

		return new ArrayList<Integer>();
	}

	public Make saveMake(Make m) {

		return makeDao.save(m);
	}

	public FuelType saveFuelType(FuelType f) {

		return fuelTypeDao.save(f);
	}

	public DriveLine saveDriveLine(DriveLine d) {

		return driveLineDao.save(d);
	}
	
	public Transmission saveTransmission(Transmission t) {

		return transmissionDao.save(t);
	}
}
