package com.gamestoreinventory.model;

import java.util.HashMap;

import com.gamestoreinventory.model.User;

public class LoginService 
{	
	private HashMap<String, User> users; 
	
	public LoginService()
	{
		users = new HashMap<String, User>(); 	
		getUsers();
	}
	
	public boolean authenticate(String username, String password)
	{
		User user = users.get(username);
		if (user != null)
		{
			if (user.getPassword().equals(password))
				return true;
		}
		return false;
	}
	
	public void getUsers()
	{
		// Placeholder for test
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		users.put("admin", user);
	}
}