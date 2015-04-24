package com.bigbass1997.coreperms.proxy;

import net.minecraft.command.CommandHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

import com.bigbass1997.coreperms.PermissionsManager;
import com.bigbass1997.coreperms.commands.CommandReloadPerms;
import com.bigbass1997.coreperms.handlers.PlayerEventHandler;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy {
	
	public ServerProxy(){
		
	}
	
	@Override
	public void preInit(FMLPreInitializationEvent e){
		PermissionsManager.initialize(e);
	}
	
	@Override
	public void init(){
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		
		CommandHandler ch = (CommandHandler) MinecraftServer.getServer().getCommandManager();
		ch.registerCommand(new CommandReloadPerms());
	}
	
	@Override
	public void stop(){
		
	}
}
