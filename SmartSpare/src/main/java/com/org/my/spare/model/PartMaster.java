package com.org.my.spare.model;



/*The following class is used to modal for GeneralGroup */

public class PartMaster
{
	/*To declare the required variables based on database structure */
	private int rowid,erowid;
	private String name;
	private String oemname;
	private String description;
	private int technicalgroupid;
	private String technicaldescription;
	private String modelname;
	private String generalgroupname;
	private int modelid;
	private int generalgroupid;
	
	
	 
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
	
	
	public String getTechnicaldescription() {
		return technicaldescription;
	}
	public void setTechnicaldescription(String technicaldescription) {
		this.technicaldescription = technicaldescription;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getGeneralgroupname() {
		return generalgroupname;
	}
	public void setGeneralgroupname(String generalgroupname) {
		this.generalgroupname = generalgroupname;
	}
	public int getModelid() {
		return modelid;
	}
	public void setModelid(int modelid) {
		this.modelid = modelid;
	}
	public int getGeneralgroupid() {
		return generalgroupid;
	}
 
	public int getTechnicalgroupid() {
		return technicalgroupid;
	}
	public void setTechnicalgroupid(int technicalgroupid) {
		this.technicalgroupid = technicalgroupid;
	}
	public void setGeneralgroupid(int generalgroupid) {
		this.generalgroupid = generalgroupid;
	}
	public String toString()
	{
		return "Bean file for PartMaster";
		
	}
	public String getOemname() {
		return oemname;
	}
	public void setOemname(String oemname) {
		this.oemname = oemname;
	}

}
