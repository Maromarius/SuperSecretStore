package com.gamestore.dao;

import java.util.HashMap;
import java.util.Map;

import com.gamestore.model.Item;

public class ItemDao {
	
	
private static ItemDao instance = null;
	
	public static ItemDao getInstance() {
		if (instance == null) instance =  new ItemDao();
		
		return instance;
	}
	
	
	
	

}
