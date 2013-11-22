package com.aafonso.constituencyResults;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aafonso.constituencyResults.utils.FileUtils;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Class: ResultsFactoryMap
 * 
 * Factory that load all files to process and give the next element available
 * 
 * We use an Iterator to go through the available results
 * 
 * We also are able to load or reload data controlling already processed data
 * and when reloaded, Iterator is reinitilized
 * 
 * If we need to start again, we can clean the list and load all the "products" again
 * 
 * We store the data in a Map to have the chance of mark processed or bad files
 * 
 */

@SuppressWarnings({"rawtypes","unchecked"})
public class ResultsFactoryMap implements InterfaceResultsFactory {
	
	// Log 
	private Logger log = LoggerFactory.getLogger(ElectionsResultsController.class);

	// Basic configuration
	public String pathToFiles;

	// List of possibles resources to process... files
	private Map<String,Integer> fileResultList = new TreeMap<String,Integer>();
	// Iterator
	private Iterator it;
	
	// Constructor
	public ResultsFactoryMap(String pathTo){
		pathToFiles=pathTo;
	}

	public void loadFactorytResults() {
		// Charge the list of elements to process
        FileUtils.getlistFiles(pathToFiles,fileResultList);
        log.info("Folder loaded, list of products ready "+fileResultList.size());
        // Iterator
        it = fileResultList.entrySet().iterator();
	}

	public void cleanFactoryResults() {
		// Delete all elements
		if (fileResultList!=null) {
			fileResultList.clear();
			it = null;
		}
	}
	
	// Advice - We define this method as synchronized 
	public synchronized String getResult() {
		// We return the next filename available to process
		if (fileResultList!=null && !fileResultList.isEmpty()){
			// If there is available elements, return it
			while (it.hasNext()) {
				Map.Entry<String,Integer> entry = (Entry<String, Integer>) it.next();
				if (entry.getValue() == 0 ){
					entry.setValue(1);
					return entry.getKey();
				}
			}
		}
		return null;
	}
	
	public void setStatusResult(String fkey, int val){
		fileResultList.put(fkey, val);
	}
	
	public int getStatusResult(String fkey){
		if (fileResultList.containsKey(fkey))
			return fileResultList.get(fkey);
		else return -9999;
	}
	
	public int getNumResults(){
		if (fileResultList!=null)
			return fileResultList.size();
		else 
			return 0;
	}
}
