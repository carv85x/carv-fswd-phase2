package com.dos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;

import org.hibernate.annotations.NaturalId;

@Entity
public class PassengerDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NaturalId
	private String passportNo;
	
	private String address;	
	private Date dateOfBirth;
	private String email;
	private String nameFirst;
	private String nameLast;
	private String gender;	
	
	public PassengerDO() {}
	
	public PassengerDO(String passportNo, String address, Date dateOfBirth, String email,
			String firstName, String lastName, String gender) {
		
		this.passportNo = passportNo;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.nameFirst = firstName;
		this.nameLast = lastName;
		this.gender = gender;
	}

	public String getPassportNo() {
		return passportNo;
	}	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return nameFirst;
	}

	public void setFirstName(String firstName) {
		this.nameFirst = firstName;
	}

	public String getLastName() {
		return nameLast;
	}

	public void setLastName(String lastName) {
		this.nameLast = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "PassengerDO [id=" + id + ", passportNo=" + passportNo + ", address=" + address + ", dateOfBirth="
				+ dateOfBirth + ", email=" + email + ", nameFirst=" + nameFirst + ", nameLast=" + nameLast + ", gender="
				+ gender + "]";
	}		

}
