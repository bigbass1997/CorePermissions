package com.bigbass1997.coreperms.commands;

import java.util.ArrayList;

import com.bigbass1997.coreperms.Member;
import com.bigbass1997.coreperms.PermissionsManager;
import com.bigbass1997.coreperms.util.Util;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandGetPerms extends CommandBase {

	@Override
	public String getCommandName() {
		return "getperms";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/getperms";
	}
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender){
        return true;
    }

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		
		Member member = PermissionsManager.getMembers().get(sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).getPersistentID());
		if(member == null){
			return;
		}else{
			if(member.getPerms().isEmpty()){
				sender.addChatMessage(Util.getChatComponent("You have no permissions."));
				return;
			}
		}
		
		sender.addChatMessage(Util.getChatComponent("Your permission nodes:"));
		ArrayList<String> memberPerms = member.getPerms();
		for(String s : memberPerms){
			sender.addChatMessage(Util.getChatComponent(s));
		}
	}

}
