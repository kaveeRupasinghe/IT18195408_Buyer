package com;
import java.sql.*;
public class Buyer {


private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con =
 DriverManager.getConnection(
 "jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb", "root", "");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }

public String readBuyers()
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
 output = "<table border='1'><tr><th>Buyer ID</th><th>Name</th><th>Address</th><th>Phone</th><th>Email</th><th>Project Name</th><th>Update</th><th>Remove</th></tr>";
 String query = "select * from buyers";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
	 String bno = Integer.toString(rs.getInt("bno"));
	 String buyerID = rs.getString("buyerID");
	 String name = rs.getString("name");
	 String address = rs.getString("address");
	 String phone = Integer.toString(rs.getInt("phone"));		
	 String email = rs.getString("email");
	 String projectName = rs.getString("projectName");
	// Add into the html table
	 output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + bno + "'>" + buyerID + "</td>";
	 output += "<td>" + name + "</td>";
	 output += "<td>" + address + "</td>";
	 output += "<td>" + phone + "</td>";
	 output += "<td>" + email + "</td>";
	 output += "<td>" + projectName + "</td>";
	 // buttons
	output += "<td><input name='btnUpdate'type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-bno='" + bno + "'>" + "</td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the buyer details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

public String insertBuyers(String buyerID, String name, String address, int phone, String email, String projectName)
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
	 String query = " insert into buyers(`bno`,`buyerID`,`name`,`address`,`phone`,`email`,`projectName`)"+ " values (?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, buyerID);
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, address);
	 preparedStmt.setInt(5, phone);
	 preparedStmt.setString(6, email);
	 preparedStmt.setString(7, projectName);


	// execute the statement
	 preparedStmt.execute();
	 con.close();

	 String newBuyers = readBuyers(); output = "{\"status\":\"success\", \"data\": \"" + newBuyers + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the buyer details.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 

public String updateBuyers(String bno, String buyerID,  String name, String address, int phone, String email, String projectName )

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
		 String query = "UPDATE buyers SET buyerID=?,name=?,address=?,phone=?,email=?,projectName=? WHERE bno=?";

		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, buyerID);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, address);			
		 preparedStmt.setInt(4, phone);
		 preparedStmt.setString(5, email);
		 preparedStmt.setString(6, projectName);
		 preparedStmt.setInt(7, Integer.parseInt(bno));
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 String newBuyers = readBuyers();
		 output = "{\"status\":\"success\", \"data\": \"" + newBuyers + "\"}";
		 }
		 catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the buyer details.\"}";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 


public String deleteBuyers(String bno)
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
String query = "delete from buyers where bno=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(bno));
// execute the statement
preparedStmt.execute();
con.close();
String newBuyers = readBuyers();
output = "{\"status\":\"success\", \"data\": \"" +	newBuyers + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
System.err.println(e.getMessage());
}
return output;
}
}