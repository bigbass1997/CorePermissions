package com.bigbass1997.coreperms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.command.ICommandSender;

import com.bigbass1997.coreperms.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PermissionsManager {
	
	public static final String NO_COMMAND_PERMS = "You do not have permission to use that command!";
	
	private static JsonElement permsJson;
	private static String permsConfigPath;
	
	private static Map<String, Group> groups;
	private static Map<UUID, Member> members;
	
	public static void initialize(FMLPreInitializationEvent e){
		permsConfigPath = e.getModConfigurationDirectory() + "/permissions.json";
		permsJson = new JsonParser().parse(Util.readFile(permsConfigPath));
		
		groups = new HashMap<String, Group>();
		members = new HashMap<UUID, Member>();
		
		populateGroups();
		populateMembers();
	}
	
	/**
	 * Use this to check if a command sender has permission to execute the command based on the provided node.
	 * 
	 * @param node Permission Node (e.g. "mod.commandname")
	 * @param sender Person who executed the command
	 * @return true if command sender has the provided permission node
	 */
	public static boolean hasPermission(String node, ICommandSender sender){
		String senderName = sender.getCommandSenderName();
		
		if(senderName.equalsIgnoreCase("server")) return true;
		if(hasPermission(node, sender.getEntityWorld().getPlayerEntityByName(senderName).getPersistentID())) return true;
		
		return false;
	}
	
	/**
	 * Almost identical to hasPermission(String node, ICommandSender sender) except that you need to pass a UUID instead of a command sender.
	 * 
	 * @param node Permission Node (e.g. "mod.commandname")
	 * @param uuid UUID of player
	 * @return true of player has the permission node provided
	 */
	public static boolean hasPermission(String node, UUID uuid){
		if(uuid.equals("console")) return true;
		
		Member member = members.get(uuid);
		
		return member.getPerms().contains(node);
	}
	
	public static Map<String, Group> getGroups(){
		return groups;
	}
	
	public static Map<UUID, Member> getMembers(){
		return members;
	}
	
	private static void populateGroups(){
		JsonArray groupsArray = permsJson.getAsJsonObject().getAsJsonArray("groups");
		
		for(JsonElement el : groupsArray){
			String groupName = el.getAsJsonObject().get("name").getAsString();
			
			ArrayList<String> groupPerms = new ArrayList<String>();
			for(JsonElement s : el.getAsJsonObject().get("perms").getAsJsonArray()){
				groupPerms.add(s.getAsString());
			}
			
			String pre = el.getAsJsonObject().get("prefix").getAsString();
			String suf = el.getAsJsonObject().get("suffix").getAsString();
			
			boolean showTag = el.getAsJsonObject().get("showTag").getAsBoolean();
			
			groups.put(groupName, new Group(groupName, groupPerms, pre, suf, showTag));
		}
	}
	
	private static void populateMembers(){
		JsonArray membersArray = permsJson.getAsJsonObject().getAsJsonArray("users");
		
		for(JsonElement el : membersArray){
			UUID uuid = Util.convertUUID(el.getAsJsonObject().get("uuid").getAsString());
			System.out.println(el.getAsJsonObject().get("uuid").getAsString());
			System.out.println(uuid);
			
			ArrayList<Group> memberGroups = new ArrayList<Group>();
			for(JsonElement group : el.getAsJsonObject().get("groups").getAsJsonArray()){
				memberGroups.add(groups.get(group.getAsString()));
			}
			
			ArrayList<String> memberPerms = new ArrayList<String>();
			for(JsonElement perm : el.getAsJsonObject().get("perms").getAsJsonArray()){
				memberPerms.add(perm.getAsString());
			}
			
			members.put(uuid, new Member(uuid, memberGroups, memberPerms));
		}
	}
	
	public ArrayList<Group> getPlayerGroups(UUID uuid){
		return members.get(uuid).getGroups();
	}
	
	public static void reloadPerms(){
		permsJson = new JsonParser().parse(Util.readFile(permsConfigPath));
		
		groups.clear();
		members.clear();
		
		populateGroups();
		populateMembers();
	}
}
