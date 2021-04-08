package com.model;

import java.sql.Date;
import java.util.List;
import java.util.Properties;

import javax.persistence.RollbackException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.dos.AirlineDO;
import com.dos.AirportDO;
import com.dos.FlightDO;
import com.dos.PassengerDO;
import com.dos.TripDO;
import com.dos.UserDO;

public class Manager {

	private Session session;
	private SessionFactory factory;
	
	public Manager() throws HibernateException{
		
		
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
	

	public void closeFactory() {
		// Close the factory
		factory.close();
	}
	
	
	public void closeSession() {
		// Close session
		session.close();
	}
	
	
	public void addRecord(Object obj) {
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(obj);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public Object getRecord(Object object, int id) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Get record from DB
		object = session.get(object.getClass(), id);
		
		// Close session
		session.close();
		
		return object;
	}	
	
	
	public void updateRecord(Object object) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.update(object);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public void deleteRecord(Object object) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.delete(object);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public void addAirline(AirlineDO airline) {
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(airline);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public AirlineDO getAirline(String airlineId) {
		
		// Declare return object
		AirlineDO airline; 
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		airline =  (AirlineDO) session.createQuery("FROM AirlineDO WHERE airlineId ='" + airlineId + "' ORDER BY airlineId").getSingleResult();
		
		// Close session
		session.close();
		
		return airline;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AirlineDO> getAirlines() {
		
		// Declare return object
		List<AirlineDO> airlines; 
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		airlines =  session.createQuery("FROM AirlineDO ORDER BY airlineId").getResultList();
		
		// Close session
		session.close();
		
		return airlines;
	}
	
	
	public void updateAirline(AirlineDO airline) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.update(airline);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public void deleteAirline(AirlineDO airline) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.delete(airline);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public void addAirport(AirportDO airport) {
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(airport);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AirportDO> getAirports() {
		
		// Declare return object
		List<AirportDO> airports; 
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		airports =  session.createQuery("FROM AirportDO ORDER BY airportId").getResultList();
		
		// Close session
		session.close();
		
		return airports;
	}	
	
		
	public AirportDO getAirportById(String airportId) {
		
		// Declare return object
		AirportDO airport;
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		airport =  (AirportDO) session.createQuery("FROM AirportDO WHERE airportId = '" + airportId + "'").getSingleResult();
		
		// Close session
		session.close();
		
		return airport;				
	}
	
	
	public void updateAirport(AirportDO airport) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.update(airport);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public void deleteAirport(AirportDO airport) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.delete(airport);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public void addFlight(FlightDO flight) {
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(flight);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public FlightDO getFlight(int id) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Get record from DB
		FlightDO flight = session.get(FlightDO.class, id);
		
		// Close session
		session.close();
		
		return flight;
	}
	
	
	public FlightDO getFlightByNo(String flightNo) {
		
		// Declare return object
		FlightDO flight; 
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		flight =  (FlightDO) session.createQuery("FROM FlightDO WHERE flightNo ='" + flightNo + "'").getSingleResult();
		
		// Close session
		session.close();
		
		return flight;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FlightDO> getFlights() {
		
		// Declare return list
		List<FlightDO> flights;
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Run query and save results
		flights = session.createQuery("FROM FlightDO ORDER BY flightNo").getResultList();		
		
		// Close session
		session.close();
		
		return flights;	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FlightDO> getFlights(String departure, String arrival, String dateDeparture, int numOfPassengers, boolean ignoreFullFlight) {
		
		// Declare return list
		List<FlightDO> flights;
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. query string
		String qry = "FROM FlightDO WHERE ";
		
		// Form full query 
		qry += "departure = '" + departure + "' ";
		qry += "AND arrival = '" + arrival + "' ";		
		qry += "AND dateDeparture = '" + dateDeparture + "' ";
		
		System.out.println("getFlights: " + qry);
		
		// Run query and save results
		flights = session.createQuery(qry).getResultList();		
		
		// Close session
		session.close();
		
		return flights;	
	}
	
		
	public void updateFlight(FlightDO flight) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.update(flight);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
		
	public void deleteFlight(FlightDO flight) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.delete(flight);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public void addPassenger(PassengerDO passenger) {
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(passenger);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public PassengerDO getPassenger(String passportNo) {
			
			// Declare return object
			PassengerDO passenger; 
			
			// Get a reference to the session
			session = factory.getCurrentSession();
			
			// Start the transaction
			session.beginTransaction();
			
			// Init. return object with query results
			passenger =  (PassengerDO) session.createQuery("FROM PassengerDO WHERE passportNo = '" + passportNo + "'").getSingleResult();
			
			// Close session
			session.close();
			
			return passenger;
	}
	
	
	public List<PassengerDO> getPassengersForConfirmation(String flightNo, String email) {
		
		// Declare return object
		List<PassengerDO> passengers; 
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		passengers =  session.createQuery("SELECT DISTINCT p FROM PassengerDO p, TripDO t WHERE p.passportNo = t.passportNo AND p.email ='" + email + "' AND t.flightNo = '" + flightNo + "'").getResultList();
		
		// Close session
		session.close();
		
		return passengers;
	}		
	
	
	@SuppressWarnings("unchecked")
	public List<PassengerDO> getPassengersByEmail(String email) {
		
		// Declare return object
		List<PassengerDO> passengers; 
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		passengers = session.createQuery("FROM PassengerDO WHERE email = '" + email + "'").getResultList();
		
		// Close session
		session.close();
		
		return passengers;
	}	
	
	
	public void updatePassenger(PassengerDO passenger) {
			
			// Get a reference to the session
			session = factory.getCurrentSession();
			
			// Start the transaction
			session.beginTransaction();
			
			// Update
			session.update(passenger);
			
			// Commit the transaction
			session.getTransaction().commit();
	}
	
	
	public void addTrip(TripDO trip) {
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(trip);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public List<TripDO> getTrips() {
		
		// Declare return object
		List<TripDO> trips; 
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		trips =  session.createQuery("FROM TripDO").getResultList();
		
		// Close session
		session.close();
		
		return trips;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TripDO> getTripsByUser(String email) {
			
			// Declare return object
			List<TripDO> trips;  
			
			// Get a reference to the session
			session = factory.getCurrentSession();
			
			// Start the transaction
			session.beginTransaction();
			
			// Init. return object with query results
			trips =  session.createQuery("SELECT DISTINCT t FROM TripDO t, PassengerDO p WHERE p.passportNo = t.passportNo AND p.email ='" + email + "' ORDER BY t.flightNo").getResultList();
			
			// Close session
			session.close();
			
			return trips;
	}	
	
	
	public boolean validateUser(String userId, String password) {
		
		// Set return flag
		boolean exists = false;
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Check whether record exists		
		if(session.createQuery("FROM UserDO WHERE userId = '" + userId + "' AND password = '" + password + "'").getResultList().size() > 0)
			exists = true;
		
		// Close session
		session.close();
		
		return exists;		
	}
	
	
	public void addUser(UserDO user) {
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Save record to the DB
		session.save(user);
		
		// Commit the transaction
		session.getTransaction().commit();
	}
	
	
	public UserDO getUser(String userId) {
		
		// Declare return object
		UserDO user; 
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Init. return object with query results
		user =  (UserDO) session.createQuery("FROM UserDO WHERE userId = '" + userId + "'").getSingleResult();
		
		// Close session
		session.close();
		
		return user;
	}
	
	
	public void updateUser(UserDO user) {
		
		// Get a reference to the session
		session = factory.getCurrentSession();
		
		// Start the transaction
		session.beginTransaction();
		
		// Update
		session.update(user);
		
		// Commit the transaction
		session.getTransaction().commit();
	}	

}
