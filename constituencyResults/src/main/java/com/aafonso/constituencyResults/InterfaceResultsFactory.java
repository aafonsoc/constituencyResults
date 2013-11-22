package com.aafonso.constituencyResults;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Class: AbstractResultsFactory
 * 
 * Abstract Factory, only basic methods, based on "product" String and a PathTo Files
 * 
 * The implementation will be in charge to give one result (while available)
 * and to load and control the list of results
 * 
 */

public interface InterfaceResultsFactory {
    
	// Do whatever need to have available Results for Factory
    public abstract void loadFactorytResults();
    
    // Do whatever need to clean all elements from Factory
    public abstract void cleanFactoryResults();
    
	// Get Results
    public abstract String getResult();
	
    // Update data 
	public abstract void setStatusResult(String fkey, int val);
	
	// Return the value of data
	public abstract int getStatusResult(String fkey);
	
	// Number of elements
	public abstract int getNumResults();
}
