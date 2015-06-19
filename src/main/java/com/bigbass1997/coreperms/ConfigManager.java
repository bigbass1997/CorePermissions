package com.bigbass1997.coreperms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;

import com.bigbass1997.coreperms.util.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigManager {
	
	public static boolean debug;

	public static String configJsonPath;
	public static JsonObject configJson;
	
	public static void loadConfig(FMLPreInitializationEvent e){
		configJsonPath = e.getModConfigurationDirectory() + "/CorePermissions.json";
		
		String configString = readFile(configJsonPath);
		if(configString == null || configString == ""){
			createConfig(e);
		}
		
		configJson = new JsonParser().parse(readFile(configJsonPath)).getAsJsonObject();
		
		//--
		
		debug = configJson.get("debug").getAsBoolean();
	}
	
	public static void reloadConfig(){
		configJson = new JsonParser().parse(readFile(configJsonPath)).getAsJsonObject();
		
		//--
		
		debug = configJson.get("debug").getAsBoolean();
	}
	
	private static void createConfig(FMLPreInitializationEvent e){
		try {
			File file = new File(configJsonPath);
			FileUtils.copyURLToFile(ConfigManager.class.getClassLoader().getResource("CorePermissions-default.json"), file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @author http://stackoverflow.com/a/19459884/4816410
	 * @author bigbass1997
	 * 
	 * @param path
	 * @return textFromFile or null
	 */
	private static String readFile(String path){
		try {
			File myFile = new File(path);
	        FileInputStream fIn;
			fIn = new FileInputStream(myFile);
			BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
	        String aDataRow = "";
	        String aBuffer = "";
	        while ((aDataRow = myReader.readLine()) != null) 
	        {
	            aBuffer += aDataRow ;
	        }
	        myReader.close();

			return aBuffer;
		} catch (FileNotFoundException e) {
			Util.log.info("File '" + path + "' not found! Creating default file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
