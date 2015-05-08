package com.bigbass1997.coreperms.commands;

import com.bigbass1997.coreperms.PermissionsManager;
import com.bigbass1997.coreperms.util.Util;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandReloadPerms extends CommandBase {

	@Override
	public String getCommandName() {
		return "reloadperms";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/reloadperms";
	}
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender){
        return true;
    }
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(PermissionsManager.hasPermission("cp.reload", sender)){
			PermissionsManager.reloadPerms();
			sender.addChatMessage(Util.getChatComponent("CorePermissions Permissions Config Reloaded!"));
		} else {
			sender.addChatMessage(Util.getChatComponent(PermissionsManager.NO_COMMAND_PERMS));
		}
	}

}
