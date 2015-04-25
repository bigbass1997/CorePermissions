package com.bigbass1997.coreperms.handlers;

import java.util.ArrayList;
import java.util.UUID;

import com.bigbass1997.coreperms.Group;
import com.bigbass1997.coreperms.Member;
import com.bigbass1997.coreperms.PermissionsManager;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.event.ServerChatEvent;

public class ChatFormatHandler {
	
	@SubscribeEvent
	public void onServerChat(ServerChatEvent e){
		String message = e.message;
		String username = e.username;
		UUID uuid = e.player.getPersistentID();
		Member member = PermissionsManager.getMembers().get(uuid);
		
		System.out.println(PermissionsManager.getMembers().toString());
		
		System.out.println(username);
		System.out.println(uuid);
		System.out.println(member);
		System.out.println(member.getUUID().toString());
		System.out.println(member.getPerms());
		System.out.println(member.getGroups());
		
		ArrayList<Group> groups = member.getGroups();
		
		String tags = "";
		for(Group group : groups){
			tags = tags.concat(group.getChatTag());
		}
		
		e.component = new ChatComponentTranslation(tags + "[" + username + "] " + message);
	}
}