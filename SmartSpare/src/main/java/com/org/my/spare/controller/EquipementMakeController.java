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

import com.org.my.spare.dao.EquipementMakeDAO;
import com.org.my.spare.model.EquipementMake;

 
 
@Controller
public class EquipementMakeController 
{
	 
	 
	EquipementMakeDAO  equipmentmakeDao;
	int updateValue=0;

	/* To list the Equpment make */
	@RequestMapping("EquipmentMake")
	public String EquipementMake(Model model)
	{
		result(model);
		return "spare/EquipmentMake";
	}
	
	
	 
	
	
	/* Action : Add*/
	/* To  Saving the new Record in the database */
	@RequestMapping("AddEquipementMake")
	public String saveRecord(@ModelAttribute EquipementMake equ,HttpSession session,Model model) 
	{
		try
		{
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			equipmentmakeDao.saveRecord( name, description, userid);
			result(model);
			return "redirect:EquipmentMake";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
		 
	}

	
	
	/* edit the values */ 
	/* To forward the editing Page */
	@RequestMapping("editEquipementMake")
	@ResponseBody
	public List<EquipementMake> edit(@RequestParam Map<String,String> req,Model model) 
	{
		List<EquipementMake> equ = equipmentmakeDao.editRecord(new Integer(req.get("rowid")));
		System.out.println(""+equ.isEmpty());
		return equ;
	}

	
	
	/* Update the records in the Database */
	@RequestMapping("UpdateEquipementMake") 
	public String update(@ModelAttribute EquipementMake equ, Model model,HttpSession session) 
	{
		try
		{
			int rowid = equ.getErowid();
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			//System.out.println(rowid+"/"+ name+"/"+ description+"/"+userid);
			equipmentmakeDao.updateRecord(rowid, name, description,userid);
			result(model);
			return "redirect:EquipmentMake";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	/* Delete the records from database */
	@RequestMapping("deleteEquipmentMake")
	@ResponseBody
	public String deleteEquType(@RequestParam(value = "rowids",required = true)String rowids,Model model) 
	{
		try
		{
		
			String msg = "";
			int row = equipmentmakeDao.deleteRecord(rowids);
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
	@RequestMapping(value = "sortEquipmentMake",method = RequestMethod.POST) 
	@ResponseBody
	public List<EquipementMake> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		List<EquipementMake> equ = equipmentmakeDao.sortData(request.get("startLetter"));
		System.out.println(equ.isEmpty());
		Collections.sort(equ,new Comparator<EquipementMake>(){
			@Override
			public int compare(EquipementMake s1, EquipementMake s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		return equ;
	}

	
	
	public void result(Model model)
	{
		List<EquipementMake> equType = equipmentmakeDao.getItems();
		Collections.sort(equType,new Comparator<EquipementMake>(){
			@Override
			public int compare(EquipementMake s1, EquipementMake s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("result",equType);
		
	}

 	
	 
	@Autowired
	public void setEquipmentService(EquipementMakeDAO equipmentmakeDao) {
		this.equipmentmakeDao = equipmentmakeDao;
	}

	 
	 
		

}
