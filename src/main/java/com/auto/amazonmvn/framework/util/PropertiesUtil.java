package com.auto.amazonmvn.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	public static Properties OR;
	public static Properties CONFIG;
	
	public static String getLocator(String locatorName) throws IOException {
	if(OR==null){
	FileInputStream fis=new FileInputStream("or\\or.properties");
	OR = new Properties();
	OR.load(fis);
	//System.out.println("or loaded");
	}
	String locatorValue=OR.getProperty(locatorName);
	//System.out.println(locatorValue);
	return locatorValue;
	}

	public static String getConfiurationValue(String configName) throws IOException{
	if(CONFIG==null){
		FileInputStream fis=new FileInputStream("config\\CONFIG.properties");
		CONFIG=new Properties();
		CONFIG.load(fis);	
	}
	String 	configValue=CONFIG.getProperty(configName);
	return configValue;	
	}
}
