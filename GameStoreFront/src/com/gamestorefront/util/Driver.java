package com.gamestorefront.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver 
{
	public static void main(String[] args) throws SQLException 
	{
		Connection connection = DatabaseConnection.getConnection();

		if (connection != null) 
		{
			System.out.println("Connection : true");
			
			Statement statement = null;
			try 
			{
				statement = connection.createStatement();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
			String sql = "Select * From Person";
			System.out.println(statement.execute(sql));
			
			DatabaseConnection.close(null, null, connection);
		} 
		else 
		{
			System.out.println("Connection : false");
		}
	}
}