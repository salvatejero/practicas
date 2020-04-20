package com.practicas.model;

public class FuelType {

	private int id;
	private String fuelType;
	
	public FuelType() {
		super();
	}
	
	public FuelType(int id, String fuelType) {
		super();
		this.id = id;
		this.fuelType = fuelType;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{id: "+id+",fuelType: "+fuelType+"}";
	}
	
	
}
