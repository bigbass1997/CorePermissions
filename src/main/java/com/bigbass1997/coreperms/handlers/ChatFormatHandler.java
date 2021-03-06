package com.bigbass1997.coreperms.handlers;

import java.util.ArrayList;
import java.util.UUID;

import com.bigbass1997.coreperms.Group;
import com.bigbass1997.coreperms.Member;
import com.bigbass1997.coreperms.PermissionsManager;
import com.bigbass1997.coreperms.util.Util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;

public class ChatFormatHandler {
	
	public static String userPrefix = "[", userSuffix = "] ";
	
	@SubscribeEvent
	public void onServerChat(ServerChatEvent e){
		String message = e.message;
		String username = e.username;
		UUID uuid = e.player.getPersistentID();
		Member member = PermissionsManager.getMembers().get(uuid);
		
		if(member == null){
			e.component = new ChatComponentTranslation("%s%s", Util.getChatComponent(userPrefix + username + userSuffix), Util.getChatComponent(message));
			return;
		}
		
		ArrayList<Group> groups = member.getGroups();
		
		String tags = "";
		for(Group group : groups){
			tags = tags.concat(group.getChatTag());
		}
		
		e.component = new ChatComponentTranslation("%s%s%s", Util.getChatComponent(tags), Util.getChatComponent(userPrefix + username + userSuffix), Util.getChatComponent(message));
	}
}
