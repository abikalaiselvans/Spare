package com.org.my;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainpageController 
{

	
	@RequestMapping("Commonindex")
	public String Common(Locale locale, Model model) 
	{
		return "Smart Common/index";
	}
	
	@RequestMapping(value = "HRMindex")
	public String HRM(Locale locale, Model model) 
	{
		return "Smart HRM/index";
	}
	
	@RequestMapping("Attendanceindex")
	public String Attendance(Locale locale, Model model) 
	{
		return "Smart Attendance/index";
	}
	
	
	@RequestMapping("Payrollindex")
	public String Payroll(Locale locale, Model model) 
	{
		return "Smart Payroll/index";
	}
	
	
	@RequestMapping("Inventoryindex")
	public String Inventory(Locale locale, Model model) 
	{
		return "Smart Inventory/index";
	}
	
	
	@RequestMapping("Conveyanceindex")
	public String Conveyance(Locale locale, Model model) 
	{
		return "Smart Conveyance/index";
	}
	
	
	@RequestMapping("Helpindex")
	public String Help(Locale locale, Model model) 
	{
		return "spare/index";
	}
	
	
	@RequestMapping("Marketingindex")
	public String Marketing(Locale locale, Model model) 
	{
		return "Smart Marketing/index";
	}
	
	
	@RequestMapping("Managemantindex")
	public String Managemant(Locale locale, Model model) 
	{
		return "Smart Management/index";
	}
	
	
	@RequestMapping("CRMindex")
	public String CRM(Locale locale, Model model) 
	{
		return "Smart AMC/index";
	}
	
	@RequestMapping("SpareHome")
	public String Spare(Locale locale, Model model) 
	{
		return "spare/SpareHome";
	}
	
	
	@RequestMapping("Transportindex")
	public String Transport(Locale locale, Model model) 
	{
		return "Smart Transport/index";
	}
	
	
	@RequestMapping("Serviceindex")
	public String Service(Locale locale, Model model) 
	{
		return "Smart Servicereport/index";
	}
	
	
	@RequestMapping("Debugindex")
	public String Debug(Locale locale, Model model) 
	{
		return "Smart Debug/index";
	}
	
	
	@RequestMapping("Fileindex")
	public String File(Locale locale, Model model) 
	{
		return "Smart UploadDownload/index";
	}
	
	
	@RequestMapping("Trainingindex")
	public String Training(Locale locale, Model model) 
	{
		return "Smart Training/index";
	}
	
	
	@RequestMapping("Utilityindex")
	public String Utility(Locale locale, Model model) 
	{
		return "Smart Utility/index";
	}
}
