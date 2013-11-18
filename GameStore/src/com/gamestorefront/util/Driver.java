/*
 * Run as a Java application if you want to test queries 
 * or manipulate database schema.
 */
package com.gamestorefront.util;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Driver 
{
	public static void main(String[] args)
	{
		Connection connection = DatabaseConnection.getConnection();

		if (connection != null) 
		{
			System.out.println("Database connection success!");
			Scanner keyboard = new Scanner(System.in);
			String sql = null;
			while (true)
			{
				System.out.println("\nEnter your query ('$' to exit): ");
				sql = keyboard.nextLine().trim();
				
				if (sql.equals("$"))
					break;
				
				try 
				{
					Statement statement = connection.createStatement();
					statement.execute(sql);
					System.out.println("Your SQL statement was successfully processed.");
				} catch (Exception e)
				{
					System.out.println("Error encountered while processing your statement. Please try again.");
				}
			}
			DatabaseConnection.close(null, null, connection);
			System.out.println("Exiting...");
			keyboard.close();
			System.exit(0);
		} 
		else 
		{
			System.out.println("Database connection failed!");
		}
	}
}