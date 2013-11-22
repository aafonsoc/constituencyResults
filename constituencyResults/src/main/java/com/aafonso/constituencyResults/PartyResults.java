package com.aafonso.constituencyResults;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Class: PartyResults
 * 
 * Extended Class to store the results and percentages for a subset of parties and "others"
 * 
 */

public class PartyResults extends Party {
	private double percChairs = 0;
	private double percVotes = 0;
	public double getPercChairs() {
		return percChairs;
	}
	public void setPercChairs(double percChairs) {
		this.percChairs = percChairs;
	}
	public double getPercVotes() {
		return percVotes;
	}
	public void setPercVotes(double percVotes) {
		this.percVotes = percVotes;
	}
}
