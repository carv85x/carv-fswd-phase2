package maven.jdbc.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;


/*************************************************
 * Phase 2 - Become a Backend Expert
 * 
 * Class Assignment 1 - JDBC Maven Assignment
 * 
 * Author: Camilo Rodriguez
 * 
 * Created 'Driver' to implement JDBC using Maven
 *************************************************/
public class Driver {
	
	String qry;	
	static Connection dbCon;
	
	final int BY_F_NAME = 1;
	final int BY_L_NAME = 2;
	final int BY_ADDRESS = 3;
	
	final int SORT_BY_FNAME = 1;
	final int SORT_BY_LNAME = 2;
	final int SORT_BY_ADDRESS = 3;
	final int SORT_BY_ID = 4;
	
	public Driver() {
		
		// Establish connection to DB
		
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
		
		Driver app = new Driver();
		
		// Scanner object
		Scanner sc = new Scanner(System.in);
		
		// Vars for input
		String fName;
		String lName;
		String address;
		int id;
		int type;
		
		// Call main menu
		app.showMenu();
		
		String input = sc.nextLine();
		
		int ans = Integer.parseInt(input);
		
		while(ans != 7) {
			
			switch(ans) {
				case 1:
					System.out.println("Enter First Name, Last Name, and Address:");
					fName = sc.nextLine();
					lName = sc.nextLine();
					address = sc.nextLine();
					app.addRecord(fName, lName, address);
					app.showMenu();
					break;
				
				case 2:
					app.displayResultSet(app.getRecords());
					app.showMenu();
					break;
					
				case 3:
					app.showSearchMenu();
					type = Integer.parseInt(sc.nextLine());
					
					System.out.println("Enter value to search: ");
					String text = sc.nextLine();
					
					app.displayResultSet(app.getRecord(type, text));
					app.showMenu();					
					break;
					
				case 4:
					System.out.println("Enter ID of record to update:");
					id = Integer.parseInt(sc.nextLine());
					
					System.out.println("Enter new First Name, Last Name, and Address: ");
					fName = sc.nextLine();
					lName = sc.nextLine();
					address = sc.nextLine();
					app.updateRecord(fName, lName, address, id);
					app.showMenu();	
					break;
					
				case 5:
					System.out.println("Enter ID of record to delete:");
					id = Integer.parseInt(sc.nextLine());
					
					app.deleteRecord(id);
					app.showMenu();
					break;
					
				case 6:
					app.showSortMenu();
					type = Integer.parseInt(sc.nextLine());
					app.sortRecords(type, app.getRecords());
					app.showMenu();
					break;
					
				default:
					break;
				
			}
			
			input = sc.nextLine();			
			ans = Integer.parseInt(input);			
		}		
		
	}
	
	
	void showMenu() {
		
		System.out.println("\n--------JDBC App---------");
		System.out.println("1. Enter new learner details");
		System.out.println("2. Display all learner details");
		System.out.println("3. Search for a particular learner by Name/Address");
		System.out.println("4. Update learner details");
		System.out.println("5. Delete learner");
		System.out.println("6. Sort Learners");
		System.out.println("7. Exit");
	}
	
	
	void showSearchMenu() {
		System.out.println("Search by:");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Address");
	}
	
	
	void showSortMenu() {
		
		System.out.println("Sort by:");
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Address");
		System.out.println("4. ID");
	}	
	
	
	void addRecord(String fName, String lName, String address) {
		// Write the query to insert
		qry = "INSERT INTO learners(firstName, lastName, Address) values(?, ?, ?)";
		
		// Get a reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(qry);
			
			// Set the values for ?
			thePreparedStatement.setString(1, fName);
			thePreparedStatement.setString(2, lName);
			thePreparedStatement.setString(3, address);
			
			// Execute the query
			if(thePreparedStatement.executeUpdate() > 0)
				System.out.println("Record added...");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to the PreparedStatement : " + e);
		}
	}
	
	ResultSet getRecords() {
		// Write the query to select *
		qry = "SELECT * FROM learners";
		
		// Get a reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(qry);
			
			// Execute the query
			return thePreparedStatement.executeQuery();				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to the PreparedStatement : " + e);
		}
		
		return null;
	}
	
	
	// Get record by either first name, last name, or address
	ResultSet getRecord(int type, String text) {
		// Write the query to select *
		qry = "SELECT * FROM learners ";
		
		if(type == BY_F_NAME) {
			qry += "WHERE firstName = ?";
		} else if(type == BY_L_NAME) {
			qry += "WHERE lastName = ?";
		} else if(type == BY_ADDRESS) {
			qry += "WHERE address = ?";
		} else {
			System.out.println("Invalid search type");
		}
		
		
		// Get a reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(qry);
			
			// Set the values for ?
			thePreparedStatement.setString(1, text);

			// Execute the query
			return thePreparedStatement.executeQuery();		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to the PreparedStatement : " + e);
		}
			
		return null;

	}
	
	// Update all fields using ID
	void updateRecord(String fName, String lName, String address, int id) {
		
		// Write the query to update
		qry = "UPDATE learners SET FirstName = ?, LastName = ?, Address = ? WHERE id = ?";
		
		// Write the PreparedStatement
		try {
			PreparedStatement thePreparedStatement = dbCon.prepareStatement(qry);
			
			// Set the values for ?
			thePreparedStatement.setString(1, fName);
			thePreparedStatement.setString(2, lName);
			thePreparedStatement.setString(3, address);
			thePreparedStatement.setInt(4, id);
			
			// Execute the query
			if(thePreparedStatement.executeUpdate() > 0)
				System.out.println("Record has been updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to the PreparedStatement : " + e);
		}
		
	}
	
	// Delete record using ID
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
		
	}
	
	void displayResultSet(ResultSet rs) {
		try {
			while(rs.next()) {
				System.out.print("ID : " + rs.getInt("Id"));
				System.out.print(", First Name : " + rs.getString("FirstName"));
				System.out.print(", Last Name : " + rs.getString("LastName"));
				System.out.print(", Address : " + rs.getString("Address"));
				System.out.println("\n");
				
			}
		} catch (SQLException e) {
			System.out.println("Unable to review Result Set: " + e);
		}
	}
	
	// Sort records by selected column
	void sortRecords(int sortBy, ResultSet rs) {
		// Use TreeMap to sort records by default
		Map<String, String> tm = new TreeMap<String, String>();
		String sortKey = "";
		
		// Set value for sortKey
		if(sortBy == SORT_BY_FNAME) {
			sortKey = "FirstName";	
		} else if(sortBy == SORT_BY_LNAME) {
			sortKey = "LastName";
		} else if(sortBy == SORT_BY_ADDRESS) {
			sortKey = "Address";
		}
		
		// Populate TreeMap
		try {
			while(rs.next()) {
				
				String learner 	= "Id: " 		+ rs.getInt("Id") 			+ ", "
								+ "FirstName: " + rs.getString("FirstName")	+ ", "
								+ "LastName: "	+ rs.getString("LastName")	+ ", "
								+ "Address: "	+ rs.getString("Address");
				
				if(sortBy == SORT_BY_ID)				
					tm.put(Integer.toString(rs.getInt("Id")), learner);
				else
					tm.put(rs.getString(sortKey), learner);					
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to review Result Set: " + e);
		}
		
		// Display sorted result set
		for(Entry<String, String> e:tm.entrySet()) {
			System.out.println(e.getValue());			
		}
	}
	

	
	
}
