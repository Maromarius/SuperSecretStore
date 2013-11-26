package com.gamestore.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.gamestore.model.User;
import com.gamestore.util.DatabaseConnection;

public class LoginGateway {
	
	
	private static LoginGateway instance = null;
	
	private LoginGateway(){};
	
	public static LoginGateway getInstance(){
		
		if(instance == null){
			instance = new LoginGateway();
		}
		return instance;	
	}
	
	public User login(String em, String pwd){
		
		String query = "select * from User where email='" + em + "' and password='" + pwd + "'";
		try 
		{
		ResultSet rs = DatabaseConnection.getInstance().ExecuteQuery(query);
		
		if(rs == null)
			return null;
		
		rs.next();
		int id = rs.getInt("UserID");
		String name = rs.getString("name");
		String address = rs.getString("name");
		String phone = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		int isAdmin = rs.getInt("isAdmin");
		return new User(name,address,phone,email,password,isAdmin,id);
		
		
		} catch (SQLException e) 
		{
			return null;
		}
			
			
		}
	
	public User getUser(int ID){
		
		String query = "select * from User where id ='" + ID + "'";
		try 
		{
		ResultSet rs = DatabaseConnection.getInstance().ExecuteQuery(query);
		
		if(rs == null)
			return null;
		
		rs.next();
		int id = rs.getInt("UserID");
		String name = rs.getString("name");
		String address = rs.getString("name");
		String phone = rs.getString("name");
		String email = rs.getString("email");
		String password = rs.getString("password");
		int isAdmin = rs.getInt("isAdmin");
		return new User(name,address,phone,email,password,isAdmin,id);
		
		
		} catch (SQLException e) 
		{
			return null;
		}
			
			
		}
	
		
	}



