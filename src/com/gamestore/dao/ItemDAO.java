package com.gamestore.dao;

import com.gamestore.model.Item;

public class ItemDAO extends DAO<Item>
{
	private static ItemDAO instance = null;
	
	public ItemDAO() 
	{
		super("Item", "ItemID", Item.class);
	}
	
	public static ItemDAO getInstance() 
	{
		if (instance == null) 
			instance =  new ItemDAO();
		return instance;
	}
	
	public boolean updateItem(int id, String name, double price, int quantity, 
			String description, String imgURL, int platform, int type)
	{
		String sql = "UPDATE `soen387k`.`Item` SET `name`='"+name+"', `price`='"+price+"', "
		 		+ "`quantity`='"+quantity+"', `description`='"+description+"', `imgURL`='"+imgURL+"',  "
		 				+ "`platform`='"+platform+"', `type`='"+type+"' WHERE `UserID`='"+id+"';";
		return executeSQLStatement(sql);
	}
	
	public boolean addItem(String name, double price, int quantity, String description, String imgURL, 
			int platform, int type)
	{
		String sql = "INSERT INTO `soen387k`.`Item` (`name`, `price`, `quantity`, `description`, `imgURL`, "
				+ "`platform`, `type`) VALUES ('"+name+"', '"+price+"', '"+quantity+"', '"+description+"', "
						+ "'"+imgURL+"', '"+platform+"', '"+type+"');";
		return executeSQLStatement(sql);
	}
}
