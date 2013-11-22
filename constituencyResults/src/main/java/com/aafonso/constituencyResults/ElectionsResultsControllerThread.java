package com.aafonso.constituencyResults;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130901
 *
 * Class: ElectionsResultsControllerThreads
 * 
 * Extended Class of the project where we process and control the principal structures and data
 *  
 * We have the Runnable implementation to allow thread working. 
 * 
 */

public class ElectionsResultsControllerThread extends ElectionsResultsController implements Runnable {

	public ElectionsResultsControllerThread(InterfaceResultsFactory resProd, AbstractResultsConsumer resCon) {
		super(resProd,resCon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void LoadDataToAnalyze() {
		super.LoadDataToAnalyze();
	}
	
	@Override
	public synchronized boolean processFile(){
		return super.processFile();
	}

}
