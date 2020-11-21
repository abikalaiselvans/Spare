package com.org.my.spare.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.my.spare.dao.SpareDAO;
 

@Controller
public class SpareController 
{

	
	SpareDAO sparedao;
	 
	
	@Autowired
	public void setSpareDAO(SpareDAO sparedao) {
		this.sparedao = sparedao;
	}
	
	 
	
	@RequestMapping("SpareController")
	public String Spare(@RequestParam Map<String,String> request,Model model, HttpSession session)
	{
		sparedao.ControlAssign(request, model,session);
		return "spare/index";
	}
	
	@RequestMapping("/Spareindex")
	public String Spareindex()
	{
		 
		return "spare/index";
	}
	
	@RequestMapping("SpareReturnHome")
	public String SpareReturnHome(@RequestParam Map<String,String> request,Model model, HttpSession session)
	{
		session.removeAttribute("SPRCOMPANY");
		session.removeAttribute("SPRBRANCH");
		session.removeAttribute("SPROFFICEID");
		session.removeAttribute("COMPANYSHORTNAME");
		session.removeAttribute("SPRSTATE");
		return "home";
	}
	
	
	
	 
	@RequestMapping("loadBranchDetails")
	@ResponseBody
	public  List<Map<String,Object>> loadBranchDetails(@RequestParam Map<String,String> request,Model model) 
	{
		 List<Map<String,Object>> d = sparedao.loadBranchDetails(request );
		 return d;
	}
	
	
}
