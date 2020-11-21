package com.org.my.spare.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

 

/*The following class is used to modal for EQUIPMENT MAKE */

@Entity  
@Table(name="spare_m_equipement_make")  
public class EquipementMake  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*To declare the required variables based on database structure */
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "INT_MAKEID")  
	private int rowid;
	
	private int erowid;
	
	@Column(name = "CHR_MAKENAME")  
	private String name;
	
	@Column(name = "CHR_DESC")  
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
		return "Bean file for Equipement Make";
		
	}

}
