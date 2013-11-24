package com.gamestore.dao;

import com.gamestore.model.User;

public class UserDAO extends DAO<User>
{
	private static UserDAO instance = null;
	
	public UserDAO() 
	{
		super("User", "UserID", User.class);
	}
	
	public static UserDAO getInstance() 
	{
		if (instance == null) 
			instance =  new UserDAO();
		return instance;
	}	
	
	public boolean updateUser(int id, String name, String address, String phone, 
			String paymentType, String email, String password, boolean isAdmin)
	{
		String sql = "UPDATE `soen387k`.`User` SET `name`='"+name+"', `address`='"+address+"', "
		 		+ "`phone`='"+phone+"',  `paymentType`='"+paymentType+"',  `email`='"+email+"',  "
		 				+ "`password`='"+password+"',  `isAdmin`='"+(isAdmin?1:0)+"' WHERE `UserID`='"+id+"';";
		return executeSQLStatement(sql);
	}
	
	public boolean addUser(String name, String address, String phone, 
			String paymentType, String email, String password, boolean isAdmin)
	{
		String sql = "INSERT INTO `soen387k`.`User` (`name`, `address`, `phone`, `paymentType`, `email`, `password`, `isAdmin`) "
				+ "VALUES ('"+name+"', '"+address+"', '"+phone+"', '"+paymentType+"', '"+email+"', '"+password+"', '"+(isAdmin?1:0)+"');";
		return executeSQLStatement(sql);
	}
}
