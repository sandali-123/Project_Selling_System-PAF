package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Users {
	
	
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/GadgetBadget","root","");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	

	//insert Users
	public String insertUser(String fname, String lname, String nic, String address, String phone,String email,String username, String password,String type)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into users (`U_id`,`fname`,`lname`,`nic`,`address`,`phone`,`email`,`username`,`password`,`type`)"
	 + " values (?, ?, ?, ?, ?,?,?,?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, fname);
	 preparedStmt.setString(3, lname);
	 preparedStmt.setString(4, nic);
	 preparedStmt.setString(5, address);
	 preparedStmt.setString(6, phone);
	 preparedStmt.setString(7, email);
	 preparedStmt.setString(8, username);
	 preparedStmt.setString(9, password);
	 preparedStmt.setString(10, type);
	// execute the statement
	 
	 
	 preparedStmt.execute();
	 con.close();
	 output = "User created successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while creating the user.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	
	
	

}
