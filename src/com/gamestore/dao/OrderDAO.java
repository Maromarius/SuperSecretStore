package com.gamestore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.gamestore.model.Item;
import com.gamestore.model.Order;

public class OrderDAO extends DAO<Order>
{
	private static OrderDAO instance = null;
	
	private OrderDAO() 
	{
		super("soen387k.Order", "OrderID", Order.class);
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
	
	// get list of ordered items & their details for a given Order based on the OrderID (order number)
	public HashMap<Integer, Item> getOrderDetails(int id) 
	{
		HashMap<Integer, Item> orderDetails = new HashMap<Integer, Item>();
		try 
		{
           Statement statement = getConnection().createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * FROM OrderedItem, Item "
           		+ "WHERE OrderedItem.itemID=Item.ItemID AND OrderedItem.orderId="+id+";");
           while (resultSet.next()) 
        	   orderDetails.put(resultSet.getInt("OrderID"));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return orderDetails;
	}
}
