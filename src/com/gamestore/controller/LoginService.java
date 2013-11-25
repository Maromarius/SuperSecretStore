package com.gamestore.controller;

import com.gamestore.dao.UserDAO;

public class LoginService 
{
	private UserDAO dao;
	private static LoginService instance;
	
	public LoginService()
	{
		dao = new UserDAO();
	}
	
	public static LoginService getInstance() 
	{
    	if(instance == null)
    	{
    		instance = new LoginService();
    	}
        return instance;
    }
	
	public boolean authenticateUser(String username, String password)
	{
		// TODO: perform actual validation here.
		return true;
	}
}
