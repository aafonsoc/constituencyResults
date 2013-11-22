package com.aafonso.constituencyResults.utils;

import java.io.File;
import java.util.Map;

/**
 * @author aafonso
 * 
 *  Alfonso Afonso
 *  Assignment BBC Programming Test 
 *  Date of Revision: 20130901
 */

/**
 * @author aafonso
 *
 * Class: fileUtils
 * 
 * Static method to load list of files in a folder into a Map
 * 
 */

public class FileUtils {

	/**
	     * List all the files under a directory
	     * @param directoryName to be listed
	     */
    public static boolean getlistFiles(String directoryName, Map<String,Integer> res){
    	try {
        	File directory = new File(directoryName);	 	
			//get all the files from a directory
			File[] fList = directory.listFiles();

			for (File file : fList){
				if (file.isFile()){
					//System.out.println(file.getName());
					if (!res.containsKey(directoryName+"/"+file.getName())) 
						res.put(directoryName+"/"+file.getName(), 0);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return true;
	    }
}
	    
