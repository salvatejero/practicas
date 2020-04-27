package com.practicas.dao;

import java.util.List;

import com.practicas.model.Car;
import com.practicas.model.Classification;


public interface CarDao {

	List<Car> findPaginationCars(int start, int end);
	
	Car save(Car c);
	
	Car getByPk(Integer key);
}
