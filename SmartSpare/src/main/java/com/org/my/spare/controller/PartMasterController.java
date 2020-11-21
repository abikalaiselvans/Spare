package com.org.my.spare.controller;

 
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.my.spare.dao.PartMasterDAO;
import com.org.my.spare.model.PartMaster;

 
 
@Controller
public class PartMasterController 
{
	 
	 
	PartMasterDAO partmasterDao;
	int updateValue=0;

	/* To list the PartMaster */
	@RequestMapping("PartMaster")
	public String PartMaster(Model model)
	{
		result(model);
		return "spare/PartMaster";
	}
	
	
	
	
	
	/* Action : Add*/
	/* To  Saving the new Record in the database */
	@RequestMapping("AddPartMaster")
	public String saveRecord(@ModelAttribute PartMaster equ,HttpSession session,Model model) 
	{
		try
		{
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			String technicalgroupid = ""+equ.getTechnicalgroupid();
			String modelid = ""+equ.getModelid();
			String generalgroupid = ""+equ.getGeneralgroupid();
			String oemname = ""+equ.getOemname();
			partmasterDao.saveRecord( name,oemname, description,technicalgroupid,modelid,generalgroupid, userid);
			result(model);
			return "redirect:PartMaster";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
		 
	}

	
	
	/* edit the values */ 
	/* To forward the editing Page */
	@RequestMapping("editPartMaster")
	@ResponseBody
	public List<PartMaster> edit(@RequestParam Map<String,String> req,Model model) 
	{
		List<PartMaster> equ = partmasterDao.editRecord(new Integer(req.get("rowid")));
		System.out.println(""+equ.isEmpty());
		return equ;
	}

	
	
	/* Update the records in the Database */
	@RequestMapping("UpdatePartMaster") 
	public String update(@ModelAttribute PartMaster equ, Model model,HttpSession session) 
	{
		try
		{
			int rowid = equ.getErowid();
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			String technicalgroupid = ""+equ.getTechnicalgroupid();
			String modelid = ""+equ.getModelid();
			String generalgroupid = ""+equ.getGeneralgroupid();
			String oemname = ""+equ.getOemname();
			//System.out.println(rowid+"/"+ name+"/"+ description+"/"+userid);
			partmasterDao.updateRecord(rowid, name, oemname,description,technicalgroupid,modelid,generalgroupid, userid);
			result(model);
			return "redirect:PartMaster";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	/* Delete the records from database */
	@RequestMapping("deletePartMaster")
	@ResponseBody
	public String deleteEquType(@RequestParam(value = "rowids",required = true)String rowids,Model model) 
	{
		try
		{
		
			String msg = "";
			int row = partmasterDao.deleteRecord(rowids);
			if (row>=1) 
				msg = "success";
			return msg;
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	
	/* Sort the Equipment Make items based on the letter */
	@RequestMapping(value = "sortPartMaster",method = RequestMethod.POST) 
	@ResponseBody
	public List<PartMaster> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		String letter =request.get("startLetter"); 
		String modelid =request.get("modelid");
		String generalgroupid =request.get("generalgroupid");
		String technicalgroupid =request.get("technicalgroupid");
		List<PartMaster> li = partmasterDao.sortData(letter,modelid,generalgroupid,technicalgroupid);
		System.out.println(li.isEmpty());
		Collections.sort(li,new Comparator<PartMaster>()
				{
			@Override
			public int compare(PartMaster s1, PartMaster s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		return li;
	}

	
	
	/* Sort the Equipment Make items based on the letter */
	@RequestMapping(value = "loadpartCodeDescription",method = RequestMethod.POST) 
	@ResponseBody
	public List<PartMaster> loadpartCodeDescription(@RequestParam Map<String,String> request,Model model) 
	{
		String partcode =request.get("partcode"); 
		List<PartMaster> li = partmasterDao.partCodeDescription(partcode);
		System.out.println(li.isEmpty());
		return li;
	}

	
	
	 
	
	
	
	
	public void result(Model model)
	{
		List<PartMaster> equType = partmasterDao.getItems();
		Collections.sort(equType,new Comparator<PartMaster>(){
			@Override
			public int compare(PartMaster s1, PartMaster s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("result",equType);
		
	}

 	
	 
	@Autowired
	public void setPartMasterService(PartMasterDAO partmasterDao) {
		this.partmasterDao = partmasterDao;
	}

	 
	 
		

}
