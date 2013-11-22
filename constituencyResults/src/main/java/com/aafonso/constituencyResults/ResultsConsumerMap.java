package com.aafonso.constituencyResults;

import java.util.Map;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Class: ResultsConsumerMap
 * 
 * Process one file and store/update results in a Map 
 * 
 */

public class ResultsConsumerMap extends AbstractResultsConsumer {
	
	@Override
	protected void updatePartiesList(Map<String,Party> mParties){
		// Everything is fine, lets search for the winner and update all the votes
		Party ptWinner = new Party();
		for (Party pt:localPartiesResult){
			if (pt.getVotes()>ptWinner.getVotes()){
				ptWinner.setName(pt.getName());
				ptWinner.setVotes(pt.getVotes());
			}
			// Update votes
			Party p = mParties.get(pt.getName());
			if (p == null) {
				p = new Party();
				p.setName(pt.getName());
				p.setVotes(pt.getVotes());
			} 
			else p.setVotes(p.getVotes()+pt.getVotes());
			mParties.put(p.getName(),p);
		}	
		// Ok, now we will update the chair (only the winner has the constituency chair)
		Party p = mParties.get(ptWinner.getName());
		if (p == null) System.out.println(ptWinner.getName());
		p.setChairs(p.getChairs()+1);
		mParties.put(p.getName(),p);
	}
}
