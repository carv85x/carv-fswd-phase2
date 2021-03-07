package com.simplilearn.maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsingPreparedStatement {
	
	String qry;	
	static Connection dbCon;
	
	public UsingPreparedStatement() {
		
		try {
    		// Define the URL to connect
    		String urlToConnect = "jdbc:mysql://localhost:3306/phase2";
    		
    		// Define the username for db to connect
    		String dbUserName = "root";
    		
    		// Define the password
    		String dbUserPassword = "";
    		
    		// Define the Driver
    		String mySqlDriver = "com.mysql.cj.jdbc.Driver";
    		
    		// Load the Driver
			Class.forName(mySqlDriver);
			
			// Try to establish the connection	    	
			dbCon = DriverManager.getConnection(urlToConnect, dbUserName, dbUserPassword);
			
			System.out.println("Successfully connected to the DB");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't load driver: " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't connect to DB: " + e);
		}		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		/*
		System.out.println("Please enter the name and address: ");
		
		String name = sc.nextLine();
		
		String address = sc.nextLine();
		
		new UsingPreparedStatement().addNewLearner(name, address);
	
		
		System.out.println("Please enter id :");
		String id = sc.nextLine();
		
		System.out.println("Before Update");
		new UsingPreparedStatement().getDetailsById(Integer.parseInt(id));
		
		System.out.println("Please enter the name and address: ");
		
		String name = sc.nextLine();
		
		String address = sc.nextLine();
		
		new UsingPreparedStatement().updateDetailsById(Integer.parseInt(id), name, address);
		
		System.out.println("After Update");		
		
		new UsingPreparedStatement().getDetailsById(Integer.parseInt(id));
		
		*/
		System.out.println("Enter record to delete");
		
		System.out.println("Please enter id :");
		String id = sc.nextLine();
		
		new UsingPreparedStatement().deleteRecord(Integer.parseInt(id));
		
	}
	
	// Insert a new record in the table using PreparedStatement
	void addNewLearner(String name, String address) {
		// Write the query to insert
		qry = "INSERT INTO learners(Name, Address) values(?, ?)";
		
		// Get a reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(qry);
			
			// Set the values for ?
			thePreparedStatement.setString(1, name);
			thePreparedStatement.setString(2, address);
			
			// Execute the query
			if(thePreparedStatement.executeUpdate() > 0)
				System.out.println("Record added...");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to the PreparedStatement : " + e);
		}
	}
	
	
	// Fetch all details by id
	void getDetailsById(int id) {
		// Write the query to select *
		qry = "select * from learners where Id = ?";
		
		// Get a reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(qry);
			
			// Set the values for ?
			thePreparedStatement.setInt(1, id);

			// Execute the query
			ResultSet theResultSet = thePreparedStatement.executeQuery();
			
			// Review result
			while(theResultSet.next()) {
				System.out.print("Name : " + theResultSet.getString("name"));
				System.out.println(", Address : " + theResultSet.getString("address"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to the PreparedStatement : " + e);
		}		
	}
	
	// Update details for user
	void updateDetailsById(int id, String name, String address) {
		// Write the query to update
		qry = "UPDATE learners SET Name = ?, Address = ? WHERE id = ?";
		
		// Write the PreparedStatement
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(qry);
			
			// Set the values for ?
			thePreparedStatement.setString(1, name);
			thePreparedStatement.setString(2, address);
			thePreparedStatement.setInt(3, id);
			
			// Execute the query
			if(thePreparedStatement.executeUpdate() > 0)
				System.out.println("Record has been updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to the PreparedStatement : " + e);
		}
		
	}
	
	// Delete record by id
	void deleteRecord(int id) {
		// Write query to delete
		qry = "DELETE FROM learners WHERE Id = ?";
		
		// Write the PreparedStatement
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(qry);
			
			// Set the value for ?
			thePreparedStatement.setInt(1, id);
			
			// Execute the query
			if(thePreparedStatement.executeUpdate() > 0)
				System.out.println("Record has been deleted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to the PreparedStatement :" + e);
		}
		
		//
		
	}
	
	
}
