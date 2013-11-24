package com.gamestore.model;

import java.util.ArrayList;
import java.util.Iterator;

public class ItemContainer {

	private static ItemContainer itemContainer;
	private ArrayList<Item> items;
	
	private ItemContainer()
	{
		items = new ArrayList<Item>();
	}
	
	public static ItemContainer getInstance()
	{
		if(itemContainer == null)
			itemContainer = new ItemContainer();
		return itemContainer;
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
}
