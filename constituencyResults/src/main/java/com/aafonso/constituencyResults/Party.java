package com.aafonso.constituencyResults;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Class: Party
 * 
 * Class to store the results of a Party. We will store total results (chairs and votes)
 * 
 */

public class Party {

	private String name = "";
	private int chairs = 0;
	private int votes = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChairs() {
		return chairs;
	}
	public void setChairs(int chairs) {
		this.chairs = chairs;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
}
