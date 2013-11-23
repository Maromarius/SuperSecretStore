package com.gamestore.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Item extends DomainObject
{	
	private String name;
	private double price;
	private int quantity;
	private String description;
	private String imgUrl;
	private Platform platform;
	private int type;
	private int id;
	
	public Item(int ID, String name, double price, int quantity, String description, String imgUrl, Platform platform, int type) {
		super(ID, Status.CLEAN);
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.imgUrl = imgUrl;
		this.platform = platform;
		this.type = type;
	}
	
	public Item(ResultSet rs)
	{
		try 
		{
			this.id = rs.getInt("ItemID");
			this.name = rs.getString("name");
			this.price = rs.getDouble("price");
			this.quantity = rs.getInt("quantity");
			this.description = rs.getString("description");
			this.imgUrl = rs.getString("imgURL");
			//this.platform = rs.getPlatform("platform");
			this.type = rs.getInt("type");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString()
	{
		return (id + " " + name + " " + price + " " + quantity + " " + description);
	}
}
