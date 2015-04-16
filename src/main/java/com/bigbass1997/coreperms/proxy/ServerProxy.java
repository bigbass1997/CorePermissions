package com.bigbass1997.coreperms.proxy;

import com.bigbass1997.coreperms.handlers.PlayerEventHandler;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
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
		FMLCommonHandler.instance().bus().register(new PlayerEventHandler());
	}
	
	@Override
	public void stop(){
		
	}
}
