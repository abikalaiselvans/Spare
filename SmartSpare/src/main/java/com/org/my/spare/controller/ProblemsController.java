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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.my.spare.dao.ProblemGroupDAO;
import com.org.my.spare.dao.ProblemsDAO;
import com.org.my.spare.dao.ProblemsDAO;
import com.org.my.spare.model.ProblemGroup;
import com.org.my.spare.model.Problems;

 
 
@Controller
public class ProblemsController 
{
	 
	 
	ProblemGroupDAO  problemGroupDao;
	ProblemsDAO  ProblemsDao;
	 
	int updateValue=0;

	/* To list the Problems */
	@RequestMapping("Problems")
	public String Problems(Model model)
	{
		result(model);
		resultproblemGroup(model);
		return "spare/Problems";
	}
	
	
	 
	
	
	/* Action : Add*/
	/* To  Saving the new Record in the database */
	@RequestMapping("AddProblems")
	public String saveRecord(@ModelAttribute Problems equ,HttpSession session,Model model) 
	{
		try
		{
			String userid = (String) session.getAttribute("USERID");
			String name = equ.getName();
			String groupid = ""+equ.getGroupid();
			String description = equ.getDescription();
			ProblemsDao.saveRecord(groupid, name, description, userid);
			result(model);
			return "redirect:Problems";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
		 
	}

	
	
	/* edit the values */ 
	/* To forward the editing Page */
	@RequestMapping("editProblems")
	@ResponseBody
	public List<Problems> edit(@RequestParam Map<String,String> req,Model model) 
	{
		List<Problems> equ = ProblemsDao.editRecord(new Integer(req.get("rowid")));
		System.out.println(""+equ.isEmpty());
		return equ;
	}

	
	
	/* Update the records in the Database */
	@RequestMapping("UpdateProblems") 
	public String update(@ModelAttribute Problems equ, Model model,HttpSession session) 
	{
		try
		{
			int rowid = equ.getErowid();
			String userid = (String) session.getAttribute("USERID");
			String groupid = ""+equ.getGroupid();
			String name = equ.getName();
			String description = equ.getDescription();
			ProblemsDao.updateRecord(rowid,groupid, name, description,userid);
			result(model);
			return "redirect:Problems";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	/* Delete the records from database */
	@RequestMapping("deleteProblems")
	@ResponseBody
	public String deleteEquType(@RequestParam(value = "rowids",required = true)String rowids,Model model) 
	{
		try
		{
		
			String msg = "";
			int row = ProblemsDao.deleteRecord(rowids);
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
	@RequestMapping(value = "sortProblems",method = RequestMethod.POST) 
	@ResponseBody
	public List<Problems> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		String startwith = request.get("startLetter");
		String groupid = request.get("groupid");
		List<Problems> equ = ProblemsDao.sortData(startwith,groupid);
		System.out.println(equ.isEmpty());
		Collections.sort(equ,new Comparator<Problems>(){
			@Override
			public int compare(Problems s1, Problems s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		return equ;
	}

	
	
	
	@RequestMapping("ProblemsSort/{startLetter}/{groupid}")
	public String ReturnserialNumberreceived(@PathVariable Map<String,String> m, Model model,HttpSession session)
	{
		try
		{
			List<Problems> equ = ProblemsDao.sortData(m.get("startLetter"),m.get("groupid"));
			Collections.sort(equ,new Comparator<Problems>(){
				@Override
				public int compare(Problems s1, Problems s2) {
					// TODO Auto-generated method stub
					return s1.getName().compareToIgnoreCase(s2.getName());
				}
			});
			
			
			
			
			List<ProblemGroup> li = problemGroupDao.getItems();
			Collections.sort(li,new Comparator<ProblemGroup>(){
				@Override
				public int compare(ProblemGroup s1, ProblemGroup s2) {
					// TODO Auto-generated method stub
					return s1.getName().compareToIgnoreCase(s2.getName());
				}
			});
			
			model.addAttribute("result",equ); 
			model.addAttribute("ProblemGroup",li);
			 
			return "redirect:/ProblemsHome";
			
			 
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
		
	}
	
	@RequestMapping("ProblemsHome")
	public String ProblemsHome(Model model)
	{
		 
		return "spare/Problems";
	}
	
	
	
	public void result(Model model)
	{
		List<Problems> li = ProblemsDao.getItems();
		 
		Collections.sort(li,new Comparator<Problems>(){
			@Override
			public int compare(Problems s1, Problems s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("result",li);
		
	}

	public void resultproblemGroup(Model model)
	{
		List<ProblemGroup> li = problemGroupDao.getItems();
		Collections.sort(li,new Comparator<ProblemGroup>(){
			@Override
			public int compare(ProblemGroup s1, ProblemGroup s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("ProblemGroup",li);
		
	}
	 
	@Autowired
	public void setEquipmentService(ProblemsDAO ProblemsDao) {
		this.ProblemsDao = ProblemsDao;
	}

	@Autowired
	public void setProblemGroupService(ProblemGroupDAO problemGroupDao) {
		this.problemGroupDao = problemGroupDao;
	}

	 
	 
	 		

}
