package com.org.my.spare.controller;

 
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.my.spare.dao.ReturnDAO; 
import com.org.my.spare.model.Return;

  
@Controller
public class ReturnController 
{
	 
	 
	ReturnDAO  returndao;
	int updateValue=0;
	private static JdbcTemplate jdbc;
	private String sql = "";
 
	@Autowired
	public void setEquipmentService(ReturnDAO returndao) 
	{
		this.returndao = returndao;
	}
	
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	@RequestMapping("/Return")
	public String IssueSlip(Model model)
	{
		try
		{
			 
			result(model);
			return "spare/Return";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
		
	}
	
 
	   
	
	public void result(Model model)
	{
		
		List<Return> li = returndao.getItems();
		Collections.sort(li,new Comparator<Return>(){
			@Override
			public int compare(Return s1, Return s2) {
				// TODO Auto-generated method stub
				return s1.getIssuenumber().compareToIgnoreCase(s2.getIssuenumber());
			}
		});
		model.addAttribute("result",li);
	}

	
	
	
	@RequestMapping("/ReturnserialNumberreceived/{rowid}")
	public String ReturnserialNumberreceived(@PathVariable ("rowid") String row, Model model,HttpSession session)
	{
		try
		{
			String receivedid = (String) session.getAttribute("EMPID"); 
			returndao.ReturnSerialNumberReceived(row,receivedid); 
			result(model);
			return "redirect:/Return";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
		
	}
	
	@RequestMapping("/BulkReceived")
	public String BulkReceived(@RequestParam Map<String,String> req , Model model,HttpSession session)
	{
		try
		{
			String receivedid = (String) session.getAttribute("EMPID"); 
			String serialnumber = req.get("serialnumber");
			returndao.BulkReceived(serialnumber,receivedid); 
			result(model);
			return "redirect:/Return";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
		
	}
	
	
	@RequestMapping("/QualityStatus/{rowid}/{status}/{desc}")
	public String QualityStatus(@PathVariable Map<String,String> m, Model model,HttpSession session)
	{
		try
		{
			String empid = (String) session.getAttribute("EMPID");
			String row = m.get("rowid");
			String status = m.get("status");
			String desc = m.get("desc");
			returndao.QualityStatus(row,empid,status,desc); 
			result(model);
			return "redirect:/Return";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
		
	}
  
	@RequestMapping(value = "/sortReturn",method = RequestMethod.POST) 
	@ResponseBody
	public List<Return> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		List<Return> equ = returndao.sortData(request );
		System.out.println(equ.isEmpty());
		Collections.sort(equ,new Comparator<Return>(){
			@Override
			public int compare(Return s1, Return s2) {
				// TODO Auto-generated method stub
				return s1.getIssuenumber().compareToIgnoreCase(s2.getIssuenumber());
			}
		});
		return equ;
	}

	 
	
	 
	 
	
	
		

}
