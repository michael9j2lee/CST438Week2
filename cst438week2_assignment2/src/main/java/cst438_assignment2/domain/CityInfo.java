package cst438_assignment2.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


public class CityInfo {
	

	@Transient
	City city;
	// Transient marks extra field in an Entity class
	//  that is not read/written to the database.
	@Transient
	TempAndTime tempAndTime;

	public CityInfo(City city, TempAndTime tempAndTime) {
		this.city = city;
		this.tempAndTime = tempAndTime;

	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public TempAndTime getTempAndTime() {
		return tempAndTime;
	}

	public void setTempAndTime(TempAndTime tempAndTime) {
		this.tempAndTime = tempAndTime;
	}

}
