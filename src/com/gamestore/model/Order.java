package com.gamestore.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order 
{
	private int id, userID;
	
	public Order()
	{
		
	}
	
	public Order(ResultSet rs)
	{
		try 
		{
			this.setId(rs.getInt("OrderID"));
			this.setUserID(rs.getInt("userID"));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
