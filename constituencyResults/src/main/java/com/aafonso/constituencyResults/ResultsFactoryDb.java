package com.aafonso.constituencyResults;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aafonso.constituencyResults.jdbc.JdbcSql;
import com.aafonso.constituencyResults.utils.FileUtils;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Class: ResultsFactoryDb
 * 
 * Factory that load all files to process and give the next element available
 * 
 * We use a database to access data, load available files and control structures
 * 
 * We also are able to load or reload data controlling already processed data
 * 
 * If we need to start again, we can clean the table and load all the "products" again
 * 
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class ResultsFactoryDb implements InterfaceResultsFactory {
	
	// Log 
	private Logger log = LoggerFactory.getLogger(ElectionsResultsController.class);

	// Basic configuration
	public String pathToFiles;

	// Sql access
	private static JdbcSql jsql = new JdbcSql();
	ResultSet res;

	// Constructor
	public ResultsFactoryDb(String pathTo){
		pathToFiles=pathTo;
		// Sql configuration
		jsql.setDriverName("org.sqlite.JDBC");
		jsql.setUrlDriver("jdbc:sqlite:");
		jsql.setDbname("db/filesresults.db");
	}

	public void loadFactorytResults() {
		// List of possibles resources to process... files
		Map<String,Integer> fileResultList = new TreeMap<String,Integer>();
		// Charge the list of elements to process
        FileUtils.getlistFiles(pathToFiles,fileResultList);
        log.info("Folder loaded, list of products ready "+fileResultList.size());
        // Store it into DB
    	try {
    		jsql.startConnection();
    		Iterator it = fileResultList.entrySet().iterator();
    		while (it.hasNext()){
    			// Select, if exist nothing to do, if does not exist insert
				Map.Entry<String,Integer> entry = (Entry<String, Integer>) it.next();
    			res = jsql.executeSQL("select name from files where name='"+entry.getKey()+"'");
				if (!res.next()) 
					jsql.executeSQLSentence("insert into files (name,value) values ('"+entry.getKey()+"',0)");
			}

   		} catch (SQLException e) {
			e.printStackTrace();
			log.error("There is some problem inserting files on DB... Must check it!!");
		}
        jsql.stopConnection();
	}

	public void cleanFactoryResults() {
		jsql.startConnection();
		jsql.executeSQLSentence("delete from files");
        jsql.stopConnection();
	}
	
	// Advice - We define this method as synchronized 
	public synchronized String getResult() {
		// We return the next filename available to process
		String fileName = null;
        // Store it into DB
    	try {
    		jsql.startConnection();
    		res = jsql.executeSQL("select name from files where value=0 limit 1");
			if (res.next()) fileName = res.getString("name");
			jsql.executeSQLSentence("update files set value = 1 where name = '"+fileName+"'");
   		} catch (SQLException e) {
			e.printStackTrace();
			log.error("There is some problem selecting files on DB... Must check it!!");
		}
        jsql.stopConnection();
		return fileName;
	}
	
	public void setStatusResult(String fkey, int val){
		jsql.startConnection();
		jsql.executeSQLSentence("update files set value = "+val+" where name = '"+fkey+"'");
        jsql.stopConnection();	}
	
	public int getStatusResult(String fkey){
		// We return the next filename available to process
		int num = -9999;
        // Store it into DB
    	try {
    		jsql.startConnection();
    		res = jsql.executeSQL("select value from files where name = '"+fkey+"'");
			if (res.next()) num = res.getInt(1);
   		} catch (SQLException e) {
			e.printStackTrace();
			log.error("There is some problem selecting value of file on DB... Must check it!!");
		}
        jsql.stopConnection();
		return num;
	}
	
	public int getNumResults(){
		// We return the next filename available to process
		int num = 0;
        // Store it into DB
    	try {
    		jsql.startConnection();
    		res = jsql.executeSQL("select count(*) from files");
			if (res.next()) num = res.getInt(1);
   		} catch (SQLException e) {
			e.printStackTrace();
			log.error("There is some problem selecting count of file on DB... Must check it!!");
		}
        jsql.stopConnection();
		return num;
	}
}
