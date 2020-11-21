package com.org.my.spare.model;



/*The following class is used to modal for PROBLEM GROUP */

public class ProblemGroup
{
	/*To declare the required variables based on database structure */
	private int rowid,erowid;
	private String name;
	private String description;
	
	
	 
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
		return "Bean file for Problems Group";
		
	}

}
