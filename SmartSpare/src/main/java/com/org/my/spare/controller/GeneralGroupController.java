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

import com.org.my.spare.dao.GeneralGroupDAO;
import com.org.my.spare.dao.GeneralGroupDAO;
import com.org.my.spare.model.GeneralGroup;

 
 
@Controller
public class GeneralGroupController 
{
	 
	 
	GeneralGroupDAO  generalgroupDao;
	int updateValue=0;

	/* To list the GeneralGroup */
	@RequestMapping("GeneralGroup")
	public String GeneralGroup(Model model)
	{
		result(model);
		return "spare/GeneralGroup";
	}
	
	
	 
	
	
	/* Action : Add*/
	/* To  Saving the new Record in the database */
	@RequestMapping("AddGeneralGroup")
	public String saveRecord(@ModelAttribute GeneralGroup equ,HttpSession session,Model model) 
	{
		try
		{
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			generalgroupDao.saveRecord( name, description, userid);
			result(model);
			return "redirect:GeneralGroup";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
		 
	}

	
	
	/* edit the values */ 
	/* To forward the editing Page */
	@RequestMapping("editGeneralGroup")
	@ResponseBody
	public List<GeneralGroup> edit(@RequestParam Map<String,String> req,Model model) 
	{
		List<GeneralGroup> equ = generalgroupDao.editRecord(new Integer(req.get("rowid")));
		System.out.println(""+equ.isEmpty());
		return equ;
	}

	
	
	/* Update the records in the Database */
	@RequestMapping("UpdateGeneralGroup") 
	public String update(@ModelAttribute GeneralGroup equ, Model model,HttpSession session) 
	{
		try
		{
			int rowid = equ.getErowid();
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			//System.out.println(rowid+"/"+ name+"/"+ description+"/"+userid);
			generalgroupDao.updateRecord(rowid, name, description,userid);
			result(model);
			return "redirect:GeneralGroup";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	/* Delete the records from database */
	@RequestMapping("deleteGeneralGroup")
	@ResponseBody
	public String deleteEquType(@RequestParam(value = "rowids",required = true)String rowids,Model model) 
	{
		try
		{
		
			String msg = "";
			int row = generalgroupDao.deleteRecord(rowids);
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
	@RequestMapping(value = "sortGeneralGroup",method = RequestMethod.POST) 
	@ResponseBody
	public List<GeneralGroup> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		List<GeneralGroup> li = generalgroupDao.sortData(request.get("startLetter"));
		System.out.println(li.isEmpty());
		Collections.sort(li,new Comparator<GeneralGroup>(){
			@Override
			public int compare(GeneralGroup s1, GeneralGroup s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		return li;
	}

	
	
	public void result(Model model)
	{
		List<GeneralGroup> equType = generalgroupDao.getItems();
		Collections.sort(equType,new Comparator<GeneralGroup>(){
			@Override
			public int compare(GeneralGroup s1, GeneralGroup s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("result",equType);
		
	}

 	
	 
	@Autowired
	public void setEquipmentService(GeneralGroupDAO generalgroupDao) {
		this.generalgroupDao = generalgroupDao;
	}

	 
	 
		

}
