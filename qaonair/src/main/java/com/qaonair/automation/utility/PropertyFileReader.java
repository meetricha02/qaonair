package com.qaonair.automation.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import eCommerce.qaonair.App;

public class PropertyFileReader {
	private static Properties props;
	

	static{
		
			props= new Properties();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream("C:\\Users\\Richa\\Desktop\\Richa\\Workspace1\\qaonair\\dataReading.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				props.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	
	// Returns the data from properties file//
	public static String getProperty(String key){
		return props.getProperty(key);
		
	}
	
	
	}
	
	

