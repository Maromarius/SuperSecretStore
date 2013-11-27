package com.gamestore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.gamestore.model.Item;
import com.gamestore.model.Order;

public class OrderDAOTEMP extends DAO<Order>
{
	private static OrderDAOTEMP instance = null;
	
	private OrderDAOTEMP() 
	{
		super("soen387k.Order", "OrderID", Order.class);
	}
	
	public static OrderDAOTEMP getInstance() 
	{
		if (instance == null) 
			instance =  new OrderDAOTEMP();
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
	
	/*
	 *  Get list of items (with their details) for a given Order based on the OrderID (order number).
	 *  The key for this mapping is the quantity ordered, and the values are implemented as buckets
	 *  with the ordered Item objects as entries. 
	 */
	public HashMap<Integer, ArrayList<Item>> getOrderDetails(int id) 
	{
		HashMap<Integer, ArrayList<Item>> orderDetails = new HashMap<Integer, ArrayList<Item>>();
		try {
           Statement statement = getConnection().createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT Item.ItemID, name, price, Item.quantity, description, "
           		+ "imgURL, platform, type, OrderedItem.quantity AS q "
           		+ "FROM OrderedItem, Item Where OrderedItem.itemID=Item.ItemID AND orderId="+id+";");
           while (resultSet.next()) 
           {
        	   int quantityOrdered = resultSet.getInt("q");
        	   ArrayList<Item> list = orderDetails.get(quantityOrdered);
        	   if (list == null)
        	   {
        		   list = new ArrayList<Item>();
        		   orderDetails.put(quantityOrdered, list);
        	   }
        	   Item i = new Item(resultSet);
        	   list.add(i);
        	   orderDetails.put(resultSet.getInt("q"), list);
           }
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return orderDetails;
	}
}
