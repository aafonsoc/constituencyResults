package com.aafonso.constituencyResults.utils;

public class GenericUtils {

	public static void Sleep(int msec){
		// Sleep a while to see results 
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
