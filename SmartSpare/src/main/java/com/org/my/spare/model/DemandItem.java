package com.org.my.spare.model;

 
public class DemandItem 
{

	int subrowid;
	int partcode,iquantity;
	double ivalue;
	String idescription  ;
	String demandno;
	String partcodename;
	int generalgroupid;
	String generalgroupname;
	String idefectiveserialnumber;
	
	
	 

	public int getSubrowid() {
		return subrowid;
	}




	public void setSubrowid(int subrowid) {
		this.subrowid = subrowid;
	}




	public int getPartcode() {
		return partcode;
	}




	public void setPartcode(int partcode) {
		this.partcode = partcode;
	}




	public int getIquantity() {
		return iquantity;
	}




	public void setIquantity(int iquantity) {
		this.iquantity = iquantity;
	}




	public double getIvalue() {
		return ivalue;
	}




	public void setIvalue(double ivalue) {
		this.ivalue = ivalue;
	}




	public String getIdescription() {
		return idescription;
	}




	public void setIdescription(String idescription) {
		this.idescription = idescription;
	}




	public String getDemandno() {
		return demandno;
	}




	public void setDemandno(String demandno) {
		this.demandno = demandno;
	}




	public String getPartcodename() {
		return partcodename;
	}




	public void setPartcodename(String partcodename) {
		this.partcodename = partcodename;
	}




	public int getGeneralgroupid() {
		return generalgroupid;
	}




	public void setGeneralgroupid(int generalgroupid) {
		this.generalgroupid = generalgroupid;
	}




	public String getGeneralgroupname() {
		return generalgroupname;
	}




	public void setGeneralgroupname(String generalgroupname) {
		this.generalgroupname = generalgroupname;
	}




	 



	public String getIdefectiveserialnumber() {
		return idefectiveserialnumber;
	}




	public void setIdefectiveserialnumber(String idefectiveserialnumber) {
		this.idefectiveserialnumber = idefectiveserialnumber;
	}




	public String toString()
	{
		return "Bean file for DemandItem";
		
	}

}
