package com.model;

import java.util.List;

import com.dos.AirlineDO;

public class ManagerTest {
	
	public static void main(String[] args) {
		// Init. Manager instance
		Manager manager = new Manager();
	
		// Init. airline list
		List<AirlineDO> airlines = manager.getAirlines();
		
		for(AirlineDO airline : airlines) {
			System.out.println(airline);
		}	
	}
	
//	public static void main(String[] args) {
//		System.out.println(System.getProperty("user.dir")); 
//	}
}
