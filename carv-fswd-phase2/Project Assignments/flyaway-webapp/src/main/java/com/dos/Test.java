package com.dos;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Test {
	
	Session session;	
	SessionFactory factory;
	
	public Test() {
		
		Configuration configuration = new Configuration();
		
		// Hibernate settings equivalent to hibernate.cfg.xml's properties
	    Properties settings = new Properties();
	    settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/flyaway");
	    settings.put(Environment.USER, "root");
	    settings.put(Environment.PASS, "");
	    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
	    settings.put(Environment.POOL_SIZE, 1);

	    settings.put(Environment.SHOW_SQL, "true");

	    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

	    settings.put(Environment.HBM2DDL_AUTO, "update");

	    configuration.setProperties(settings);
	    configuration	.addAnnotatedClass(AirlineDO.class)
						.addAnnotatedClass(AirportDO.class)
						.addAnnotatedClass(FlightDO.class)
						.addAnnotatedClass(PassengerDO.class)
						.addAnnotatedClass(TripDO.class)
						.addAnnotatedClass(UserDO.class);
	    
	    factory = configuration.buildSessionFactory();
	}
	
	public static void main(String[] args) {
		
		Test test = new Test();

		try {
			
			// ADD AirlineDO Record
			AirlineDO airline = new AirlineDO("AV", "Avianca", "Colombia");
			test.addAirlineDORecord(airline);
			
			// Add FlightDO Record
			FlightDO flight = new FlightDO(		"BOG"
											,	java.sql.Date.valueOf("2021-08-13")
											,	java.sql.Date.valueOf("2021-08-13")
											,	"BGA"
											,	"AV"
											,	150
											,	50.00
											,	java.sql.Time.valueOf("15:35:00")
											,	java.sql.Time.valueOf("16:15:00")
											);	
			
			
			System.out.println("Your flight is " + test.addFlightDORecord(flight).toString());
			
			
//			// Add PassengerDO Record
//			PassengerDO passenger = new PassengerDO(	"AR123456"
//													,	null
//													,	"123 Main St"
//													,	java.sql.Date.valueOf("1985-08-13")
//													,	"camilo@email.com"
//													,	"Camilo"
//													,	"Rodriguez"
//													,	"Male"
//													);
			

			
		} finally {
			test.factory.close();
		}
		
	}
	
	void addAirlineDORecord(AirlineDO airline) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();	
				
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(airline);
		
		// Commit the transaction
		session.getTransaction().commit();		
	}

	FlightDO addFlightDORecord(FlightDO flight) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();	
				
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(flight);
		
		// Commit the transaction
		session.getTransaction().commit();
		
		// Get a reference to the session
		session = factory.getCurrentSession();	
		
		// Start the transaction
		session.beginTransaction();
		
		// Get recently added record
		return (FlightDO) session.createQuery("FROM FlightDO F "
												+ "WHERE F.airlineId = '" + flight.getAirlineId() + "' "
												+ "AND F.arrival = '" + flight.getArrival() + "' "	
												+ "AND F.departure = '" + flight.getDeparture() + "' "
												+ "AND F.dateDeparture = '" + flight.getDateDeparture() + "' "
												+ "AND F.timeDeparture = '" + flight.getTimeDeparture() + "' ").getSingleResult();		
	}
}
