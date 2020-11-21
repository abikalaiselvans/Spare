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

import com.org.my.exception.UserException;
import com.org.my.spare.dao.EquipementTypeDAO;
import com.org.my.spare.model.EquipementType;

 
 
@Controller
public class EquipementTypeController 
{
	 
	EquipementTypeDAO equTypeDao;
	int updateValue=0;

	/* To list the Equpment type */
	@RequestMapping("EquipmentType")
	public String equipmentType(Model model)
	{
		result(model);
		return "spare/EquipmentType";
	}
	
	
	
	
	
	/* Action : Add*/
	/* To  Saving the new Record in the database */
	@RequestMapping("AddEquipementType")
	public String saveRecord(@ModelAttribute EquipementType equ,HttpSession session,Model model) 
	{
		try
		{
			
			String userid = (String) session.getAttribute("USERID");
			String equTypeName = equ.getName();
			String equTypeDesc = equ.getDescription();
			int row = equTypeDao.saveRecord(equTypeName, equTypeDesc,userid);
			result(model);
			return "redirect:EquipmentType";
		}
		catch(Exception e)
		{
			//model.addAttribute("errmessage",e.getMessage());
			throw new UserException(e.getMessage()); 
			
		}
		 
	}

	
	
	/* edit the values */ 
	/* To forward the editing Page */
	@RequestMapping("equipmentTypeEdit")
	@ResponseBody
	public List<EquipementType> edit(@RequestParam Map<String,String> req,Model model) 
	{
		List<EquipementType> equ = equTypeDao.editRecord(new Integer(req.get("rowid")));
		System.out.println(""+equ.isEmpty());
		return equ;
	}

	
	
	/* Update the records in the Database */
	@RequestMapping("UpdateEquipmentType") 
	public String update(@ModelAttribute EquipementType equ, Model model) 
	{
		String equName,equDesc;
		equName = equ.getName();
		equDesc = equ.getDescription();
		updateValue = equ.getErowid();
		int row = equTypeDao.updateRecord(equName,equDesc,updateValue);
		result(model);
		return "redirect:EquipmentType";
	}

	
	/* Delete the records from database */
	@RequestMapping("DeleteEquipmentType")
	@ResponseBody
	public String deleteEquType(@RequestParam(value = "equTypeIds",required = true)String equTypeIds) 
	{
		String msg = "";
		int row = equTypeDao.deleteRecord(equTypeIds);
		System.out.println("row value in controller after delete"+row);
		if (row>=1) 
			msg = "success";
		return msg;
	}

	
	
	/* Sort the Equtype items based on the letter */
	@RequestMapping(value = "sortEquipmentType",method = RequestMethod.POST) 
	@ResponseBody
	public List<EquipementType> sortData(@RequestParam(value = "startLetter",required = true) String startLetter,Model model) 
	{
		List<EquipementType> equ = equTypeDao.sortData(startLetter);
		System.out.println(equ.isEmpty());
		Collections.sort(equ,new Comparator<EquipementType>(){
			@Override
			public int compare(EquipementType s1, EquipementType s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		return equ;
	}

	
	
	public void result(Model model)
	{
		List<EquipementType> equType = equTypeDao.getItems();
		Collections.sort(equType,new Comparator<EquipementType>(){
			@Override
			public int compare(EquipementType s1, EquipementType s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("result",equType);
		
	}

	 
	@Autowired
	public void setEquipmentService(EquipementTypeDAO equTypeDao) {
		this.equTypeDao = equTypeDao;
	}

	  
		

}
