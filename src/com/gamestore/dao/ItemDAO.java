package com.gamestore.dao;

import com.gamestore.model.Item;

public class ItemDAO extends DAO<Item>
{
	private static ItemDAO instance = null;
	
	private ItemDAO() 
	{
		super("Item", "ItemID", Item.class);
	}
	
	public static ItemDAO getInstance() 
	{
		if (instance == null) 
			instance =  new ItemDAO();
		return instance;
	}
	
	public boolean update(int id, String name, double price, int quantity, 
			String description, String imgURL, String platform, String type)
	{
		String sql = "UPDATE `soen387k`.`Item` SET `name`='"+name+"', `price`='"+price+"', "
		 		+ "`quantity`='"+quantity+"', `description`='"+description+"', `imgURL`='"+imgURL+"',  "
		 				+ "`platform`='"+platform+"', `type`='"+type+"' WHERE `ItemID`='"+id+"';";
		return executeSQLStatement(sql);
	}
	
	public boolean update(Item item)
	{
		String sql = "UPDATE `soen387k`.`Item` SET `name`='"+item.getName()+"', `price`='"+item.getPrice()+"', "
		 		+ "`quantity`='"+item.getQuantity()+"', `description`='"+item.getDescription()+"', `imgURL`='"+item.getImgUrl()+"',  "
		 				+ "`platform`='"+item.getPlatform()+"', `type`='"+item.getType()+"' WHERE `ItemID`='"+item.getID()+"';";
		return executeSQLStatement(sql);
	}
	
	public boolean add(String name, double price, int quantity, String description, String imgURL, 
			String platform, String type)
	{
		String sql = "INSERT INTO `soen387k`.`Item` (`name`, `price`, `quantity`, `description`, `imgURL`, "
				+ "`platform`, `type`) VALUES ('"+name+"', '"+price+"', '"+quantity+"', '"+description+"', "
						+ "'"+imgURL+"', '"+platform+"', '"+type+"');";
		return executeSQLStatement(sql);
	}
	
	public boolean addbyObject(Item item)
	{
		String sql = "INSERT INTO `soen387k`.`Item` (`name`, `price`, `quantity`, `description`, `imgURL`, "
				+ "`platform`, `type`) VALUES ('"+item.getName()+"', '"+item.getPrice()+"', '"+item.getQuantity()+"', '"+item.getDescription()+"', "
						+ "'"+item.getImgUrl()+"', '"+item.getPlatform()+"', '"+item.getType()+"');";
		return executeSQLStatement(sql);
	}
}
