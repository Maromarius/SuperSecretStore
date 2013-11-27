package com.gamestore.model;

public class OrderedItem extends DomainObject{
	
	private int ItemID;
	private int quantity;
	private double price;
	private String name;
	
	
	
	public OrderedItem(int itemID, int quantity, double price, String name) {
		super();
		this.ItemID = itemID;
		this.quantity = quantity;
		this.price = price;
		this.name = name;
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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	

}
