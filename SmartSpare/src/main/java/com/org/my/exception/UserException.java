package com.org.my.exception;

public class UserException extends RuntimeException 
{  
	 private static final long serialVersionUID = -4794572499177930357L;  
	   
	 private String exceptionMsg;  
	    
	 public UserException(String exceptionMsg) 
	 {  
	  this.exceptionMsg = exceptionMsg;  
	 }  
	   
	 public String getExceptionMsg()
	 {  
	  return this.exceptionMsg;  
	 }  
	   
	 public void setExceptionMsg(String exceptionMsg) 
	 {  
	  
		 this.exceptionMsg = exceptionMsg;  
	 }  
	
	 @Override
	 public String toString() 
	 {
			return "UserException [exceptionMsg=" + exceptionMsg + "]";
	 }
}  