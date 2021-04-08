package com.dos;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

@Entity
public class FlightDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NaturalId
	private String flightNo;
	
	private String arrival;
	private Date dateArrival;
	private Date dateDeparture;
	private String departure;
	private String airlineId;
	private int passengerCapacity;
	private Double ticketPrice;
	private Time timeArrival;
	private Time timeDeparture;
	
	public FlightDO() {}
		
	public FlightDO(String arrival, Date dateArrival, Date dateDeparture,
			String departure, String airlineId, int passengerCapacity, Double ticketPrice, Time timeArrival,
			Time timeDeparture) {
		

		this.arrival = arrival;
		this.dateArrival = dateArrival;
		this.dateDeparture = dateDeparture;
		this.departure = departure;
		this.airlineId = airlineId;
		this.passengerCapacity = passengerCapacity;
		this.ticketPrice = ticketPrice;
		this.timeArrival = timeArrival;
		this.timeDeparture = timeDeparture;
	}	

	public int getId() {
		return id;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	
	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Date getDateArrival() {
		return dateArrival;
	}

	public void setDateArrival(Date dateArrival) {
		this.dateArrival = dateArrival;
	}

	public Date getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Time getTimeArrival() {
		return timeArrival;
	}

	public void setTimeArrival(Time timeArrival) {
		this.timeArrival = timeArrival;
	}

	public Time getTimeDeparture() {
		return timeDeparture;
	}

	public void setTimeDeparture(Time timeDeparture) {
		this.timeDeparture = timeDeparture;
	}

	public String getFlightNo() {
		return flightNo;
	}

	@Override
	public String toString() {
		return "FlightDO [id=" + id + ", flightNo=" + flightNo + ", arrival=" + arrival + ", dateArrival=" + dateArrival
				+ ", dateDeparture=" + dateDeparture + ", departure=" + departure + ", airlineId=" + airlineId
				+ ", passengerCapacity=" + passengerCapacity + ", ticketPrice=" + ticketPrice + ", timeArrival="
				+ timeArrival + ", timeDeparture=" + timeDeparture + "]";
	}
	
}
