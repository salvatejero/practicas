package com.practicas.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.practicas.model.Car;
import com.practicas.model.comparators.CarComparator;
import com.practicas.services.data.DatabaseJson;

public class CarService {

	
	public static List<Car> getCars(int start, int stop) {
		
		// comprobamos los parámetros de entrada
		
		assert start < stop;

		List<Car> listCar = DatabaseJson.loadDatabase().getDataParsed();
		
		int begin = start;
		if(begin < 0) {
			begin = 0;
		}
		int end = stop;
		// si end es mayor que la longitud, end lo asignamos a la longitud
		if(end <= 0 || end > listCar.size()) {
			end = listCar.size();
		}
		
		return listCar.subList(begin, end);
	}
	
	public static List<Car> getCars(int start, int end, Predicate<Car> p) {
		
		assert p != null;
		
		List<Car> cars = getCars(start, end).stream().filter(p).collect(Collectors.toList());
		return cars;
	}
	
	
	public static List<Car> getCars(int start, int end, Predicate<Car> p, CarComparator comparator){
		
		List<Car> cars = getCars( start,  end,  p);
		if(comparator != null) {
			return cars.stream().sorted(comparator).collect(Collectors.toList());
		}
		
		return cars.stream().sorted().collect(Collectors.toList());
	}
	
	
	public static List<Car> getCars(int start, int end, Predicate<Car> p, CarComparator comparator, int limit){
		
		assert limit > 0;
		
		List<Car> cars = getCars( start,  end,  p, comparator);
		return cars.stream().limit(limit).collect(Collectors.toList());
	}
	
	
	public static Optional<Car> getCarByPk(int pk) {
		
		assert pk >=0;
		List<Car> cars = getCars(-1, -1);
		return cars.stream().filter(c -> c.getPk() == pk).findFirst();
	}
	
	
}
