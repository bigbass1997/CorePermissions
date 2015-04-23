package com.bigbass1997.coreperms;

import java.util.ArrayList;
import java.util.UUID;

public class Member {
	
	private UUID uuid;
	
	private ArrayList<String> perms; //Player specific perms.
	private ArrayList<Group> groups;
	
	public Member(UUID uuid, ArrayList<Group> groups, ArrayList<String> perms){
		this.uuid = uuid;
		this.perms = perms;
		this.groups = groups;
	}
	
	public UUID getUUID(){
		return uuid;
	}
	
	public void addPerm(String perm){
		this.perms.add(perm);
	}
	
	public void addPerms(ArrayList<String> perms){
		this.perms.addAll(perms);
	}
	
	public ArrayList<String> getPerms(){
		ArrayList<String> allPerms = new ArrayList<String>();
		allPerms.addAll(this.perms);
		
		for(Group group : groups){
			allPerms.addAll(PermissionsManager.getGroups().get(group.name).getPerms());
		}
		
		return allPerms;
	}
	
	public ArrayList<Group> getGroups(){
		return groups;
	}
}
