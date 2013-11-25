package com.gamestore.model;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCart {
	
	private ArrayList<Item> items;
	
	public ShoppingCart()
	{
		items = new ArrayList<Item>();
	}
	
	public void Add(Item item)
	{
		items.add(item);
		return;
	}
	
	public Item Get(int itemID)
	{
		Item itemToReturn = null;
		for(Iterator<Item> i = items.iterator(); i.hasNext(); ) {
		    Item item = i.next();
		    if(item.getId() == itemID)
		    {
		    	itemToReturn = item;
		    	break;
		    }	
		}
		return itemToReturn;
	}
	
	public void Remove(int itemID)
	{
		for(Iterator<Item> i = items.iterator(); i.hasNext(); ) {
		    Item item = i.next();
		    if(item.getId() == itemID)
		    {
		    	items.remove(item);
		    	return;
		    }	
		}
	}

	public ArrayList<Item> Search(String searchString)
	{
		ArrayList<Item> results = new ArrayList<Item>();
		
		for(Iterator<Item> i = items.iterator(); i.hasNext(); ) {
		    Item item = i.next();
		    if(item.Contains(searchString))
		    	results.add(item);
		}
		return results;
	}
	public ArrayList<Item> getList()
	{
		return items;
	}

}
