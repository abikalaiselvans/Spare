package com.org.my.spare.controller;

 
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.my.spare.dao.DemandDAO;
import com.org.my.spare.model.Demand;

  
@Controller
public class DemandController 
{
	 
	 
	DemandDAO  demanddo;
	int updateValue=0;
	private Logger logger = Logger.getLogger(DemandController.class);
	
	@Autowired
	public void setEquipmentService(DemandDAO demanddo) {
		this.demanddo = demanddo;
	}
	
	
	
	//========================================================================================================
		 //List<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
	//Map<String,Object> m =jdbc.queryForMap(sql);
 	//SqlRowSet srs = jdbc.queryForRowSet(sql);
 	//List<Map<String, Object>> l = jdbc.queryForList(sql);
		 
 		 /*
		  * 
		  CALLABLE statment
		  		SqlOutParameter outParameter = new SqlOutParameter("empname", Types.VARCHAR);
				SqlParameter empid = new SqlParameter(Types.VARCHAR);
				List paramList = new ArrayList();
				paramList.add(empid);
				paramList.add(outParameter);
				
				final String procedureCall = "{call getEmpName(?, ?)}";
				Map<String, Object> resultMap = jdbc.call(new CallableStatementCreator() 
				{
					@Override
					public CallableStatement createCallableStatement(Connection connection)	throws SQLException 
					{
						CallableStatement callableStatement = connection.prepareCall(procedureCall);
						callableStatement.setString(1, "ADMIN");
						callableStatement.registerOutParameter(2, Types.VARCHAR);
						return callableStatement;
					}
				}, paramList);
				
				System.out.println(resultMap);
		  
		  
				//Batch UPDATE
				List<Map<String,Object>> l = new ArrayList();
				Map<String,Object>  m = new HashMap();
				for(int x=0;x<5;x++)
				{	
					m = new HashMap();
					m.put("name", "O1"+x);
					l.add(m);
				}
				insertBatch(l);
				
				public void insertBatch(final List<Map<String,Object>> office)
				{
					  String sql = "INSERT INTO com_m_office (CHR_OFFICENAME) VALUES (?)";
					  jdbc.batchUpdate(sql, new BatchPreparedStatementSetter() 
					  {
					 	@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							Map d = office.get(i);
							ps.setString(1, ""+d.get("name"));
							
						}
					 
						@Override
						public int getBatchSize() {
							return office.size();
						}
					  });
					}
		  
		  */
		 
		//========================================================================================================


	
	
	@RequestMapping("/Demand")
	public String Demand(Model model)
	{
		try
		{
			result(model);
			System.out.println("finished");
			return "spare/Demand";
			//return "redirect:DemandView";
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			model.addAttribute("errmessage",  e.getMessage());
			logger.error(e.getMessage());
			return "error/error";
		}
		
	}
	
	
	
	
	@RequestMapping("DemandView")
	public String DemandView(Model model)
	{
			System.out.println("going to view");
			return "spare/Demand";
	}
	
	
	
	
	
	
	
	
	@RequestMapping("DemandtoIssue")
	public String DemandtoIssue(Model model)
	{
		try
		{
			result(model);
			return "redirect:DemandtoIssuejsp";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			logger.error(e.getMessage());
			return "error/error";
		}
		
	}
	
 
	@RequestMapping("DemandtoIssuejsp")
	public String DemandtoIssuejsp(Model model)
	{
		result(model);
		return "spare/DemandIssue";
	}
	
	
	
	//ADD
	@RequestMapping("AddDemand")
	public String saveRecord(@ModelAttribute Demand d, HttpServletRequest r, BindingResult errors, HttpSession session,Model model) 
	{
		try
		{
			if (!errors.hasErrors()) 
			{
				String userid = (String) session.getAttribute("USERID");
				demanddo.saveRecord(r,d,userid);
			}
			else
			 
				System.out.println(errors.toString()); 
			result(model); 
			return "redirect:Demand"; 
		}
		catch(Exception e)
		{
			 
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";	 
		}
		 
	}
	
	
	
	/*CANCEL */
	@RequestMapping("DemandCancel/{rowid}")
	public String IssueSlipCancel(@PathVariable ("rowid") String row, Model model,HttpSession session)
	{
		try
		{
			demanddo.cancelRecord(row,""+session.getAttribute("USERID"));
			return "redirect:../Demand";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
			
	}
	
	//EDIT
	@RequestMapping("editDemand")
	@ResponseBody
	public List<Demand> edit(@RequestParam Map<String,String> req,Model model) 
	{ 
		List<Demand> d = demanddo.editRecord(new Integer(req.get("rowid")));
		return d;
	}

	
	//UPDATE
	@RequestMapping("UpdateDemand") 
	public String update(@ModelAttribute Demand d, HttpServletRequest r, BindingResult errors, HttpSession session,Model model) 
	{
		try
		{
			String userid = (String) session.getAttribute("USERID");
			demanddo.updateRecord(r,d,userid);
			result(model);
			return "redirect:Demand";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}

	
	
	/* Delete the records from database */
	@RequestMapping("deleteDemand")
	@ResponseBody
	public String deleteDemand(@RequestParam(value = "rowids",required = true)String rowids,Model model) 
	{
		try
		{
			String msg = "";
			int row = demanddo.deleteRecord(rowids);
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
	
	
	//DEMAND PRINT
	@RequestMapping("DemandPrint/{rowid}")
	public String DemandPrint(@PathVariable ("rowid") String row, Model model,HttpSession session)
	{
		try
		{
			model.addAttribute("demandrowid",row);
			return "redirect:../sparedemandPrint";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
			
	}
	
	@RequestMapping("sparedemandPrint")
	public String sparedemandPrint(Locale locale, Model model) 
	{
		return "spare/demandPrint";
	}
	
	
	
	
	
	
	//ISSUE
	@RequestMapping("DemandIssue/{rowid}")
	public String DemandIssue(@PathVariable ("rowid") String row, Model model,HttpSession session)
	{
		try
		{
			model.addAttribute("demandrowid",row);
			return "redirect:../spareIssue";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
			
	}
	
	@RequestMapping("spareIssue")
	public String spareIssue(Locale locale, Model model) 
	{
		return "spare/Issue";
	}
	 
	
	
	
	
	
	//DEMAND ISSUE UPDATE
	@RequestMapping("UpdateDemandIssue") 
	public String UpdateDemandIssue(@RequestParam Map<String,String> req ,  HttpSession session,Model model) 
	{
		try
		{
			int row = demanddo.updateDemandIssue(req,session);
			result(model);
			//return "redirect:Demand";
			return "spare/Demand";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",e.getMessage());
			return "error/error";	 
		}
	}
	
	
	
	
	@RequestMapping(value = "loadpartCodedropdown",method = RequestMethod.POST) 
	@ResponseBody
	public  List<Map<String,Object>> loadpartCodedropdown(@RequestParam Map<String,String> request,Model model) 
	{
		 List<Map<String,Object>> d = demanddo.loadpartCodedropdown(request );
		 return d;
	}
	
	
	@RequestMapping(value = "loadpartCodeDescriptions",method = RequestMethod.POST) 
	@ResponseBody
	public String loadpartCodeDescriptions(@RequestParam Map<String,String> request,Model model) 
	{
		String d = demanddo.loadpartCodeDescription(request );
		return d;
	}
	 
	@RequestMapping(value = "loadSerialnumberIssueslip" ) 
	@ResponseBody
	public List<Map<String,Object>> loadSerialnumberIssueslip(@RequestParam Map<String,String> request,Model model) 
	{
		List<Map<String,Object>>  d = demanddo.loadSerialnumberIssueslip(request );
		return d;
	}
	
	
	
	public void result(Model model)
	{
		List<Demand> li = demanddo.getItems();
		Collections.sort(li,new Comparator<Demand>(){
			@Override
			public int compare(Demand s1, Demand s2) {
				// TODO Auto-generated method stub
				return s1.getDemandno().compareToIgnoreCase(s2.getDemandno());
			}
		});
		model.addAttribute("result",li);
	}

  
	
	@RequestMapping(value = "sortloadDemand",method = RequestMethod.POST) 
	@ResponseBody
	public List<Demand> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		List<Demand> equ = demanddo.sortData(request );
		Collections.sort(equ,new Comparator<Demand>(){
			@Override
			public int compare(Demand s1, Demand s2) {
				return s1.getDemandno().compareToIgnoreCase(s2.getDemandno());
			}
		});
		System.out.println(""+equ);
		return equ;
	}

	 
 		

}
