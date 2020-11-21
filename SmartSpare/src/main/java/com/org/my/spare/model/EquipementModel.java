package com.org.my.spare.model;



/*The following class is used to modal for EQUIPMENT TYPE */

public class EquipementModel  
{
	/*To declare the required variables based on database structure */
	private int rowid,erowid;
	private int equipementtypeid;
	private int equipementmakeid;
	 
	private String name;
	private String description;
	private String equipementtypename;
	private String equipementmake;
	 
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
	
	
	public int getEquipementtypeid() {
		return equipementtypeid;
	}
	public void setEquipementtypeid(int equipementtypeid) {
		this.equipementtypeid = equipementtypeid;
	}
	
	
	 
	
	
	public String getEquipementtypename() {
		return equipementtypename;
	}
	public void setEquipementtypename(String equipementtypename) {
		this.equipementtypename = equipementtypename;
	}
	public int getEquipementmakeid() {
		return equipementmakeid;
	}
	public void setEquipementmakeid(int equipementmakeid) {
		this.equipementmakeid = equipementmakeid;
	}
	public String getEquipementmake() {
		return equipementmake;
	}
	public void setEquipementmake(String equipementmake) {
		this.equipementmake = equipementmake;
	}
	public String toString()
	{
		return "Bean file for Equipement Model";
		
	}

}
