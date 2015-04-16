package com.bigbass1997.coreperms.proxy;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy {
	
	public ServerProxy(){
		
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		config.save();
	}
	
	@Override
	public void init(){
		
	}
	
	@Override
	public void stop(){
		
	}
}
