package com.gamestore.model;

public class OrderedItem extends DomainObject{
	
	private int ItemID;
	private int quantity;
	private double price;
	
	
	public OrderedItem(int itemID, int quantity, double price) {
		super();
		this.ItemID = itemID;
		this.quantity = quantity;
		this.price = price;
	}



	public int getItemID() {
		return ItemID;
	}



	public void setItemID(int itemID) {
		ItemID = itemID;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}

	
	
	
	
	
	

}
