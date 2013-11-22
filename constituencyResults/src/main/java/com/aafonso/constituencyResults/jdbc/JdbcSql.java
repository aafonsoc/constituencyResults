package com.aafonso.constituencyResults.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSql implements InterfaceSql {

	// Defaults by testing F&R
	private String driverName = "";
	private String urlDriver = "";
	private String serverName = ""; 
	private String portNumber = "";
	private String dbname = "";
	private String username = "";
	private String password = "";
	
	private Connection connection;
	
	public JdbcSql()
	{
		
	}
	
	public JdbcSql(String dn, String ud, String sn, String pn, String db, String un, String pa) {
		setDriverName(dn);
		setUrlDriver(ud);
		setServerName(sn);
		setPortNumber(pn);
		setDbname(db);
		setUsername(un);
		setPassword(pa);
	}
	
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrlDriver() {
		return urlDriver;
	}

	public void setUrlDriver(String urlDriver) {
		this.urlDriver = urlDriver;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private boolean checkDriver() {
		try {
			// Load the JDBC driver
			Class.forName(driverName); } 
		catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			return false;
			}
		return true;
	}

	public boolean startConnection() {
		String url;

		if (!checkDriver()) return false;
		if ( "org.sqlite.JDBC".equals(driverName))
			url = "jdbc:sqlite:"+dbname;
		else 
			url = urlDriver + serverName + ":" + portNumber + ":" + dbname;
		try {
			// Create a connection to the database
			//connection = DriverManager.getConnection(url, username, password);
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean stopConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	public Connection getConnection() {
		return connection;
	}

	public ResultSet executeSQL(String SqlTxt) {
		
		Statement stmt;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		} 
		ResultSet rs;
		try {
			rs = stmt.executeQuery(SqlTxt);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		} 
		return rs;
	}

	public int executeSQLSentence(String SqlTxt) {
		Statement stmt;
		int nreg = 0;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return nreg;
		} 
		ResultSet rs;
		try {
			nreg = stmt.executeUpdate(SqlTxt);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return nreg;
		} 
		return nreg;
	}	
}
