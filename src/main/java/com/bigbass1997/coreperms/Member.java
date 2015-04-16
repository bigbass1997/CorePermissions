package com.bigbass1997.coreperms;

import java.util.ArrayList;
import java.util.UUID;

public class Member {
	
	private UUID uuid;
	
	private ArrayList<String> perms; //Player specific perms.
	
	public Member(UUID uuid){
		this.uuid = uuid;
		
		perms = new ArrayList<String>();
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
		return this.perms;
	}
}
