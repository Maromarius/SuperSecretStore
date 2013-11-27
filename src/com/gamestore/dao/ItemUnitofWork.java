package com.gamestore.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.gamestore.model.Item;

import org.junit.Assert;

import com.gamestore.model.DomainObject;
import com.gamestore.model.IdentityMap;

public class ItemUnitofWork {

	
	private List<Item> newObjects = new ArrayList();
	private List<Item> dirtyObjects = new ArrayList();
	private List<Item> removedObjects = new ArrayList();
	
	private static ItemUnitofWork instance = null;
	
	
	private IdentityMap<DomainObject> idMap;

	public static ItemUnitofWork getInstance() {
		if (instance == null)
		{
			instance = new ItemUnitofWork();
		}
		return instance;
	}
	
	public void registerNew(Item obj) {
		Assert.assertNotNull("id not null", obj.getID());
		Assert.assertTrue("object not dirty", !dirtyObjects.contains(obj));
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		Assert.assertTrue("object not already registered new", !newObjects.contains(obj));
		newObjects.add(obj);
	}
	
	public void registerDirty(Item obj) {
		Assert.assertNotNull("id not null", obj.getID());
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		if(newObjects.contains(obj))
			newObjects.remove(obj);
		if (!dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
			dirtyObjects.add(obj);
		}
	}
	
	public void registerRemoved(Item obj) {
		Assert.assertNotNull("id not null", obj.getID());
		if (newObjects.remove(obj)) return;
		dirtyObjects.remove(obj);
		if (!removedObjects.contains(obj)) {
			removedObjects.add(obj);
		}
	}
	
	public void registerClean(Item obj) {
		Assert.assertNotNull("id not null", obj.getID());
	}
	
	public void commit() 
	{
		insertNew();
		updateDirty();
		deleteRemoved();
		newObjects.clear();
		dirtyObjects.clear();
		removedObjects.clear();
	}
	
	private void insertNew() {
		for (Iterator objects = newObjects.iterator(); objects.hasNext();) {
			Item obj = (Item) objects.next();
			ItemDAO.getInstance().addbyObject(obj);
		}
	}
	
	private void updateDirty() {
		for (Iterator objects = dirtyObjects.iterator(); objects.hasNext();) {
			Item obj = (Item) objects.next();
			if(obj.getID()>=10000)
			{
				//new object not stored in db.
				ItemDAO.getInstance().addbyObject(obj);
			}
			else
			{
				ItemDAO.getInstance().update(obj);
			}
		}
	}
	
	private void deleteRemoved() {
		for (Iterator objects = removedObjects.iterator(); objects.hasNext();) {
			Item obj = (Item) objects.next();
			ItemDAO.getInstance().delete(obj.getID());
		}
	}
	public List<Item> getAllNew() {
		return this.newObjects;
	}

}
