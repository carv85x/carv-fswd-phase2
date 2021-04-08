package com.dos;

import java.sql.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TripDO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Date datePurchase;	
	private String flightNo;	
	private String passportNo;
	
	public TripDO() {}	

	public TripDO(Date datePurchase, String flightNo, String passportNo) {
		this.datePurchase = datePurchase;
		this.flightNo = flightNo;
		this.passportNo = passportNo;
	}

	public Date getDatePurchase() {
		return datePurchase;
	}

	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	@Override
	public String toString() {
		return "TripDO [id=" + id + ", datePurchase=" + datePurchase + ", flightNo=" + flightNo + ", passportNo="
				+ passportNo + "]";
	}	

}
