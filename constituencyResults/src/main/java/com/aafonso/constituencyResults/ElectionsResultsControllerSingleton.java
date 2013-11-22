package com.aafonso.constituencyResults;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130901
 *
 * Class: ElectionsResultsControllerSingleton
 * 
 * Extended Class of the project where we process and control the principal structures and data
 * 
 * This class is created as singleton to allow many producer and consumer share objects
 * without problems having different data and calculations without using threads features.
 * 
 */

public class ElectionsResultsControllerSingleton extends ElectionsResultsController {

	// Singleton
	private static ElectionsResultsControllerSingleton INSTANCE = null;
	
	// Constructor
	protected ElectionsResultsControllerSingleton(InterfaceResultsFactory resProd, AbstractResultsConsumer resCon) {
		super(resProd,resCon);
	}
	
	// Singleton instance
	public static synchronized ElectionsResultsControllerSingleton getInstance(InterfaceResultsFactory resProd, AbstractResultsConsumer resCon){
		if (INSTANCE == null){
			synchronized(ElectionsResultsControllerSingleton.class) {
				if (INSTANCE == null) {
					INSTANCE = new ElectionsResultsControllerSingleton(resProd,resCon);
				}
			}
		}
		return INSTANCE;
	}
	
	  public void clear()  
	  {  
		  INSTANCE = null;  
	  }  
}
