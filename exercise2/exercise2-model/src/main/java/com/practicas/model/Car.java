package com.practicas.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="CAR")
public class Car implements Comparable<Car>, Serializable{

	private static final long serialVersionUID = 3594839582111552527L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	// Engine Information
	@Transient
	private Transmission transmission;
	
	@NotEmpty
	@Column(name="ENGINETYPE", nullable=false)
	private String enginetype;
	@Transient
	private EngineStatistics enginestatistics;
	@Transient
	private DriveLine driveLine;
	
	
	// Dimensions
	@NotEmpty
	@Column(name="WIDTH", nullable=false)
	private int width;
	
	@NotEmpty
	@Column(name="LENGTH", nullable=false)
	private int length;
	
	@NotEmpty
	@Column(name="HEIGHT", nullable=false)
	private int height;
	
	
	// Identification
	@NotEmpty
	@OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MAKE_ID", referencedColumnName = "ID")
	private Make make;
	@Transient
	private String modelyear;
	@Transient
	private String name;
	@Transient
	private int year;
	
	// FuelInformation
	@Transient
	private int highwaympg;
	@Transient
	private int citymph;
	@Transient
	private FuelType fueltype;
	
	public Car() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public String getEnginetype() {
		return enginetype;
	}

	public void setEnginetype(String enginetype) {
		this.enginetype = enginetype;
	}

	public EngineStatistics getEnginestatistics() {
		return enginestatistics;
	}

	public void setEnginestatistics(EngineStatistics enginestatistics) {
		this.enginestatistics = enginestatistics;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public String getModelyear() {
		return modelyear;
	}

	public void setModelyear(String modelyear) {
		this.modelyear = modelyear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHighwaympg() {
		return highwaympg;
	}

	public void setHighwaympg(int highwaympg) {
		this.highwaympg = highwaympg;
	}

	public int getCitymph() {
		return citymph;
	}

	public void setCitymph(int citymph) {
		this.citymph = citymph;
	}

	public FuelType getFueltype() {
		return fueltype;
	}

	public void setFueltype(FuelType fueltype) {
		this.fueltype = fueltype;
	}

	public DriveLine getDriveLine() {
		return driveLine;
	}

	public void setDriveLine(DriveLine driveLine) {
		this.driveLine = driveLine;
	}

	@Override
	public int compareTo(Car o) {
		
		if (this.getId() <= o.getId()) {
			return -1;
		}else {
			return 1;
		}
	}

	@Override
	public boolean equals(Object obj) {
		Car c1 = (Car)obj;
		return this.getId() == c1.getId();
	}

	@Override
	public String toString() {
		return "{id: "+id+",make: "+getMake().getMake()+", name: "+name+"}";
	}
		
	
}
