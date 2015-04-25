package com.bigbass1997.coreperms.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.util.ChatComponentText;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
	
	public static final Logger log = LogManager.getLogger("Overview");
	
	public static final boolean debug = false;
	
	public static String getBlockName(Block block){
		String blockname = "null";
		
		try {
			blockname = block.getLocalizedName();
		} catch (NoSuchMethodError error){
			if(debug) error.printStackTrace();
		}
		
		if(blockname == "null"){
			try {
				blockname = block.getUnlocalizedName();
			} catch (NoSuchMethodError error){
				if(debug) error.printStackTrace();
			}
		}
		
		return blockname;
	}
	
	public static ChatComponentText getChatComponent(String s){
		return new ChatComponentText(s);
	}
	
	/**
	 * @author http://stackoverflow.com/a/19459884/4816410
	 * @author bigbass1997
	 * 
	 * @param path
	 * @return textFromFile or null
	 */
	public static String readFile(String path){
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
			Util.log.info("Permissions file not found! Creating default file.");
			createDefaultPermsConfig(path);
			return readFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static void createDefaultPermsConfig(String path){
		try {
			File file = new File(path);
			file.createNewFile();

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(DefaultConfig.permsConfig);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UUID convertUUID(String uuid){
		if(uuid.length() != 32 && uuid.length() != 36){
			Util.log.warn("Util.convertUUID() invalid uuid: " + uuid);
			return null;
		}
		if(!uuid.contains("-")){
			String set1 = uuid.substring(0, 8);
			String set2 = uuid.substring(8, 12);
			String set3 = uuid.substring(12, 16);
			String set4 = uuid.substring(16, 20);
			String set5 = uuid.substring(20);
			
			String DASH = "-";
			String s = set1 + DASH + set2 + DASH + set3 + DASH + set4 + DASH + set5;
			
			return UUID.fromString(s);
		} else {
			return UUID.fromString(uuid);
		}
	}
}
