package com.bigbass1997.coreperms;

import java.io.File;
import java.util.ArrayList;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class PermissionsManager {
	
	private static Configuration permsConfig;
	
	private static ArrayList<Group> groups;
	
	public static void initialize(FMLPreInitializationEvent e){
		permsConfig = new Configuration(new File(e.getModConfigurationDirectory().getAbsolutePath() + "perms.cfg"));
		
		groups = new ArrayList<Group>();
		
		populateGroups();
	}
	
	private static void populateGroups(){
		
	}
}
