package servermodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Billing {
	//DB Connection
	private Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eginv", "root", "");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	//Read method
	public String readBilling()
	{
		String output = "";

		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error occured when connecting to the DB for Reading";
			}

			//html table 
			output = "<table border='1'><tr><th>Billing Code</th><th>Meter ID</th><th>User ID</th>" 
					+ "<th>Usege</th><th>Total</th><th>Status</th><th>Created On</th><th>Updated On</th><th>Update</th><th>Remove</th></tr>";

			output = "<div class=''><table class='table table-hover table-bordered table-striped table-bordered' style='width:100%' style='text-align:center'><thead class='thead-dark'>"
					+ "<th style='padding:10px; text-align:center;'>Billing Code</th>"
					+ "<th style='padding:10px; text-align:center;'>Meter ID</th>"
					+ "<th style='padding:10px; text-align:center;'>User ID</th>"
					+ "<th style='padding:10px; text-align:center;'>Usege</th>"
					+ "<th style='padding:10px; text-align:center;'>Total</th>"
					+ "<th style='padding:10px; text-align:center;'>Status</th>"
					+ "<th style='padding:10px; text-align:center;'>Created On</th>"
					+ "<th style='padding:10px; text-align:center;'>Updated On</th>"
					+ "<th style='padding:10px; text-align:center;'>Update</th><th>Remove</thead></tr>";
			String query = "select * from billing";

			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);

			// iterate through the result set
			while (result.next())
			{
				String billID = Integer.toString(result.getInt("billID"));
				String billing_id = result.getString("billing_id");
				String mc_id = result.getString("mc_id");
				String user_id = result.getString("user_id");
				String usege = Integer.toString(result.getInt("usege"));
				String total = Integer.toString(result.getInt("total"));
				String status = result.getString("status");
				String created = result.getString("created");
				String updated = result.getString("updated");
				

				// Add into the html table							
				output += "<tbody style='padding:10px; text-align:center;'><td ><input id='hidBillIDUpdate' name='hidBillIDUpdate' type='hidden' value='" + billID + "'>" + billing_id + "</td>";
				output += "<td>" + mc_id + "</td>";
				output += "<td>" + user_id + "</td>";		
				output += "<td>" + usege + "</td>";	
				output += "<td>" + total + "</td>";
				output += "<td>" + status + "</td>";
				output += "<td>" + created + "</td>";
				output += "<td>" + updated + "</td>";	
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-billid='" + billID + "'></td>" + 
						"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billid='" + billID + "'> </td></tbody>";
			}
			con.close();

			// Closing the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error occured when reading the billing service.";
			System.err.println(e.getMessage());
		}

		return output;
	}


	//Insert method
	public String insertBilling(String billing_id, String mc_id, String user_id, String usege, String total, String status)
	{

		String output = "";

		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error occured when connecting to the DB for inserting.";
			}

			// Sql query
			String query = " insert into billing (`billing_id`,`mc_id`,`user_id`,`usege`,`total`,`status`)" + " values (?, ?, ?, ? ,? ,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, billing_id);
			preparedStmt.setString(2, mc_id);
			preparedStmt.setString(3, user_id);
			preparedStmt.setInt(4, Integer.parseInt(usege));
			preparedStmt.setInt(5, Integer.parseInt(total));
			preparedStmt.setString(6, status);
			
			// execute the preparedStatement
			preparedStmt.execute();
			
			con.close();
			
			String newBill = readBilling();			
			output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
			
		}
		catch (Exception e)
		{
			
			output = "{\"status\":\"error\", \"data\":\"Error while inserting a billing record.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	//update method
	public String updateBilling(String billID, String billing_id, String mc_id, String user_id, String usege, String total, String status)
	{
		
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error occured when connecting to the DB for updating.";
			}
			
			// sql query
			String query = "UPDATE billing SET billing_id=?,mc_id=?,user_id=?,usege=?,total=?,status=? WHERE billID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, billing_id);
			preparedStmt.setString(2, mc_id);
			preparedStmt.setString(3, user_id);
			preparedStmt.setInt(4, Integer.parseInt(usege));
			preparedStmt.setInt(5, Integer.parseInt(total));
			preparedStmt.setString(6, status);
			preparedStmt.setInt(7, Integer.parseInt(billID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newBill = readBilling();
			output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating a billing record.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//delete method
	public String deleteBilling(String billID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error occured when connecting to the DB for deleting.";
			}
			// sql query
			String query = "DELETE FROM `billing` WHERE billID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newBill = readBilling();
			output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting a billing record.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
