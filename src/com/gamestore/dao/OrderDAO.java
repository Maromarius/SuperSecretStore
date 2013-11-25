package com.gamestore.dao;

import com.gamestore.model.Order;

public class OrderDAO extends DAO<Order>
{
	public OrderDAO(String tableName, String idName, Class<Order> clazz) 
	{
		super("Order", "OrderID", Order.class);
	}
	
	public boolean update(int id, int userID)
	{
		String sql = "UPDATE `soen387k`.`Order` SET `userID`='"+userID+"' WHERE `OrderID`='"+id+"';";
		return executeSQLStatement(sql);
	}
	
	public boolean add(int userID)
	{
		String sql = "INSERT INTO `soen387k`.`Order` (`userID`) VALUES ('"+userID+"');";
		return executeSQLStatement(sql);
	}
}
