package com.dos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class AirlineDO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NaturalId
	private String airlineId;	

	private String airlineName;	

	private String country;
	
	
	public AirlineDO() {}
	
	public AirlineDO(String airlineId, String airlineName, String country) {
		
		this.airlineId = airlineId;
		this.airlineName = airlineName;
		this.country = country;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public String getAirlineId() {
		return airlineId;
	}

	@Override
	public String toString() {
		return "AirlineDO [id=" + id + ", airlineId=" + airlineId + ", airlineName=" + airlineName + ", country="
				+ country + "]";
	}

	
	
	

}
