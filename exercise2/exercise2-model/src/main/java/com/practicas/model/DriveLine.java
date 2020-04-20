package com.practicas.model;

public class DriveLine {

	private int id;
	private String driveLine;
	
	public DriveLine() {
		super();
	}
	
	public DriveLine(int id, String driveLine) {
		super();
		this.id = id;
		this.driveLine = driveLine;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDriveLine() {
		return driveLine;
	}

	public void setDriveLine(String driveLine) {
		this.driveLine = driveLine;
	}
	
	@Override
	public String toString() {
		return "{id: "+id+",driveLine: "+driveLine+"}";
	}
	
}
