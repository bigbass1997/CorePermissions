package com.bigbass1997.coreperms;

import java.util.ArrayList;

public class Group {
	
	public final String name;
	
	private ArrayList<String> perms; //Group specific perms.
	
	private String prefix, suffix;
	
	private boolean showTag;
	
	public Group(String name, ArrayList<String> perms, String prefix, String suffix, boolean showTag){
		this.name = name;
		
		this.prefix = prefix;
		this.suffix = suffix;
		
		this.showTag = showTag;
		
		this.perms = new ArrayList<String>();
		this.perms.addAll(perms);
	}
	
	public String getChatTag(){
		if(showTag) return prefix.concat(name).concat(suffix);
		else return "";
	}
	
	public ArrayList<String> getPerms(){
		return perms;
	}
	
	@Override
	public String toString(){
		return "Group[" + name + "]";
	}
}
