package com.gamestore.dao;

import com.gamestore.model.DomainObject;

public class AbstractMapper {
	
	private static AbstractMapper instance;
	
	
	public static AbstractMapper getInstance() {
		if (instance == null) instance =  new AbstractMapper();
		
		return instance;
	}
	


}
