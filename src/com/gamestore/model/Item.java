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
	private String platform;
	private String type;
	
	
	public Item(int ID, String name, double price, int quantity, String description, String imgUrl, String platform, String type) {
		super(ID, Status.CLEAN);
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.imgUrl = imgUrl;
		this.platform = platform;
		this.type = type;
	}
	
	public Item(ResultSet rs) throws SQLException
	{
		super(rs.getInt("ItemID"), Status.CLEAN);
		try 
		{
			
			this.name = rs.getString("name");
			this.price = rs.getDouble("price");
			this.quantity = rs.getInt("quantity");
			this.description = rs.getString("description");
			this.imgUrl = rs.getString("imgURL");
			this.platform = rs.getString("platform");
			this.type = rs.getString("type");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public Item() {
		// TODO Auto-generated constructor stub
	}

	public String getPlatformName()
	{
		return platform.toString();
	}
	public boolean Contains(String searchString)
	{
		return this.name.toLowerCase().contains(searchString.toLowerCase())|| this.description.toLowerCase().contains(searchString.toLowerCase()); 
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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStockStatus()
	{
		return "In Stock";
	}
	
	public String toString()
	{
		return (getID() + " " + name + " " + price + " " + quantity + " " + description);
	}
}
