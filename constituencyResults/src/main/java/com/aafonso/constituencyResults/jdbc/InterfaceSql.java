package com.aafonso.constituencyResults.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;

/*
 * This could be (or should be) an abstract class due to the execute methods based on connection
 * are the same (and they could have been coded on abstract class), but the last changes on 
 * Hibernate 4 deprecated the connection class (compatibility class between jdbc and hibernate).
 * 
 * For this reason, it is better to maintain it as interface and build a doWork methods for hibernate
 * 
 * */

public interface InterfaceSql {

	public boolean startConnection();
	public boolean stopConnection();
	
	public Connection getConnection();
	public ResultSet executeSQL(String SqlTxt);
	public int executeSQLSentence(String SqlTxt);

}
