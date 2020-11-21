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

import com.org.my.spare.dao.ProblemGroupDAO;
import com.org.my.spare.model.ProblemGroup;

 
 
@Controller
public class ProblemGroupController 
{
	 
	 
	ProblemGroupDAO  problemgroupDao;
	int updateValue=0;

	/* To list the Equpment make */
	@RequestMapping("ProblemGroup")
	public String ProblemGroup(Model model)
	{
		result(model);
		return "spare/ProblemGroup";
	}
	
	
	 
	
	
	/* Action : Add*/
	/* To  Saving the new Record in the database */
	@RequestMapping("AddProblemGroup")
	public String saveRecord(@ModelAttribute ProblemGroup equ,HttpSession session,Model model) 
	{
		try
		{
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			problemgroupDao.saveRecord( name, description, userid);
			result(model);
			return "redirect:ProblemGroup";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
		 
	}

	
	
	/* edit the values */ 
	/* To forward the editing Page */
	@RequestMapping("editProblemGroup")
	@ResponseBody
	public List<ProblemGroup> edit(@RequestParam Map<String,String> req,Model model) 
	{
		List<ProblemGroup> equ = problemgroupDao.editRecord(new Integer(req.get("rowid")));
		System.out.println(""+equ.isEmpty());
		return equ;
	}

	
	
	/* Update the records in the Database */
	@RequestMapping("UpdateProblemGroup") 
	public String update(@ModelAttribute ProblemGroup equ, Model model,HttpSession session) 
	{
		try
		{
			int rowid = equ.getErowid();
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String description = equ.getDescription();
			problemgroupDao.updateRecord(rowid, name, description,userid);
			result(model);
			return "redirect:ProblemGroup";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	/* Delete the records from database */
	@RequestMapping("deleteProblemGroup")
	@ResponseBody
	public String deleteEquType(@RequestParam(value = "rowids",required = true)String rowids,Model model) 
	{
		try
		{
		
			String msg = "";
			int row = problemgroupDao.deleteRecord(rowids);
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
	@RequestMapping(value = "sortProblemGroup",method = RequestMethod.POST) 
	@ResponseBody
	public List<ProblemGroup> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		List<ProblemGroup> equ = problemgroupDao.sortData(request.get("startLetter"));
		System.out.println(equ.isEmpty());
		Collections.sort(equ,new Comparator<ProblemGroup>(){
			@Override
			public int compare(ProblemGroup s1, ProblemGroup s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		return equ;
	}

	
	
	public void result(Model model)
	{
		List<ProblemGroup> li = problemgroupDao.getItems();
		Collections.sort(li,new Comparator<ProblemGroup>(){
			@Override
			public int compare(ProblemGroup s1, ProblemGroup s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("result",li);
		
	}

 	
	 
	@Autowired
	public void setEquipmentService(ProblemGroupDAO problemgroupDao) {
		this.problemgroupDao = problemgroupDao;
	}

	 
	 
		

}
