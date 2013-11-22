package com.aafonso.constituencyResults;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aafonso.constituencyResults.utils.GenericUtils;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130901
 *
 * Class: ElectionsResultsController
 * 
 * Main Class of the project where we process and control the principal structures and data
 * 
 */

@SuppressWarnings("rawtypes")
public class ElectionsResultsController {
	
	// Log 
	protected Logger log = LoggerFactory.getLogger(ElectionsResultsController.class);
	// Files (results) Factory
	protected static volatile InterfaceResultsFactory resultsProducer;
	// Manage results from file and process
	protected static AbstractResultsConsumer resultsConsumer;
	// List of the Complete results by Party, we maintain producer's results
	protected static Map<String,Party> partiesResult = new HashMap<String,Party>();
	// ScoreBoard 
	protected ScoreBoard scoreBoard = new ScoreBoard();
	
	// Constructor
	public ElectionsResultsController(InterfaceResultsFactory resProd, AbstractResultsConsumer resCon) {
		// LogInfo
		log.info("Init app - Loading Factory!!!");
		// Store the results Factory
		resultsProducer = resProd;
		// Store the consumer
		resultsConsumer = resCon;
		// Auto Load Data... good practice 
		LoadDataToAnalyze();
	}

	// Load Data
	public void LoadDataToAnalyze() {
		// LogInfo
		log.info("Loading Data Factory!!!");
		// Load Data Factory
		resultsProducer.loadFactorytResults();
	}
	
	// Main process
	public void run() {
		int ntimes = 0;
		// Loop process (with a small delay to see results)
		while (true) {
			if (!processFile()) break;
			// A bit stop 
			GenericUtils.Sleep(20);
			// We can also reload the list of files any time we want ... 1/10 for testing mode
			ntimes++;
			if ((ntimes%10)==0) LoadDataToAnalyze();			
		}
	}
	
	// Task (take one file, convert from file.xml to JaxB, parse it, add data to existing data 
	// and finaly, calculate scoreboard totals and percentages
	public boolean processFile(){
		String fileToProc = resultsProducer.getResult();
		if (fileToProc != null) {
			// Ok, we got the file, lets process 
			resultsConsumer = new ResultsConsumerMap();
			int res = resultsConsumer.loadfileResult(fileToProc,partiesResult);
			// Now mark file with the magageRes result (1 = OK, -1,-2 = BAD Data or Format) 
			resultsProducer.setStatusResult(fileToProc,res);
			// LogInfo
			log.info("Processed File "+fileToProc);
			// Automatically update results
			scoreBoard.calculateResults(partiesResult);
		}
		else return false;
		return true;
	}

	// Show results (all the parties list) 
	public void showListParties(){
		int chair = 0;
		int votes = 0;
		List list = this.getListParties();

		System.out.println("-------------------");
		System.out.println("Listing all Parties");
		System.out.println("-------------------");
		for (Iterator it = list.iterator(); it.hasNext();) 
		{         
			Party p = (Party)it.next();             
			System.out.println("Party : "+p.getName() +" Chairs =  "+p.getChairs()+" Votes = "+p.getVotes());            
			chair += p.getChairs();
			votes += p.getVotes();
		} 

		System.out.println("Total Chairs = "+chair);
		System.out.println("Total Votes = "+votes);
	}

	// Show results (Scoreboard with percentages and winner if exist) 
	public void showResults() {
		// Get Results and Print in console :)
		List list = this.getResults();

		System.out.println("---------------");
		System.out.println("Listing Results");
		System.out.println("---------------");
		Boolean first = true;
		for (Iterator it = list.iterator(); it.hasNext();) 
		{       
			PartyResults p = (PartyResults)it.next();   

			if (first){
				if (p.getChairs()>=326) p.setName("Winner!! - "+p.getName());
				first = false;
			}
			
			DecimalFormat f = new DecimalFormat("##0.00");
			System.out.println("Party : "+p.getName() +" Chairs =  "+p.getChairs()+" Votes = "+p.getVotes() + " Percentage Chairs = "+ f.format(p.getPercChairs())+ "%  Percentage Votes = "+f.format(p.getPercVotes())+"%");            
		} 
		
		System.out.println("Total Chairs "+scoreBoard.getTotalChairs());
		System.out.println("Total Votes  "+scoreBoard.getTotalVotes());
	}
	
	// All Parties results (List)
	public List getListParties(){
		return scoreBoard.updatedResults(partiesResult);
	}
	
	// Results (List) to process from other interfaces 
	public List getResults() {
		return scoreBoard.calculateResults(partiesResult);
	}
	
	// Delete all processed data
	public void cleanResults(){
		partiesResult.clear();
		scoreBoard.clear();
		LoadDataToAnalyze();		
	}
}
