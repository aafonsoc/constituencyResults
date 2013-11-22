package com.aafonso.constituencyResults;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.aafonso.constituencyResults.jdbc.JdbcSql;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Class: ResultsConsumerDb
 * 
 * Process one file and store/update results in Db 
 * 
 */

public class ResultsConsumerDb extends AbstractResultsConsumer {

	// Sql access
	private static JdbcSql jsql = new JdbcSql();
	ResultSet res;
	
	// Constructor
	public ResultsConsumerDb() {
		super();
		// Sql configuration
		jsql.setDriverName("org.sqlite.JDBC");
		jsql.setUrlDriver("jdbc:sqlite:");
		jsql.setDbname("db/electionresults.db");
	}
	
	// Empty results stored in Db
	@Override
	public void cleanResults() {
		jsql.startConnection();
		jsql.executeSQLSentence("delete from parties");
        jsql.stopConnection();
	}
	
	@Override
	protected void updatePartiesList(Map<String, Party> mParties) {
		// Open connection
		jsql.startConnection();
		// Everything is fine, lets search for the winner and update all the votes
		Party ptWinner = new Party();
		for (Party pt:localPartiesResult){
			if (pt.getVotes()>ptWinner.getVotes()){
				ptWinner.setName(pt.getName());
				ptWinner.setVotes(pt.getVotes());
			}
			// Update votes
			updateResultDb(jsql,pt.getName(),pt.getVotes(),0);
		}	
		// Update chairs... we are sure we update/insert previously, so we send 0 votes to avoid duplicate votes
		updateResultDb(jsql,ptWinner.getName(),0,1);
		// Finally we update result list (map) that is the "tool" used by our controller
		updateResultsMap(jsql,mParties);
		// Close connection... all done!!
        jsql.stopConnection();
	}
	
	public void updateResultDb(JdbcSql jsql,String nparty, int votes, int chairs) {
        // Store it into DB
    	try {
    		res = jsql.executeSQL("select name from parties name='"+nparty+"'");
			if (res.next()) 
				jsql.executeSQLSentence("update parties set votes = votes+"+votes+", chairs=chairs+"+chairs+" where name = '"+nparty+"'");
			else
				jsql.executeSQLSentence("insert into parties (name,votes,chairs) values ('"+nparty+"',"+votes+","+chairs+")");
   		} catch (SQLException e) {
			e.printStackTrace();
			log.error("There is some problem updating parties on DB... Must check it!!");
		}
	}
	
	public void updateResultsMap(JdbcSql jsql, Map<String,Party> mParties){
		// Clean results
		mParties.clear();
    	try {
    		res = jsql.executeSQL("select * from parties");
			while (res.next()){
				Party pt = new Party();
				pt.setName(res.getString("name"));
				pt.setVotes(res.getInt("votes"));
				pt.setChairs(res.getInt("chairs"));
				mParties.put(res.getString("name"),pt);
			}
   		} catch (SQLException e) {
			e.printStackTrace();
			log.error("There is some problem updating parties on DB... Must check it!!");
		}
		
	}

}
