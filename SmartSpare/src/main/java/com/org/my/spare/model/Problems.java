package com.org.my.spare.model;

import java.util.Comparator;



/*The following class is used to modal for PROBLEM GROUP */

public class Problems implements Comparator
{
	/*To declare the required variables based on database structure */
	private int rowid,erowid;
	private String name;
	private String description;
	private int groupid;
	private String groupname;
	
	 
	 
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public int getErowid() {
		return erowid;
	}
	public void setErowid(int erowid) {
		this.erowid = erowid;
	}
	public int getRowid() {
		return rowid;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String toString()
	{
		return "Bean file for Problems";
		
	}
	
	
	@Override
	public int compare(Object o1, Object o2) 
	{
		// TODO Auto-generated method stub
		Problems s1=(Problems)o1;  
		Problems s2=(Problems)o2;  
		return s1.name.compareTo(s2.name);  
	}

}
