package com.gamestore.dao;

import com.gamestore.model.Order;

public class OrderDAO extends DAO<Order>
{
	private static OrderDAO instance = null;
	
	private OrderDAO() 
	{
		super("Order", "OrderID", Order.class);
	}
	
	public static OrderDAO getInstance() 
	{
		if (instance == null) 
			instance =  new OrderDAO();
		return instance;
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
