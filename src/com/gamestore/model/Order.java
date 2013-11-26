package com.gamestore.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order extends DomainObject
{
	private int userID;
	
	public Order()
	{
		
	}
	
	public Order(ResultSet rs) throws SQLException
	{
		super(rs.getInt("OrderID"), Status.CLEAN);
		try 
		{
			this.setUserID(rs.getInt("userID"));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
