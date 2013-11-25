package com.gamestore.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ShoppingCart {
	
	private ArrayList<Item> items;
	private Set<Item> itemSet;
	
	public ShoppingCart()
	{
		items = new ArrayList<Item>();
		itemSet = new HashSet<Item>();
	}
	
	public void Add(Item item)
	{
		items.add(item);
		itemSet.add(item);
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
	
	public int getCount(int itemID)
	{
		int count = 0;
		for(Iterator<Item> i = items.iterator(); i.hasNext(); ) {
		    Item item = i.next();
		    if(item.getId() == itemID)
		    {
		    	count++;
		    }	
		}
		return count;
	}
	
	public void Remove(int itemID)
	{
		
		for(Iterator<Item> i = items.iterator(); i.hasNext(); ) {
		    Item item = i.next();
		    if(item.getId() == itemID)
		    {
		    	items.remove(item);
		    }	
		}
		for(Iterator<Item> i = items.iterator(); i.hasNext(); ) {
		    Item item = i.next();
		   if(item.getId() == itemID)
		   {
			   itemSet.remove(item);
			   return;
		   }
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
		return items;
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

}
