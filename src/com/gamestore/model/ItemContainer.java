package com.gamestore.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ItemContainer {

	private static ItemContainer itemContainer;
	private ArrayList<Integer> itemIds;
	HashMap<Integer,Item> itemMap;
	
	private ItemContainer()
	{
		itemIds = new ArrayList<Integer>();
		itemMap = new HashMap<Integer,Item>();
	}
	
	public static ItemContainer getInstance()
	{
		if(itemContainer == null)
			itemContainer = new ItemContainer();
		return itemContainer;
	}
	
	public void Add(Item item)
	{
		itemIds.add(item.getID());
		itemMap.put(item.getID(), item);
		return;
	}
	
	public Item Get(int itemID)
	{
		return itemMap.get(itemID);
	}
	
	public void Remove(int itemID)
	{
		itemIds.remove(itemMap.remove(itemID).getID());
	}

	public ArrayList<Item> Search(String searchString)
	{
		ArrayList<Item> results = new ArrayList<Item>();
		
		for(Iterator<Integer> i = itemIds.iterator(); i.hasNext(); ) {
		    int itemId = i.next();
		    if(itemMap.get(itemId).Contains(searchString))
		    	results.add(itemMap.get(itemId));
		}
		return results;
	}
	public void Reset()
	{
		itemIds.clear();
		itemMap.clear();
	}
	
	public ArrayList<Item> GetAllItems()
	{
		ArrayList<Item> results = new ArrayList<Item>();
		for(Iterator<Integer> i = itemIds.iterator(); i.hasNext(); ) {
		    int itemId = i.next();
		    results.add(itemMap.get(itemId));
		}
		return results;
	}
}
