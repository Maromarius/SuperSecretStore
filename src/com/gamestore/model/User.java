package com.gamestore.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User 
{
	private String name, address, phone, email, password, paymentType;
	private boolean isAdmin;
	private int id;
	
	public User()
	{
		
	}
	
	public User(ResultSet rs)
	{
		try 
		{
			this.id = rs.getInt("UserID");
			this.name = rs.getString("name");
			this.address = rs.getString("address");
			this.phone = rs.getString("phone");
			this.paymentType = rs.getString("paymentType");
			this.email = rs.getString("email");
			this.password = rs.getString("password");
			this.isAdmin = rs.getBoolean("isAdmin");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public User(String h)
	{
		System.out.println(h);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public String toString()
	{
		return (id + " " + name + " " + password + " " + phone + " " + email);
	}
}
