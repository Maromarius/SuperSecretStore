package com.gamestore.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamestore.dao.OrderDAORAMI;

public class Order extends DomainObject
{
	private int userID;
	private List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
	private User user;
	private String orderStatus;
	
	public Order(int id, List<OrderedItem> orderItems, String orderStatus, User user)
	{
		super(id, Status.CLEAN);
		this.orderedItems = orderItems;
		this.user = user;
		this.orderStatus = orderStatus;
	}
	
	public Order(ResultSet rs) throws SQLException
	{
		super(rs.getInt("OrderID"), Status.CLEAN);
		try 
		{
			this.setUserID(rs.getInt("userID"));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public List<OrderedItem> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(List<OrderedItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getOrderStatus() {
		return this.orderStatus;
		
	}
	
	public String setOrderStatus(String _status) {
		return this.orderStatus = _status;
		
	}
	
	public void updateItemPrice(int index, double price)
	{
		
		// Manage Lock Item in DB
		orderedItems.get(index).setPrice(price);
		OrderDAORAMI.getInstance().update(this);
	}
	
	public boolean updateItemQty(int index, int qty)
	{
		int diff = 0;
		if (OrderDAORAMI.getInstance().checkQuantities(qty, orderedItems.get(index), orderedItems.get(index).getItemID()))
		{
			return true;
		}
		else
		{
			diff = qty - orderedItems.get(index).getQuantity();
		}
		orderedItems.get(index).setQuantity(qty);
		OrderDAORAMI.getInstance().updateOrderItem(this, index, diff);
		return false;
	}
	
	public void deleteItem(int index)
	{
		OrderDAORAMI.getInstance().deleteOrderItem(this.getID(), orderedItems.get(index));
		orderedItems.remove(index);
		if (orderedItems.isEmpty())
		{
			OrderDAORAMI.getInstance().delete(this.getID());
		}
	}
	
	public void delete()
	{
		Iterator<OrderedItem> it = orderedItems.iterator();
		while(it.hasNext())
		{
			OrderedItem oi = it.next();
			OrderDAORAMI.getInstance().deleteOrderItem(this.getID(), oi);
			it.remove();
		}
		OrderDAORAMI.getInstance().delete(this.getID());

	}
	
	public void cleanup()
	{
		Iterator<OrderedItem> it = orderedItems.iterator();
		while(it.hasNext())
		{
			OrderedItem oi = it.next();
			if (oi.getQuantity() == 0)
			{
				OrderDAORAMI.getInstance().deleteOrderItem(this.getID(), oi);
				it.remove();
			}
		}
		if (this.isEmpty())
		{
			OrderDAORAMI.getInstance().delete(this.getID());
		}
	}
	
	public boolean isEmpty()
	{
		return orderedItems.isEmpty();
	}
	
	public double getTotal() {
		double total = 0;
		for ( OrderedItem oi : orderedItems)
		{
			total += oi.getPrice() * oi.getQuantity();
		}
		return total;
	}
	
}


