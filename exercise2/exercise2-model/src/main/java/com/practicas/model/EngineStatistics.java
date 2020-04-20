package com.practicas.model;

public class EngineStatistics {

	private int id;
	private int horsepower;
	private int torque;
	
	public EngineStatistics() {
		
	}
	
	public EngineStatistics(int id, int horsepower, int torque) {
		super();
		this.id = id;
		this.horsepower = horsepower;
		this.torque = torque;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

	public int getTorque() {
		return torque;
	}

	public void setTorque(int torque) {
		this.torque = torque;
	}
	
	
}
