package com.bigbass1997.coreperms.handlers;

import java.util.ArrayList;
import java.util.UUID;

import com.bigbass1997.coreperms.Group;
import com.bigbass1997.coreperms.Member;
import com.bigbass1997.coreperms.PermissionsManager;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;

public class PlayerEventHandler {
	
	@SubscribeEvent
	public void onServerChat(ServerChatEvent e){
		String message = e.message;
		String username = e.username;
		UUID uuid = e.player.getPersistentID();
		Member member = PermissionsManager.getMembers().get(uuid);
		
		ArrayList<Group> groups = member.getGroups();
		
		String tags = "";
		for(Group group : groups){
			tags.concat(group.getChatTag());
		}
		
		e.component = new ChatComponentTranslation(tags + "[" + username + "] " + message);
	}
}
