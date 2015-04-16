package com.bigbass1997.coreperms;

import com.bigbass1997.coreperms.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = CorePermissionsMod.MODID, version = CorePermissionsMod.VERSION, acceptableRemoteVersions = "*")
public class CorePermissionsMod {
	public static final String MODID = "CorePermissions";
	public static final String VERSION = "0.0";
	
	@Mod.Instance(MODID)
	public static CorePermissionsMod instance;
	
	@SidedProxy(clientSide = "com.bigbass1997.coreperms.proxy.ClientProxy", serverSide = "com.bigbass1997.coreperms.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e){
		proxy.preInit(e);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e){
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e){
	}
	
	@Mod.EventHandler
	public void serverStopping(FMLServerStoppingEvent e){
		proxy.stop();
	}
}
