package com.org.my.spare.model;

public class IssueSlip 
{

	String isueslipno,customer,issuedate;
	int count;
	String callnumber, machinenumber;
	String returnstatus;
	int rowid;
	
	
	public int getRowid() {
		return rowid;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	public String getIsueslipno() {
		return isueslipno;
	}
	public void setIsueslipno(String isueslipno) {
		this.isueslipno = isueslipno;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public String getMachinenumber() {
		return machinenumber;
	}
	public void setMachinenumber(String machinenumber) {
		this.machinenumber = machinenumber;
	}
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	
	
	
}
