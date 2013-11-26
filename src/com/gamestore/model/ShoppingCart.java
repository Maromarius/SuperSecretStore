package com.gamestore.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
		if(itemQuantity.containsKey(item.getId()))
		{
			currentQuantity = itemQuantity.get(item.getId());
		}
		currentQuantity++;
		itemQuantity.put(item.getId(), currentQuantity);
		itemSet.add(item);
		return;
	}
	
	public void Add(Item item, int quantity)
	{
		int currentQuantity = 0;
		if(itemQuantity.containsKey(item))
		{
			currentQuantity = itemQuantity.get(item.getId());
		}
		itemQuantity.put(item.getId(), quantity+currentQuantity);
		itemSet.add(item);
		return;
	}
	
	public int getCount(int itemID)
	{
		return itemQuantity.get(itemID);
	}
	
	public void Remove(Item item, int quantity)
	{
		int newQuantity = getCount(item.getId()) - quantity;
		if(newQuantity <= 0)
		{
			itemQuantity.remove(item.getId());
			itemSet.remove(item);
		}
		else
		{
			itemQuantity.put(item.getId(), newQuantity);
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
		    for(int n = 0 ; n < getCount(item.getId());n++)
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
		if(itemQuantity.containsKey(item.getId()))
		{
			if(quantity == 0)
			{
				Remove(item, getCount(item.getId()));
			}
			else
			{
				itemQuantity.put(item.getId(), quantity);
				return;
			}
		}
	}

}
