package com.gamestore.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ShoppingCart {
	
	HashMap<Integer,Integer> itemQuantity;
	private Set<Item> itemSet;
	
	public ShoppingCart()
	{
		itemQuantity = new HashMap<Integer,Integer>();
		itemSet = new HashSet<Item>();
	}
	
	public void Add(Item item)
	{
		int currentQuantity = 0;
		if(itemQuantity.containsKey(item.getID()))
		{
			currentQuantity = itemQuantity.get(item.getID());
		}
		currentQuantity++;
		itemQuantity.put(item.getID(), currentQuantity);
		itemSet.add(item);
		return;
	}
	
	public void Add(Item item, int quantity)
	{
		int currentQuantity = 0;
		if(itemQuantity.containsKey(item))
		{
			currentQuantity = itemQuantity.get(item.getID());
		}
		itemQuantity.put(item.getID(), quantity+currentQuantity);
		itemSet.add(item);
		return;
	}
	
	public int getCount(int itemID)
	{
		return itemQuantity.get(itemID);
	}
	
	public void Remove(Item item, int quantity)
	{
		int newQuantity = getCount(item.getID()) - quantity;
		if(newQuantity <= 0)
		{
			itemQuantity.remove(item.getID());
			itemSet.remove(item);
		}
		else
		{
			itemQuantity.put(item.getID(), newQuantity);
		}
	}

	public ArrayList<Item> Search(String searchString)
	{
		ArrayList<Item> results = new ArrayList<Item>();
		
		for(Iterator<Item> i = itemSet.iterator(); i.hasNext(); ) {
		    Item item = i.next();
		    if(item.Contains(searchString))
		    	results.add(item);
		}
		return results;
	}
	
	public ArrayList<Item> getList()
	{
		ArrayList<Item> itemList = new ArrayList<Item>();
		for(Iterator<Item> i = itemSet.iterator(); i.hasNext(); ) {
		    Item item = i.next();
		    for(int n = 0 ; n < getCount(item.getID());n++)
		    {
		    	itemList.add(item);
		    }
		}
		return itemList;
	}
	
	public ArrayList<Item> getSetList()
	{
		ArrayList<Item> itemsToReturn = new ArrayList<Item>();
		Object[] itemArray = itemSet.toArray();
		
		for(int i = 0 ; i < itemArray.length; i++)
		{
			itemsToReturn.add((Item)itemArray[i]);
		}
		return itemsToReturn;
	}
	
	public void setQuantity(Item item, int quantity)
	{
		if(itemQuantity.containsKey(item.getID()))
		{
			if(quantity == 0)
			{
				Remove(item, getCount(item.getID()));
			}
			else
			{
				itemQuantity.put(item.getID(), quantity);
				return;
			}
		}
	}
	
	public String getTotalPrice()
	{
		if(itemSet.size() > 0)
		{

			double total = 0;
			for(Iterator<Item> i = itemSet.iterator(); i.hasNext(); ) {
			    Item item = i.next();
			    total += (itemQuantity.get(item.getID())*item.getPrice());
			}
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
			return currencyFormatter.format(total);
		}
		return "";
	}
	
	public ArrayList<OrderedItem> makeOrder()
	{
		ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
		ArrayList<Item> shoppingCartItems = getList();
		for(int i = 0; i < shoppingCartItems.size(); i++)
		{
			Item item = shoppingCartItems.get(i);
			orderedItems.add(new OrderedItem(item.getID(),  this.getCount(item.getID()),  item.getPrice()));
		}
		return orderedItems;
		
	}
	

}
