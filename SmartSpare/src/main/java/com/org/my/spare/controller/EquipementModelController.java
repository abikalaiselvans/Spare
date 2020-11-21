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
import com.org.my.spare.dao.EquipementModelDAO;
import com.org.my.spare.dao.EquipementTypeDAO;
import com.org.my.spare.model.EquipementMake;
import com.org.my.spare.model.EquipementModel;
import com.org.my.spare.model.EquipementType;

 
 
@Controller
public class EquipementModelController 
{
	 
	EquipementModelDAO equipmentModelDao;
	EquipementTypeDAO  equipmentTypeDao;
	EquipementMakeDAO  equipmentMakeDao;
	int updateValue=0;

	/* To list the Equpment type */
	@RequestMapping("EquipmentModel")
	public String EquipementModel(Model model)
	{
		result(model);
		equipmentTypeResult(model);
		equipmentMakeResult(model);
		return "spare/EquipmentModel";
	}
	
	
	 
	
	
	/* Action : Add*/
	/* To  Saving the new Record in the database */
	@RequestMapping("AddEquipementModel")
	public String saveRecord(@ModelAttribute EquipementModel equ,HttpSession session,Model model) 
	{
		try
		{
			String userid = (String) session.getAttribute("USERID");
			String typeid = ""+equ.getEquipementtypeid();
			String makeid = ""+equ.getEquipementmakeid();
			String name = equ.getName();
			String description = equ.getDescription();
			equipmentModelDao.saveRecord(typeid, makeid,name, description, userid);
			result(model);
			return "redirect:EquipmentModel";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
		 
	}

	
	
	/* edit the values */ 
	/* To forward the editing Page */
	@RequestMapping("editEquipementModel")
	@ResponseBody
	public List<EquipementModel> edit(@RequestParam Map<String,String> req,Model model) 
	{
		List<EquipementModel> equ = equipmentModelDao.editRecord(new Integer(req.get("rowid")));
		System.out.println(""+equ.isEmpty());
		return equ;
	}

	
	
	/* Update the records in the Database */
	@RequestMapping("UpdateEquipementModel") 
	public String update(@ModelAttribute EquipementModel equ, Model model,HttpSession session) 
	{
		try
		{
			int rowid = equ.getErowid();
			String userid = (String) session.getAttribute("USERID");
			int typeid = equ.getEquipementtypeid();
			int makeid = equ.getEquipementmakeid() ;
			String name = equ.getName();
			String description = equ.getDescription();
			System.out.println(rowid+name);
			equipmentModelDao.updateRecord(rowid, name, description,userid, typeid,makeid);
			result(model);
			return "redirect:EquipmentModel";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	/* Delete the records from database */
	@RequestMapping("deleteEquipmentModel")
	@ResponseBody
	public String deleteEquType(@RequestParam(value = "rowids",required = true)String rowids, Model model) 
	{
		try
		{
			String msg = "";
			int row = equipmentModelDao.deleteRecord(rowids);
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

	
	
	/* Sort the Equtype items based on the letter */
	@RequestMapping(value = "sortEquipmentModel",method = RequestMethod.POST) 
	@ResponseBody
	public List<EquipementModel> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		String typeid=request.get("typeid");
		String makeid=request.get("equipementmakeid");
		String startwith = request.get("startLetter");
		List<EquipementModel> equ = equipmentModelDao.sortData(startwith,typeid,makeid);
		System.out.println(equ.isEmpty());
		Collections.sort(equ,new Comparator<EquipementModel>(){
			@Override
			public int compare(EquipementModel s1, EquipementModel s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		return equ;
	}

	
	
	public void result(Model model)
	{
		List<EquipementModel> equType = equipmentModelDao.getItems();
		Collections.sort(equType,new Comparator<EquipementModel>(){
			@Override
			public int compare(EquipementModel s1, EquipementModel s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("result",equType);
		
	}

	
	public void equipmentTypeResult(Model model)
	{
		List<EquipementType> equType = equipmentTypeDao.getItems();
		Collections.sort(equType,new Comparator<EquipementType>(){
			@Override
			public int compare(EquipementType s1, EquipementType s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("equipmentType",equType);
		
	}
	
	public void equipmentMakeResult(Model model)
	{
		List<EquipementMake> listMake = equipmentMakeDao.getItems();
		Collections.sort(listMake,new Comparator<EquipementMake>(){
			@Override
			public int compare(EquipementMake s1, EquipementMake s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		model.addAttribute("equipmentMake",listMake);
		
	}
	
	
	
	 
	@Autowired
	public void setEquipmentService(EquipementModelDAO equipmentModelDao) {
		this.equipmentModelDao = equipmentModelDao;
	}

	@Autowired
	public void setEquipmentType(EquipementTypeDAO equipmentTypeDao) {
		this.equipmentTypeDao = equipmentTypeDao;
	}
	
	@Autowired
	public void setEquipmentMake(EquipementMakeDAO equipmentMakeDao) {
		this.equipmentMakeDao = equipmentMakeDao;
	}
  
	
		

}
