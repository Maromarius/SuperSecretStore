package com.gamestore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gamestore.model.User;

public class UserDAO extends DAO<User>
{
	private static UserDAO instance = null;
	
	private UserDAO() 
	{
		super("User", "UserID", User.class);
	}
	
	public static UserDAO getInstance() 
	{
		if (instance == null) 
			instance =  new UserDAO();
		return instance;
	}	
	
	public boolean update(int id, String name, String address, String phone, 
			String paymentType, String email, String password, boolean isAdmin)
	{
		String sql = "UPDATE `soen387k`.`User` SET `name`='"+name+"', `address`='"+address+"', "
		 		+ "`phone`='"+phone+"',  `paymentType`='"+paymentType+"',  `email`='"+email+"',  "
		 				+ "`password`='"+password+"',  `isAdmin`='"+(isAdmin?1:0)+"' WHERE `UserID`='"+id+"';";
		return executeSQLStatement(sql);
	}
	
	public boolean add(String name, String address, String phone, 
			String paymentType, String email, String password, boolean isAdmin)
	{
		String sql = "INSERT INTO `soen387k`.`User` (`name`, `address`, `phone`, `paymentType`, `email`, `password`, `isAdmin`) "
				+ "VALUES ('"+name+"', '"+address+"', '"+phone+"', '"+paymentType+"', '"+email+"', '"+password+"', '"+(isAdmin?1:0)+"');";
		return executeSQLStatement(sql);
	}
	
	public ArrayList<Integer> getOrderList(int id) // get a User's list of orders based on his/her UserID (record ID)
	{
		ArrayList<Integer> orderList = new ArrayList<Integer>();
		try {
           Statement statement = getConnection().createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT OrderID FROM soen387k.Order WHERE Order.userID="+id+";");
           while (resultSet.next()) 
        	   orderList.add(resultSet.getInt("OrderID"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return orderList;
	}
}
