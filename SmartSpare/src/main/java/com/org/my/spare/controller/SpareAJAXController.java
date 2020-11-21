package com.org.my.spare.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.my.spare.dao.UniqueDAO;
import com.org.my.spare.model.Unique;


@Controller
public class SpareAJAXController 
{
	UniqueDAO uniqueDao;
	@RequestMapping("/autocompleteUnique")
	@ResponseBody
	public List<Unique> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		
		
		String startLetter = request.get("searchKey");
		String table = request.get("tableName");
		String field = request.get("columnName");
		System.out.println(startLetter+"/"+table+"/"+field);
		List<Unique> li = uniqueDao.sortData(startLetter,table,field);
		System.out.println(li.isEmpty());
		Collections.sort(li,new Comparator<Unique>(){
			@Override
			public int compare(Unique s1, Unique s2) {
				// TODO Auto-generated method stub
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
		//System.out.println(li);
		return li;
	}
	
	
	
	
	 
	@Autowired
	public void setuniqueService(UniqueDAO uniqueDao) {
		this.uniqueDao = uniqueDao;
	}

}
