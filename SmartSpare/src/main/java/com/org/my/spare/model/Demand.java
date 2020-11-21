package com.org.my.spare.model;

 
import java.util.List;

 
public class Demand
{
	 
	private int rowid,erowid,rowcount;
	 
	String demandno, storagelocation ,location, locationname ;
	String documenttype,customername,customeraddress;
	String creationtime,createdby,cancel,canceldate,canceledby;
	String contactname,contactnumber,callnumber;
	String machineserialnumber;
	int demandage; 
	List<DemandItem> items; 
	String status;
	
	

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



	public int getRowcount() {
		return rowcount;
	}



	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}



	public String getDemandno() {
		return demandno;
	}



	public void setDemandno(String demandno) {
		this.demandno = demandno;
	}



	public String getStoragelocation() {
		return storagelocation;
	}



	public void setStoragelocation(String storagelocation) {
		this.storagelocation = storagelocation;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getLocationname() {
		return locationname;
	}



	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}



	public String getDocumenttype() {
		return documenttype;
	}



	public void setDocumenttype(String documenttype) {
		this.documenttype = documenttype;
	}



	public String getCustomername() {
		return customername;
	}



	public void setCustomername(String customername) {
		this.customername = customername;
	}



	public String getCustomeraddress() {
		return customeraddress;
	}



	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}



	public String getCreationtime() {
		return creationtime;
	}



	public void setCreationtime(String creationtime) {
		this.creationtime = creationtime;
	}



	public String getCreatedby() {
		return createdby;
	}



	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}



	public String getCancel() {
		return cancel;
	}



	public void setCancel(String cancel) {
		this.cancel = cancel;
	}



	public String getCanceldate() {
		return canceldate;
	}



	public void setCanceldate(String canceldate) {
		this.canceldate = canceldate;
	}



	public String getCanceledby() {
		return canceledby;
	}



	public int getDemandage() {
		return demandage;
	}



	public void setDemandage(int demandage) {
		this.demandage = demandage;
	}



	public void setCanceledby(String canceledby) {
		this.canceledby = canceledby;
	}



	public List<DemandItem> getItems() {
		return items;
	}



	public void setItems(List<DemandItem> items) {
		this.items = items;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getContactname() {
		return contactname;
	}



	public void setContactname(String contactname) {
		this.contactname = contactname;
	}



	public String getContactnumber() {
		return contactnumber;
	}



	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}



	public String getCallnumber() {
		return callnumber;
	}



	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}



	public String getMachineserialnumber() {
		return machineserialnumber;
	}



	public void setMachineserialnumber(String machineserialnumber) {
		this.machineserialnumber = machineserialnumber;
	}



	public String toString()
	{
		return "Bean file for Demand";
		
	}

}
