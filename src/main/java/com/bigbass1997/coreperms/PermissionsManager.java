package com.bigbass1997.coreperms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.bigbass1997.coreperms.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PermissionsManager {
	
	private static JsonElement permsJson;
	
	private static Map<String, Group> groups;
	private static Map<UUID, Member> members;
	
	public static void initialize(FMLPreInitializationEvent e){
		permsJson = new JsonParser().parse(Util.readFile(e.getModConfigurationDirectory() + "/permissions.json"));
		
		groups = new HashMap<String, Group>();
		members = new HashMap<UUID, Member>();
		
		populateGroups();
		populateMembers();
		
		for(Member m : members.values()){
			System.out.println(m.getUUID().toString());
			System.out.println(m.getPerms());
		}
	}
	
	public static Map<String, Group> getGroups(){
		return groups;
	}
	
	private static void populateGroups(){
		JsonArray groupsArray = permsJson.getAsJsonObject().getAsJsonArray("groups");
		
		for(JsonElement el : groupsArray){
			String groupName = el.getAsJsonObject().get("name").getAsString();
			
			ArrayList<String> groupPerms = new ArrayList<String>();
			for(JsonElement s : el.getAsJsonObject().get("perms").getAsJsonArray()){
				groupPerms.add(s.getAsString());
			}
			
			groups.put(groupName, new Group(groupName, groupPerms));
		}
	}
	
	private static void populateMembers(){
		JsonArray membersArray = permsJson.getAsJsonObject().getAsJsonArray("users");
		
		System.out.println("Members:");
		for(JsonElement el : membersArray){
			UUID uuid = Util.convertUUID(el.getAsJsonObject().get("uuid").getAsString());
			
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
}
