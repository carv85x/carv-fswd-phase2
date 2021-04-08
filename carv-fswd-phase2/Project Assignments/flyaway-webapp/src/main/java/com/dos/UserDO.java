package com.dos;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class UserDO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NaturalId
	private String userId;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private String type;	
	
	public UserDO() {}

	public UserDO(String userId, String email, String password, String type) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "UserDO [id=" + id + ", userId=" + userId + ", email=" + email + ", password=" + password + ", type="
				+ type + "]";
	}

}
