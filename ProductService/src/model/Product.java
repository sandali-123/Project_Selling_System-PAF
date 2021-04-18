package model;
import java.sql.*;

public class Product {
	
	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget", "root", "sachi123");
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	 } 
	
	//insert product
	
	public String insertProduct(String Proj_type, String Proj_name, String Proj_link, String Proj_Desc, String Price,String Submit_date)
	{
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
			
			// create a prepared statement
			
			String query = " insert into projects (`Proj_id`,`Proj_type`,`Proj_name`,`Proj_link`,`Proj_Desc`,`Price`,`Submit_date`) values (?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Proj_type);
			preparedStmt.setString(3, Proj_name);
			preparedStmt.setString(4, Proj_link);
			preparedStmt.setString(5, Proj_Desc);
			preparedStmt.setString(6, Price);
			preparedStmt.setString(7, Submit_date);
			
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Product created successfully";
		}
		catch (Exception e)
		{
			output = "Error while creating the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//readProduct
	
	public String readItems()
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for reading."; 
		 }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>ProjectType</th><th>ProjectName</th>" +
		 "<th>ProjectLink</th>" +
		 "<th>ProjectDesc</th>" +
		 "<th>Price</th>" +
		 "<th>SubmitDate</th>" +
		 "<th>Update</th><th>Remove</th></tr>";
	
		 String query = "select * from projects";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String Proj_id = Integer.toString(rs.getInt("Proj_id"));
			 String Proj_type = rs.getString("Proj_type");
			 String Proj_name = rs.getString("Proj_name");
			 String Proj_link = rs.getString("Proj_link");
			 String Proj_Desc = rs.getString("Proj_Desc");
			 String Price = Double.toString(rs.getDouble("Price"));
			 String Submit_date = rs.getString("Submit_date");
			 
			 // Add into the html table
			 output += "<tr><td>" + Proj_type + "</td>";
			 output += "<td>" + Proj_name + "</td>";
			 output += "<td>" + Proj_link + "</td>";
			 output += "<td>" + Proj_Desc + "</td>";
			 output += "<td>" + Price + "</td>";
			 output += "<td>" + Submit_date + "</td>";
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
			 + "<input name='itemID' type='hidden' value='" + Proj_id
			 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	//update product
	public String updateProduct(String Proj_id,String Proj_type, String Proj_name, String Proj_link, String Proj_Desc, String Price,String Submit_date)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for updating."; 
			 }
			 
		 // create a prepared statement
			 
		 String query = "UPDATE projects SET Proj_type=?,Proj_name=?,Proj_link=?,Proj_Desc=?,Price=?,Submit_date=? WHERE Proj_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		
		 preparedStmt.setString(1, Proj_type);
		 preparedStmt.setString(2, Proj_name);
		 preparedStmt.setString(3, Proj_link);
		 preparedStmt.setString(4, Proj_Desc);
		 preparedStmt.setDouble(5, Double.parseDouble(Price));
		 preparedStmt.setString(6, Submit_date);
		 preparedStmt.setInt(7, Integer.parseInt(Proj_id));
		 
		
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	//delete product
	public String deleteItem(String ProjID)
	 {
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
	 // create a prepared statement
			
		 String query = "delete from projects where Proj_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 
		 preparedStmt.setInt(1, Integer.parseInt(ProjID));
	 
	 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the item.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	 } 

}
