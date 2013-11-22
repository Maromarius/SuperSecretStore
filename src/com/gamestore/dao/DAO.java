package com.gamestore.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.gamestore.util.DatabaseConnection;

public class DAO <E>
{
	private String tableName, idName;
	private Connection connection;
	private Class<E> clazz;
	
	public DAO(String tableName, String idName, Class<E> clazz)
	{
		connection = DatabaseConnection.getConnection();
		this.tableName = tableName;
		this.setIdName(idName);
		this.clazz = clazz;
	}
	
	public boolean delete(int recordId) 
	{
		 String sql = "DELETE FROM `soen387k`.`"+tableName+"` WHERE `"+idName+"`='"+recordId+"';";
		 return executeSQLStatement(sql);
	}
	
	public E find(int recordId) 
	{
		ResultSet resultSet = null;
		String sql = "SELECT * FROM "+tableName+" WHERE "+idName+"="+recordId+";";
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
		        Constructor constructor = clazz.getDeclaredConstructor(ResultSet.class);
		        E record = (E) constructor.newInstance(resultSet);
		        return record;	
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (NoSuchMethodException e){
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<Integer, E> findAll() 
	{
		HashMap<Integer, E> entries = new HashMap<Integer,E>();
		String sql = "SELECT * FROM "+tableName+" WHERE "+idName+"=?";
		ResultSet resultSet = null;
		try 
		{
			ArrayList<Integer> idList = getAllIds();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for (Integer id : idList)
			{
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
				{
			        Constructor constructor = clazz.getDeclaredConstructor(ResultSet.class);
			        E record = (E) constructor.newInstance(resultSet);
			        entries.put(id, record);
				}
			}
		} 
		catch (SQLException e){
			e.printStackTrace();
		} catch (InstantiationException e){
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return entries;
	}
	
	public ArrayList<Integer> getAllIds()
	{
		ArrayList<Integer> idList = new ArrayList<Integer>();
		try 
		{
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT "+idName+" FROM "+tableName+";");
           while (resultSet.next()) 
        	   idList.add(resultSet.getInt(idName));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return idList;
	}
	
	protected boolean executeSQLStatement(String sql)
	{
		Statement statement = null;
		try 
		{
			statement = connection.createStatement();
			statement.execute(sql);
			return true; // successful operation
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false; // unsuccessful operation
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}
	
	public Connection getConnection(){
		return connection;
	}
}
