package com.bigbass1997.coreperms;

import java.util.ArrayList;
import java.util.UUID;

public class Group {
	
	public final String name;
	
	//TODO Might need to rethink how member specific perms are loaded. And in turn how groups store members.
	private ArrayList<Member> members;
	private ArrayList<String> perms; //Group specific perms.
	
	private String prefix, suffix;
	
	public Group(String name){
		this.name = name;
		
		members = new ArrayList<Member>();
		perms = new ArrayList<String>();
		
		prefix = suffix = "";
	}
	
	public String getChatTag(){
		return prefix.concat(name).concat(suffix);
	}
	
	public void setPrefix(String prefix){
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix){
		this.suffix = suffix;
	}
	
	public void addPlayer(UUID uuid){
		members.add(new Member(uuid));
	}
	
	public void removePlayer(UUID uuid){
		//members.remove()
	}
}
