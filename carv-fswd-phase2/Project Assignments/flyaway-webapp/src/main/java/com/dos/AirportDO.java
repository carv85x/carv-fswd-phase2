package com.dos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class AirportDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NaturalId
	private String airportId;
	
	private String airportName;
	private String city;
	private String country;
	
	public AirportDO() {}	

	public AirportDO(String airportId, String airportName, String city, String country) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.city = city;
		this.country = country;
	}

	public String getAirportId() {
		return airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AirportDO [id=" + id + ", airportId=" + airportId + ", airportName=" + airportName + ", city=" + city
				+ ", country=" + country + "]";
	}

	
	
}
