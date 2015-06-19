package com.bigbass1997.coreperms.util;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.util.ChatComponentText;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bigbass1997.coreperms.ConfigManager;

public class Util {
	
	public static final Logger log = LogManager.getLogger("Overview");
	
	public static String getBlockName(Block block){
		String blockname = "null";
		
		try {
			blockname = block.getLocalizedName();
		} catch (NoSuchMethodError error){
			if(ConfigManager.debug) error.printStackTrace();
		}
		
		if(blockname == "null"){
			try {
				blockname = block.getUnlocalizedName();
			} catch (NoSuchMethodError error){
				if(ConfigManager.debug) error.printStackTrace();
			}
		}
		
		return blockname;
	}
	
	public static ChatComponentText getChatComponent(String s){
		return new ChatComponentText(s);
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
