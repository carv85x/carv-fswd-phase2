package com.simplilearn.maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App 
{
	
	String qry;	
	static Connection dbCon;
	
	public App() {
		
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
	
    public static void main( String[] args )  {
        
    	Scanner sc = new Scanner(System.in);
    	
    	/*
    	
    	// --------- Select by Id --------//
    	System.out.println("Please enter the ID");
    	
    	@SuppressWarnings("resource")
		int id = sc.nextInt();
    	
    	new App().getLearnerDetailsById(id); 
    	
    	*/
    	
    	// --------- Insert --------------//
    	System.out.println("Please enter name:");
    	String name = sc.nextLine();
    	
    	System.out.println("Please enter address:");
    	String address = sc.nextLine();
    			
    	new App().addNewLearner(name, address);
    	new App().getAllRecords();    	
    
    	
    	// --------- Update -------------//
    	  	
    	System.out.println("Enter Id of user to update: ");
    	String id = sc.nextLine();    	
    	
    	
    	System.out.println("Enter new name: ");
    	name = sc.nextLine();
    	
    	System.out.println("Enter new address: ");
    	address = sc.nextLine();
    	
    	new App().updateLearner(Integer.parseInt(id), name, address);
    	new App().getAllRecords();
    	   	
    	
    	
    	// -------- Delete -----------//
    	System.out.println("Please enter Id of record to be deleted: ");
    	id = sc.nextLine();
    	new App().deleteLearner(Integer.parseInt(id));
    	new App().getAllRecords();

    }
    
    
    // Get all records from table
    void getAllRecords() {
    	// Write the query to fetch all rows from table
    	qry = "select * from learners";
    	
    	// Get a reference to the statement
    	try {
			Statement theStatement = dbCon.createStatement();
			
			// Execute the query
			ResultSet theResultSet = theStatement.executeQuery(qry);
			
			// Traverse through the results
			while(theResultSet.next()) {
				System.out.print("Name : " + theResultSet.getString("Name"));
				System.out.print(", ID : " + theResultSet.getString("Id"));
				System.out.println(", Address : " + theResultSet.getString("Address"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot create statement: " + e);
		}
    }
    
    // Get Learner details for a particular id
    void getLearnerDetailsById(int id) {
    	// Write the query to fetch details from the table
    	qry = "select * from learners where Id = " + id;
    	
    	// Get a reference to the statement
    	try {
			Statement theStatement = dbCon.createStatement();
			
			// Execute the query
			ResultSet theResultSet = theStatement.executeQuery(qry);
			
			// Traverse through the results
			while(theResultSet.next()) {
				System.out.print("Name : " + theResultSet.getString("Name"));
				System.out.print(", ID : " + theResultSet.getString("Id"));
				System.out.println(", Address : " + theResultSet.getString("Address"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot create statement: " + e);
		}
    }
    
    // Add a new record to the table
    void addNewLearner(String name, String address) {
    	// Write the query to fetch details from the table
    	qry = "insert into learners(Name, Address) values('" 
    			+ name 
    			+ "', '" 
    			+ address 
    			+ "')";
    	
    	// Get a reference to the statement
    	try {
			Statement theStatement = dbCon.createStatement();
			
			// Execute the query
			if(theStatement.executeUpdate(qry) > 0) {
				System.out.println("Record entered successfully");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot add to the table: " + e);
		}
    }
    
    // Add a new record to the table
    void updateLearner(int id, String name, String address) {
    	// Write the query to fetch details from the table
    	qry = "UPDATE learners SET Name ='" + name + "', Address = '" + address + "' WHERE Id = " + id;

    	// Get a reference to the statement
    	try {
			Statement theStatement = dbCon.createStatement();
			
			// Execute the query
			if(theStatement.executeUpdate(qry) > 0) {
				System.out.println("Record updated successfully");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot update record: " + e);
		}
    }
    

    // Add a new record to the table
    void deleteLearner(int id) {
    	// Write the query to fetch details from the table
    	qry = "DELETE FROM learners WHERE Id = " + id;

    	// Get a reference to the statement
    	try {
			Statement theStatement = dbCon.createStatement();
			
			// Execute the query
			if(theStatement.executeUpdate(qry) > 0) {
				System.out.println("Record deleted successfully");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot update record: " + e);
		}
    }
    
    
}
