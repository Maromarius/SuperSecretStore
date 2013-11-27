package com.gamestore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gamestore.controller.LoginGateway;
import com.gamestore.model.DomainObject;
import com.gamestore.model.Item;
import com.gamestore.model.ItemContainer;
import com.gamestore.model.Order;
import com.gamestore.model.OrderedItem;
import com.gamestore.util.DatabaseConnection;

public class OrderDAO {
	
private static OrderDAO instance = null;
	
	public static OrderDAO getInstance() {
		if (instance == null) instance =  new OrderDAO();
		
		return instance;
	}
	
	private Map<Integer, Order> order = new HashMap<Integer, Order>(); 
	
	public void addOrderToMap(Order arg) {
		instance.order.put(arg.getID(), arg);
	} 
	
	public List<Order> getAll() {
		
		List<Order> list = new ArrayList<Order>();
		Order o = null;
		
		String query = "select * from Orders inner join OrderedItem on Orders.OrderID = OrderedItem.orderID";
		ResultSet rs = DatabaseConnection.getInstance().ExecuteQuery(query);
		try {
			int prevID = -1;
			int prevUserID = -1;
			String prevStatus = "";
			List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
			while(rs.next()) {
				int id = rs.getInt("OrderID");
				int userId = rs.getInt("userID");
				String status = rs.getString("status");
				
				if (id != prevID)
				{
					if (prevID != -1 && !orderedItems.isEmpty())
					{
						o = new Order(prevID, new ArrayList<OrderedItem>(orderedItems), prevStatus, LoginGateway.getInstance().getUser(prevUserID));
						list.add(o);
						orderedItems.clear();
					}
					prevID = id;
					prevUserID = userId;
					prevStatus = status;
				}
				
				o = order.get(id);
				if (o == null)
				{
					int itemId = rs.getInt("itemID");
					int quantity = rs.getInt("quantity");
					double price = rs.getDouble("price");
					String name = rs.getString("name");
					orderedItems.add(new OrderedItem(itemId, quantity, price, name));
				}
				else
				{
					prevID = id;
					prevUserID = userId;
					prevStatus = status;
					if (!list.contains(o)){
						list.add(o);
					}
					continue;
				}
				
			}
			if (prevID != -1 && !orderedItems.isEmpty())
			{
				o = new Order(prevID, new ArrayList<OrderedItem>(orderedItems), prevStatus, LoginGateway.getInstance().getUser(prevUserID));
				list.add(o);
			}
			
			List<DomainObject> addedObjects = UnitofWork.getCurrent().getAllNew();
			for(DomainObject item : addedObjects) {
				list.add((Order)item);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Order findById(int id) {
		
		Order o = null;
		List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
		int userId = 0;
		int itemId = 0;
		int quantity = 0;
		double price = 0;
		String name = "";
		String status = "";
		
		String query = "select * from Orders INNER JOIN OrderedItem on Orders.OrderID = OrderedItem.orderID where Orders.OrderID = '"+ id + "'";
		ResultSet rs = DatabaseConnection.getInstance().ExecuteQuery(query);
		try {
			while(rs.next()) {
				userId = rs.getInt("userID");
				status = rs.getString("status");
				itemId = rs.getInt("itemID");
				quantity = rs.getInt("quantity");
				price = rs.getDouble("price");
				name = rs.getString("name");
				
				orderedItems.add(new OrderedItem(itemId, quantity, price, name));
			}
			o = new Order(id, new ArrayList<OrderedItem>(orderedItems), status, LoginGateway.getInstance().getUser(userId));
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	public List<Order> findByUserId(int uid) {
			
			Order o = null;
			List<Order> list = new ArrayList<Order>();
			String query = "select * from Orders INNER JOIN OrderedItem on Orders.OrderID = OrderedItem.orderID where Orders.userID = '" + uid + "'";
			ResultSet rs = DatabaseConnection.getInstance().ExecuteQuery(query);
			try {
				int prevID = -1;
				String prevStatus = "";
				List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
				
				while(rs.next()) {
					int id = rs.getInt("OrderID");
					String status = rs.getString("status");
					
					if (id != prevID)
					{
						if (prevID != -1 && !orderedItems.isEmpty())
						{
							o = new Order(prevID, new ArrayList<OrderedItem>(orderedItems), prevStatus, LoginGateway.getInstance().getUser(uid));
							list.add(o);
							orderedItems.clear();
						}
						prevID = id;
						prevStatus = status;
					}
					
	
					o = order.get(id);
					if (o == null)
					{
						int itemId = rs.getInt("itemID");
						int quantity = rs.getInt("quantity");
						double price = rs.getDouble("price");
						String name = rs.getString("name");
						orderedItems.add(new OrderedItem(itemId, quantity, price, name));
					}
					else
					{
						prevID = id;
						prevStatus = status;
						if (!list.contains(o)){
							list.add(o);
						}
						continue;
					}
				}
				if (prevID != -1 && !orderedItems.isEmpty())
				{
					o = new Order(prevID, new ArrayList<OrderedItem>(orderedItems), prevStatus, LoginGateway.getInstance().getUser(uid));
					list.add(o);
				}
				// Add All New Orders
				List<DomainObject> addedObjects = UnitofWork.getCurrent().getAllNew();
				for(DomainObject item : addedObjects) {
					list.add((Order)item);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		
	public Order get(int key) {
		Order o =  (Order) instance.order.get(key);
		if (o == null) {
			try{
				o = findById(key);
				this.addOrderToMap(o);
				}
			catch (Exception ex) {	
			}
		}
		return o;
	}
	
	public boolean checkQuantities(int newQuantity, DomainObject obj, int itemID)
	{
		OrderedItem orderedItem = (OrderedItem)obj;
		Item item = ItemContainer.getInstance().Get(orderedItem.getItemID());
		int oldQuantity = orderedItem.getQuantity();
		
		return ((newQuantity - oldQuantity) > item.getQuantity());
	}
	
	public void insert (DomainObject obj) {
		Order o = (Order) obj;
		String query = "INSERT INTO Orders (userID) VALUES (" + "'" + o.getUser().getId() + "'" + ")";
		int  id = DatabaseConnection.getInstance().ExecuteInsert(query);
		// Set id from DB
		o.setID(id);
		instance.order.put(o.getID(), o);
		
		for (OrderedItem oi : o.getOrderedItems())
		{
			String query2 = "INSERT INTO OrderedItem (orderID, itemID, quantity, price, name) VALUES (" + "'" + o.getID() + "','" + oi.getItemID() + "','" + oi.getQuantity() + "','" + oi.getPrice() + "','" + oi.getName() + "'" + ")";
			DatabaseConnection.getInstance().Execute(query2);
			
			Item item = ItemContainer.getInstance().Get(oi.getItemID());
			item.setQuantity(item.getQuantity() - oi.getQuantity());
			ItemDAO.getInstance().update(item);
			
		}
		
		
		
	}
	
	public void update(DomainObject obj) {

		Order o = (Order) obj;
		for (OrderedItem oi : o.getOrderedItems())
		{
			String query = "UPDATE OrderedItem SET orderID='" + o.getID() + "',itemID='" + oi.getItemID() + "',quantity='" + oi.getQuantity() + "', price='" + oi.getPrice() + "',name='" + oi.getName() + "' WHERE orderID='" + o.getID() + "' AND itemID='" + oi.getItemID() + "'";
			DatabaseConnection.getInstance().Execute(query);
			
	
		}
	}
	
	public void delete(int key) {
		String query = "DELETE FROM Orders WHERE id=" + key;
		DatabaseConnection.getInstance().Execute(query);
		instance.order.remove(key);
	}
	
	public void updateOrderItem(DomainObject obj, int itemIndex, int diff)
	{
		Order o = (Order) obj;
		OrderedItem oi = o.getOrderedItems().get(itemIndex);
		
		String query = "UPDATE OrderedItem SET orderID='" + o.getID() + "',itemID='" + oi.getItemID() + "',quantity='" + oi.getQuantity() + "', price='" + oi.getPrice() + "',name='" + oi.getName() + "' WHERE orderID='" + o.getID() + "' AND itemID='" + oi.getItemID() + "'";
		DatabaseConnection.getInstance().Execute(query);
		
		Item item = ItemContainer.getInstance().Get(oi.getItemID());
		item.setQuantity(item.getQuantity() - diff);
		ItemDAO.getInstance().update(item);
		
	}
	public void deleteOrderItem(int oid, DomainObject obj)
	{
		OrderedItem oi = (OrderedItem) obj;
		String query = "DELETE FROM OrderedItem WHERE orderID='" + oid + "' AND itemID='" + oi.getItemID() + "'";
		DatabaseConnection.getInstance().Execute(query);
		//soleInstance.order.remove(oid);
		
		Order o = instance.get(oid);
		
		Item item = ItemContainer.getInstance().Get(oi.getItemID());
		item.setQuantity(item.getQuantity() - oi.getQuantity());
		ItemDAO.getInstance().update(item);		
	}
}
