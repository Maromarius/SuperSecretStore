package com.gamestore.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User
{
	private String name, address, phone, email, password;
	private int isAdmin;
	private int id;
	
	public User(ResultSet rs)
	{
		try 
		{
			this.id = rs.getInt("UserID");
			this.name = rs.getString("name");
			this.address = rs.getString("address");
			this.phone = rs.getString("phone");
			this.email = rs.getString("email");
			this.password = rs.getString("password");
			this.isAdmin = rs.getInt("isAdmin");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public User(String name, String address, String phone, String email,
			String password, int isAdmin, int id) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.id = id;
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

	public int isAdmin() {
		return isAdmin;
	}

	public void setAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String toString()
	{
		return (id + " " + name + " " + password + " " + phone + " " + email);
	}
}
