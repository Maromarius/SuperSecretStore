package com.gamestore.model;

import java.util.HashMap;

public class IdentityMap <E>
{
	private HashMap<Integer, E> map = new HashMap<Integer, E>();
	
	public E get(int id)
	{
		return map.get(id); // return null if not found
	}
	
	public void add(int id, E entry)
	{
		map.put(id, entry); // returns null if cannot add
	}
}
