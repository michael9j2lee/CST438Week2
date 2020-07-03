package cst438_assignment2.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


public class CityInfo {
	
	int id;
	String name;
	String countryCode;
	String countryName;
	String district;
	int population;
	double temp;
	long time;

	public CityInfo(City city, TempAndTime tempAndTime) {
		this.id = city.getID();
		this.name = city.getName();
		this.countryCode = city.getCountry().getCode();
		this.countryName = city.getCountry().getName();
		this.district = city.getDistrict();
		this.population = city.getPopulation();
		this.temp = tempAndTime.getTemp();
		this.time = tempAndTime.getTime();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
}
