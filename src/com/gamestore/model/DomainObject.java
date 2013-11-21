package com.gamestore.model;

import com.gamestore.dao.UnitofWork;

public class DomainObject {
	
	
	private int ID;
	private Status Status; 

	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public DomainObject(int ID) {
		this.ID = ID;
	}
	
	public DomainObject() {
	}
	
	public DomainObject(int id, Status status) {
		this.ID = id;
		this.Status = status;
	}
	public void markNew() {
		this.setStatus(Status.NEW);
		UnitofWork.getCurrent().registerNew(this);
	}
	public void markClean() {
		this.setStatus(Status.CLEAN);
		UnitofWork.getCurrent().registerClean(this);
	}
	public void markDirty() {
		this.setStatus(Status.DIRTY);
		UnitofWork.getCurrent().registerDirty(this);
	}
	public void markRemoved() {
		this.setStatus(Status.DELETED);
		UnitofWork d = UnitofWork.getCurrent();
		UnitofWork.getCurrent().registerRemoved(this);
	}
	public Status getStatus() {
		return Status;
	}
	public void setStatus(Status status) {
		Status = status;
	}
	
}
