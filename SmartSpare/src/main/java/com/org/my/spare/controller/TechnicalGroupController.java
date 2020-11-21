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

import com.org.my.spare.dao.TechnicalGroupDAO;
import com.org.my.spare.model.TechnicalGroup;

 
 
@Controller
public class TechnicalGroupController 
{
	 
	 
	TechnicalGroupDAO  technicalGroupDao;
	int updateValue=0;

	/* To list the Equpment make */
	@RequestMapping("TechnicalGroup")
	public String TechnicalGroup(Model model)
	{
		
		result(model);
		return "spare/TechnicalGroup";
	}
	
	
	 
	
	
	/* Action : Add*/
	/* To  Saving the new Record in the database */
	@RequestMapping("AddTechnicalGroup")
	public String saveRecord(@ModelAttribute TechnicalGroup equ,HttpSession session,Model model) 
	{
		try
		{
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			technicalGroupDao.saveRecord( name, description, userid);
			result(model);
			return "redirect:TechnicalGroup";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
		 
	}

	
	
	/* edit the values */ 
	/* To forward the editing Page */
	@RequestMapping("editTechnicalGroup")
	@ResponseBody
	public List<TechnicalGroup> edit(@RequestParam Map<String,String> req,Model model) 
	{
		List<TechnicalGroup> equ = technicalGroupDao.editRecord(new Integer(req.get("rowid")));
		System.out.println(""+equ.isEmpty());
		return equ;
	}

	
	
	/* Update the records in the Database */
	@RequestMapping("UpdateTechnicalGroup") 
	public String update(@ModelAttribute TechnicalGroup equ, Model model,HttpSession session) 
	{
		try
		{
			int rowid = equ.getErowid();
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			technicalGroupDao.updateRecord(rowid, name, description,userid);
			result(model);
			return "redirect:TechnicalGroup";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	/* Delete the records from database */
	@RequestMapping("deleteTechnicalGroup")
	@ResponseBody
	public String deleteEquType(@RequestParam(value = "rowids",required = true)String rowids,Model model) 
	{
		try
		{
		
			String msg = "";
			int row = technicalGroupDao.deleteRecord(rowids);
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
	@RequestMapping(value = "sortTechnicalGroup",method = RequestMethod.POST) 
	@ResponseBody
	public List<TechnicalGroup> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		List<TechnicalGroup> equ = technicalGroupDao.sortData(request.get("startLetter"));
		System.out.println(equ.isEmpty());
		Collections.sort(equ,new Comparator<TechnicalGroup>(){
			@Override
			public int compare(TechnicalGroup s1, TechnicalGroup s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		return equ;
	}

	
	
	public void result(Model model)
	{
		List<TechnicalGroup> li = technicalGroupDao.getItems();
		Collections.sort(li,new Comparator<TechnicalGroup>(){
			@Override
			public int compare(TechnicalGroup s1, TechnicalGroup s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("result",li);
		
	}

 	
	 
	@Autowired
	public void setEquipmentService(TechnicalGroupDAO technicalGroupDao) {
		this.technicalGroupDao = technicalGroupDao;
	}

	 
	 
		

}
