package com.gamestore.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;

import com.gamestore.model.DomainObject;
import com.gamestore.model.IdentityMap;

public class UnitofWork {
	
	private List<DomainObject> newObjects = new ArrayList();
	private List<DomainObject> dirtyObjects = new ArrayList();
	private List<DomainObject> removedObjects = new ArrayList();
	private static UnitofWork instance = null;
	private IdentityMap<DomainObject> idMap;

	public static UnitofWork getCurrent() {
		if (instance == null)
		{
			instance = new UnitofWork();
		}
		return instance;
	}
	
	public void registerNew(DomainObject obj) {
		Assert.assertNotNull("id not null", obj.getID());
		Assert.assertTrue("object not dirty", !dirtyObjects.contains(obj));
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		Assert.assertTrue("object not already registered new", !newObjects.contains(obj));
		newObjects.add(obj);
	}
	
	public void registerDirty(DomainObject obj) {
		Assert.assertNotNull("id not null", obj.getID());
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		if (!dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
			dirtyObjects.add(obj);
		}
	}
	
	public void registerRemoved(DomainObject obj) {
		Assert.assertNotNull("id not null", obj.getID());
		if (newObjects.remove(obj)) return;
		dirtyObjects.remove(obj);
		if (!removedObjects.contains(obj)) {
			removedObjects.add(obj);
		}
	}
	
	
	
	public void registerClean(DomainObject obj) {
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
			DomainObject obj = (DomainObject) objects.next();
			ItemDAO.getInstance().add(obj);
		}
	}
	
	private void updateDirty() {
		for (Iterator objects = dirtyObjects.iterator(); objects.hasNext();) {
			DomainObject obj = (DomainObject) objects.next();
			ItemDAO.getInstance().update(obj);
		}
	}
	
	private void deleteRemoved() {
		for (Iterator objects = removedObjects.iterator(); objects.hasNext();) {
			DomainObject obj = (DomainObject) objects.next();
			ItemDAO.getInstance().delete(obj.getID());
		}
	}
	public List<DomainObject> getAllNew() {
		return this.newObjects;
	}

}
