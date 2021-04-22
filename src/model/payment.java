package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {

	// A common method to connect to the DB
	private Connection connect() {

		Connection con = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "");
		} catch (Exception e) {
			e.printStackTrace();

		}

		return con;
	}

	// Add Payment
	public String addPayment(int Pay_id, int order_ID, String Cus_Name, String Card_No, String Bank, String Tot_Amount,
			String Exp_date, String CVV, String Payment_date) {

		String output = "";

		
		boolean validate = false;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
 
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment (`Pay_id`,`order_ID`,`Cus_Name`,`Card_No`,`Bank`,`Tot_Amount`,`Exp_date`,`CVV`,`Payment_date`)"
					+ " values (?, ?, ?, ?, ?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, 0);
			preparedStmt.setString(3, Cus_Name);
			preparedStmt.setString(4, Card_No);
			preparedStmt.setString(5, Bank);
			preparedStmt.setString(6, Tot_Amount);
			preparedStmt.setString(7, Exp_date);
			preparedStmt.setString(8, CVV);
			preparedStmt.setString(9, Payment_date);

			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// view payment

	public String readpayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Order ID</th><th>Customer Name</th><th>Card Number</th>"
					+ "<th>Bank</th>" + "<th>Total Amount</th>" + "<th>CVV</th>" + "<th>Expire Date</th>"
					+ "<th>Payment Date</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Pay_id = Integer.toString(rs.getInt("Pay_id"));
				String order_ID = Integer.toString(rs.getInt("order_ID"));
				String Cus_Name = rs.getString("Cus_Name");
				String Card_No = rs.getString("Card_No");
				String Bank = rs.getString("Bank");
				String Tot_Amount = rs.getString("Tot_Amount");
				String Exp_date = rs.getString("Exp_date");
				String CVV = rs.getString("CVV");
				String Payment_date = rs.getString("Payment_date");

				// Add into the html table
			
				output += "<td>" + order_ID + "</td>";
				output += "<td>" + Cus_Name + "</td>";
				output += "<td>" + Card_No + "</td>";
				output += "<td>" + Bank + "</td>";
				output += "<td>" + Tot_Amount + "</td>";
				output += "<td>" + Exp_date + "</td>";
				output += "<td>" + CVV + "</td>";
				output += "<td>" + Payment_date + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='Pay_id' type='hidden' value='" + Pay_id + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment";
			System.err.println(e.getMessage());
		}
		return output;
	}

//update Payment Details
	public String updatePayment(int Pay_id,int order_ID, String Cus_Name, String Card_No, String Bank, String Tot_Amount,String Exp_date , String CVV)
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE payment SET Pay_id=?,order_ID =?,Cus_Name=?,Card_No=?,Bank=?,Tot_Amount=?,Exp_date=?,CVV=?, WHERE Pay_id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Pay_id);
	 preparedStmt.setInt(2, order_ID);
	 preparedStmt.setString(3, Cus_Name); 
	 preparedStmt.setString(4, Card_No);
	 preparedStmt.setString(5, Bank);
	 preparedStmt.setString(6, Tot_Amount);
	 preparedStmt.setString(7, Exp_date);
	 preparedStmt.setString(8, CVV);

	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the Payment"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

//delete payment
	public String deletePayment(String ID) {

		String output = "";

		try {

			Connection con = connect();

			if (con == null) {

				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where Pay_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();

			con.close();

			output = "Deleted successfully";

		} catch (Exception e) {

			output = "Error while deleting the Payment Record.";
			System.err.println(e.getMessage());

		}

		return output;
	}

}
