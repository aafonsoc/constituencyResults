package com.aafonso.constituencyResults;

import java.sql.SQLException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author aafonso
 * 
 * Alfonso Afonso
 * Assignment BBC Programming Test 
 * Date of Revision: 20130903
 *
 * Unit Test
 *  
 * We have two different set of test to show how could be used our Controller
 * 
 * - Threads test (three process generating data and one reading)
 * - Singleton simple use
 * 
 */

public class ElecctionResultsControllerTest extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public ElecctionResultsControllerTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( ElecctionResultsControllerTest.class );
	}
	
	/**
	 * Simple  Test with Map Structure for Factory
	 * @throws JAXBException 
	 * @throws InterruptedException 
	 */
	public void testingMapApp() throws JAXBException {
		System.out.println("---------------");
		System.out.println("Normal Map Test");
		System.out.println("---------------");		
		// Creating factory
		ResultsFactoryMap rf = new ResultsFactoryMap("election-results");
		// Creating consumer
		ResultsConsumerMap rc = new ResultsConsumerMap();
		
		// Clean Factory
		rf.cleanFactoryResults();
		// Clean Results
		rc.cleanResults();
		
		// Testing Class, generating result and show'em
		ElectionsResultsController gen = new ElectionsResultsController(rf,rc);

		// Clean possible static data between test (not a normal issue)
		gen.cleanResults();
		// Do test
		gen.run();    	
		gen.showListParties();
		gen.showResults();
	}

	/**
	 * Simple  Test with JDBC Structure for Factory
	 * @throws JAXBException 
	 * @throws InterruptedException 
	 */

	public void testingDBApp() throws JAXBException, SQLException{
		System.out.println("---------------");
		System.out.println("Normal Db Test");
		System.out.println("---------------");		
		// Creating factory
		ResultsFactoryDb rf = new ResultsFactoryDb("election-results");
		// Creating consumer
		ResultsConsumerDb rc = new ResultsConsumerDb();
		
		// Clean Factory
		rf.cleanFactoryResults();
		// Clean Results
		rc.cleanResults();

		// Testing Class, generating result and show'em
		ElectionsResultsController gen = new ElectionsResultsController(rf,rc);
		
		// Clean possible static data between test (not a normal issue)
		gen.cleanResults();
		// Do test
		gen.run();    	
		gen.showListParties();
		gen.showResults();
	}
	
	/**
	 * Simple Singleton Test with Map Structure for Factory
	 * @throws JAXBException 
	 * @throws InterruptedException 
	 */
	public void testSingletonMapApp() throws JAXBException{
		System.out.println("------------------");
		System.out.println("Singleton Map Test");
		System.out.println("------------------");		
		// Creating factory
		ResultsFactoryMap rf = new ResultsFactoryMap("election-results");
		// Creating consumer
		ResultsConsumerMap rc = new ResultsConsumerMap();

		// Clean Factory
		rf.cleanFactoryResults();
		// Clean Results
		rc.cleanResults();
		
		// Testing Singleton Class, generating result and show'em
		ElectionsResultsControllerSingleton genProd = null;
		ElectionsResultsControllerSingleton genInfo = null;
		genProd = ElectionsResultsControllerSingleton.getInstance(rf,rc);
		genInfo = ElectionsResultsControllerSingleton.getInstance(rf,rc);
		
		// Clean possible static data between test (not a normal issue)
		genProd.cleanResults();
		// Do test
		genProd.run();    	
		genProd.showListParties();
		genInfo.showResults();
		// As is a Singleton Class and to be able to Test all, we have to clear INSTANCE 
		genProd.clear();
	}
	
	/**
	 * Simple Singleton Test with JDBC Structure for Factory
	 * @throws JAXBException 
	 * @throws InterruptedException 
	 */
	public void testSingletonDbApp() throws JAXBException{
		System.out.println("-----------------");
		System.out.println("Singleton Db Test");
		System.out.println("-----------------");		
		// Creating factory
		ResultsFactoryDb rf = new ResultsFactoryDb("election-results");
		// Creating consumer
		ResultsConsumerDb rc = new ResultsConsumerDb();

		// Clean Factory
		rf.cleanFactoryResults();
		// Clean Results
		rc.cleanResults();
		
		// Testing Singleton Class, generating result and show'em
		ElectionsResultsControllerSingleton genProd = null;
		ElectionsResultsControllerSingleton genInfo = null;
		genProd = ElectionsResultsControllerSingleton.getInstance(rf,rc);
		genInfo = ElectionsResultsControllerSingleton.getInstance(rf,rc);
		
		// Clean possible static data between test (not a normal issue)
		genProd.cleanResults();
		// Do test
		genProd.run();    	
		genProd.showListParties();
		genInfo.showResults();
		// As is a Singleton Class and to be able to Test all, we have to clear INSTANCE 
		genProd.clear();
	}

	/**
	 * Threads Test with Map Structure for Factory
	 * @throws JAXBException 
	 * @throws InterruptedException 
	 */
	public void testThreadMapApp() throws JAXBException, InterruptedException{
		System.out.println("---------------");
		System.out.println("Thread Map Test");
		System.out.println("---------------");		
		// Creating factory
		ResultsFactoryMap rf = new ResultsFactoryMap("election-results");
		// Creating consumer
		ResultsConsumerMap rc = new ResultsConsumerMap();

		// Clean Factory
		rf.cleanFactoryResults();
		// Clean Results
		rc.cleanResults();

		// Testing Thread Class, generating result and show'em
		ElectionsResultsControllerThread genProd_1 = new ElectionsResultsControllerThread(rf,rc);
		ElectionsResultsControllerThread genProd_2 = new ElectionsResultsControllerThread(rf,rc);
		ElectionsResultsControllerThread genProd_3 = new ElectionsResultsControllerThread(rf,rc);
		ElectionsResultsControllerThread genInfo = new ElectionsResultsControllerThread(rf,rc);
		
		// Clean possible static data between test (not a normal issue)
		genProd_1.cleanResults();
		// We launch three threads to process files 
		Thread t1 = new Thread(genProd_1);
		t1.start();
		Thread t2 = new Thread(genProd_2);
		t2.start();
		Thread t3 = new Thread(genProd_3);
		t3.start();
		Thread t4 = new Thread(genInfo);
		t4.start();

		if (Thread.currentThread().getName().equals("main")) {		
			for (int i=0; i<=5; i++){
				System.out.println("Info process, loop number "+i);
				genInfo.showResults();
				Thread.sleep(2000);
			}
		}
	}
}
