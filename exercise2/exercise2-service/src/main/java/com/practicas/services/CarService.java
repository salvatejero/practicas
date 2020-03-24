package com.practicas.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.practicas.model.Car;
import com.practicas.model.comparators.CarComparator;
import com.practicas.services.data.DatabaseJson;

public class CarService {

	public static long totalCar;
	
	/**
	 * Obtiene los coches de un rango, se precargan todos
	 * @param start inicio del rango
	 * @param stop fin del rango
	 * @return
	 */
	public static List<Car> getCars(int start, int stop) {
		
		// comprobamos los parámetros de entrada
		
		assert start < stop;

		List<Car> listCar = DatabaseJson.loadDatabase().getDataParsed();
		totalCar = listCar.size();
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
	
	/**
	 * Obtiene las marcas distintas de los coches
	 * @return
	 */
	public static List<String> getCarsMakes() {
		
		return getCars(-1, -1).stream().map(car -> car.getIdentification().getMake()).distinct().sorted().collect(Collectors.toList());
	}
	
	/**
	 * Obtiene los años distintos de los vehículos
	 * @return
	 */
	public static List<Integer> getCarsYears() {
		
		return getCars(-1, -1).stream().map(car -> car.getIdentification().getYear()).distinct().sorted().collect(Collectors.toList());
	}
	
	/**
	 * Obtiene los coches que cumplen un predicado
	 * @param start inicio
	 * @param end fin
	 * @param p Predicado
	 * @return
	 */
	public static List<Car> getCars(int start, int end, Predicate<Car> p) {
		
		assert p != null;
		Stream<Car> stream = getCars(-1, -1).stream().filter(p);
		List<Car> cars = stream.collect(Collectors.toList()).subList(start, end);
		return cars;
	}
	
	/**
	 * Obtiene los coches que cumplen un predicado
	 * @param start inicio
	 * @param end fin
	 * @param p Predicado
	 * @return
	 */
	public static List<Car> getCars(int start, int end, List<Predicate<Car>> ps) {
		
		assert ps != null;
		Stream<Car> stream = getCars(-1, -1).stream();
		for(Predicate<Car> p: ps) {
			stream.filter(p);
		}
		List<Car> cars = stream.collect(Collectors.toList()).subList(start, end);
		return cars;
	}
	
	/**
	 * Obtiene el número de coches que cumplen el predicado
	 * @param p
	 * @return
	 */
	public static long getCarsCount(List<Predicate<Car>> ps) {
		
		assert ps != null;
		Stream<Car> stream = getCars(-1, -1).stream();
		for(Predicate<Car> p: ps) {
			stream.filter(p);
		}
		return stream.count();
	}
	
	
	/**
	 * Obtiene el número de coches que cumplen el predicado
	 * @param p
	 * @return
	 */
	public static long getCarsCount(Predicate<Car> p) {
		
		assert p != null;
		Stream<Car> stream = getCars(-1, -1).stream().filter(p);
		return stream.count();
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
