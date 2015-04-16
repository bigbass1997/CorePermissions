package com.bigbass1997.coreperms.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;

public class PlayerEventHandler {
	
	@SubscribeEvent
	public void playerNameFormat(NameFormat e){
		//Chat formatting for groups' prefix/suffixes.
	}
	
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerLoggedInEvent e){
		
	}
}
