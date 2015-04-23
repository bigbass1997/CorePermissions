package com.bigbass1997.coreperms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		//TODO Add chat formating (e.g. colors etc)
	}
	
	/**
	 * @author http://stackoverflow.com/a/19459884/4816410
	 * @param path
	 * @return aBuffer or null
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static UUID convertUUID(String uuid){
		String set1 = uuid.substring(0, 7);
		String set2 = uuid.substring(8, 11);
		String set3 = uuid.substring(12, 15);
		String set4 = uuid.substring(16, 19);
		String set5 = uuid.substring(20);
		
		String DASH = "-";
		return UUID.fromString(set1 + DASH + set2 + DASH + set3 + DASH + set4 + DASH + set5);
	}
}
