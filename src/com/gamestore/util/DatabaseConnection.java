package com.gamestore.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class DatabaseConnection 
{
	private static int port = 3334;
	private static Connection connection = null;
	private static DatabaseConnection DBaccess = null;
	
	private DatabaseConnection(){
		
		connection = getConnection();
	};
	
	public static DatabaseConnection getInstance(){
		
		if(DBaccess == null){
			
			DBaccess = new DatabaseConnection();
		}
		return DBaccess;
	}
	
	public static Connection getConnection(){
		
		if(connection == null){
			
			connection = createConnection();
		}
		
		return connection;
	}
	
	static 
	{
		String driverName = "com.mysql.jdbc.Driver";
		try 
		{
			Class.forName(driverName);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private static Connection createConnection() 
	{
		connection = null;
		// SSH CONNECTION SETTINGS TO LOG IN TO CONCORDIA
		Properties prop = new Properties();
        InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("encs.properties");
        try 
        {
			prop.load(inputStream);
		} catch (IOException e2) 
		{
			e2.printStackTrace();
		}
		String sshUser = prop.getProperty("encs_username"); // ENCS LOGIN USERNAME
		String sshPassword = prop.getProperty("encs_password"); // ENCS LOGIN PASSWORD
		String sshHost = "login.encs.concordia.ca"; 
		int nSshPort = 22; // remote SSH host port number
		String strRemoteHost = "clipper.encs.concordia.ca"; // Host-name of database server
		int nLocalPort = port++; //int nLocalPort = findUnusedPort(); // local port number use to bind SSH
		// tunnel
		int nRemotePort = 3306; // remote port number of your database
		final JSch jsch = new JSch();
		int assport = 0;
		Session session=null;
		try 
		{
			session = jsch.getSession(sshUser, sshHost, nSshPort);
		} catch (JSchException e1) 
		{
			e1.printStackTrace();
		}
		session.setPassword(sshPassword);
		final Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		try 
		{
			session.connect();
		} 
		catch (JSchException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			assport = session.setPortForwardingL(nLocalPort, strRemoteHost,nRemotePort);
		} catch (JSchException e1) {
			e1.printStackTrace();
		}

		String databaseName = "soen387k";
		String username = "soen387k";
		String password = "e73v72";
		String localHostIP = "127.0.0.1";
		String url = "jdbc:mysql://"+localHostIP+":"+nLocalPort+"/"+databaseName;

		try 
		{
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return connection;

	}

	public static void close(ResultSet rs, Statement stmt, Connection con) 
	{
		try 
		{
			if (rs != null)
				rs.close();
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		try 
		{
			if (stmt != null)
				stmt.close();
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		try 
		{
			if (con != null)
				con.close();
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public static void printRs(ResultSet rs) 
	{
		try 
		{
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			StringBuffer sb = new StringBuffer();
			while (rs.next()) 
			{
				for (int i = 1; i <= cols; i++) 
				{
					String columnName = meta.getColumnName(i);
					sb.append(columnName + "=");
					sb.append(rs.getString(columnName) + "  ");
				}
				sb.append("\n");
			}
			System.out.print(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet ExecuteQuery (String query) {
		ResultSet result = null;
		try {
			Statement st = connection.createStatement();
			result = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public void Execute (String query) {
		try {
			Statement st = connection.createStatement();
			st.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int ExecuteInsert (String query) {
		ResultSet result = null;
		int id = -1;
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			result = st.getGeneratedKeys();
			result.next();
			id =  result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
}