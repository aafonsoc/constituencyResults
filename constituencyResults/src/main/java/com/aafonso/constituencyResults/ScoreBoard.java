package com.aafonso.constituencyResults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Class: ScoreBoard
 * 
 * Class to manage the electoral results based on parties chairs
 * 
 * Auto calculate:
 *   - winner 
 *   - three first parties 
 *   - "others" aggrupated 
 *   - total votes, chairs, percentages
 *   
 * We have two sets of methods to calculate results, that give us two different list of data:
 *   - list of all parties + votes + chairs
 *   - list of the fourth ones (three first + others) in extended PartyResults object with percentages
 * 
 * For sorting purposes we use SortHashMap with new compare method to store the Party results
 * in a new ArrayList sorted by chairs
 * 
 * We also have available the get methods for TotalVotes, TotalChairs and ResultList
 * 
 */

@SuppressWarnings({"unchecked","rawtypes"})
public class ScoreBoard {
	
	private int totalVotes = 0;
	private int totalChairs = 0;
	private List<PartyResults> results = new ArrayList<PartyResults>();

	// 
	public class SortHashMap implements Comparator<Party>
	{
		public int compare(Party p1, Party p2) 
		{
			return (p2.getChairs() - p1.getChairs());
		}
	}
	
	// Return the sorted list of all parties
	public List<Party> updatedResults(Map<String,Party> partiesResult){
	
		Collection<Party> sp = partiesResult.values();
		List list = new ArrayList(sp);
		Collections.sort(list,new SortHashMap());
		// Always update the resume results to coordinate partial and total
		this.internalCalculations(list);
		
		// Return the whole set
		return list;
	}
	
	// Return a sorted list with the three first parties plus others
	public List<PartyResults> calculateResults(Map<String,Party> partiesResult){

		this.updatedResults(partiesResult);
		
		// Return only the results according to specifications... calculated into updatedResults
		return this.getResults();
	}
	
	// Calculate results, grouped data
	private List<PartyResults> internalCalculations(List<Party> list){
		int chair = 0;
		int votes = 0;
		int counter = 0;

		if (!(list.size()>0)) return results;
		// Set to 0 totalVotes, not already calculated
		totalVotes=0;
		totalChairs=0;
		results.clear();

		for (Iterator it = list.iterator(); it.hasNext();) 
		{         
			Party p = (Party)it.next();             
			if (counter<3) {
				counter++;
				PartyResults pr = new PartyResults();
				pr.setChairs(p.getChairs());
				pr.setName(p.getName());
				pr.setVotes(p.getVotes());
				results.add(pr);
			}
			else {
				chair += p.getChairs();
				votes += p.getVotes();				
			}
			totalVotes += p.getVotes();
			totalChairs += p.getChairs();
		} 
		PartyResults other = new PartyResults();
		other.setName("Others");
		other.setChairs(chair);
		other.setVotes(votes);
		results.add(other);
		
		// We have the partial data calculated, now we only have to update the percentage and possible winner
		//		votes -- x
		//		total -- 100      x = votes*100/total
		//		
		//		chairs -- x
		//		650    -- 100     x = chairs*100/650
		
		for (Iterator it = results.iterator(); it.hasNext();){
			PartyResults p = (PartyResults) it.next();
			if (totalChairs>0) p.setPercChairs(p.getChairs()*100.0/650);
			if (totalVotes>0)  p.setPercVotes(p.getVotes()*100.0/totalVotes);
		}			
		return results;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public int getTotalChairs() {
		return totalChairs;
	}

	public List<PartyResults> getResults() {
		return results;
	}
	
	public void clear(){
		results.clear();
	}
		
}
