package com.org.my.spare.controller;

 
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.my.spare.dao.IssueSlipDAO;
import com.org.my.spare.model.IssueSlip;

  
@Controller
public class IssueSlipController 
{
	 
	 
	IssueSlipDAO  issueslipdao;
	int updateValue=0;
	private static JdbcTemplate jdbc;
	private String sql = "";
 
	@Autowired
	public void setEquipmentService(IssueSlipDAO issueslipdao) {
		this.issueslipdao = issueslipdao;
	}
	
	
	
	/* To Automatically inject the DataSource into JdbcTemplate class */
	@Autowired
	public void setDatasource(DataSource datasource) 
	{
		  this.jdbc=new JdbcTemplate(datasource); 
	}
	
	@RequestMapping("IssueSlip")
	public String IssueSlip(Model model)
	{
		try
		{
			 
			result(model);
			return "spare/IssueSlip";
		}
		catch(Exception e)
		{
			model.addAttribute("errmessage",  e.getMessage());
			return "error/error";
		}
		
	}
	
 
	
		//ISSUE SLIP RETURN
		@RequestMapping("IssueSlipReturn/{rowid}")
		public String DemandIssue(@PathVariable ("rowid") String row, Model model,HttpSession session)
		{
			try
			{
				model.addAttribute("issuesliprowid",row);
				return "redirect:../spare_IssueSlipReturn";
				 
			}
			catch(Exception e)
			{
				model.addAttribute("errmessage",  e.getMessage());
				return "error/error";
			}
				
		}
		
		@RequestMapping("spare_IssueSlipReturn")
		public String spareIssue(Locale locale, Model model) 
		{
			return "spare/IssueSlipReturn";
		}
	 
		
		//ISSUESLIP PRINT
		@RequestMapping("isueslipnoPrint")
		public String isueslipnoPrint(HttpServletRequest r, Model model,HttpSession session)
		{
			try
			{
				
				String row = r.getParameter("id");
				String sql = "SELECT CHR_DEMANDNO FROM spare_t_demanditem  WHERE CHR_ISSUENO ='"+row+"' GROUP BY CHR_ISSUENO";
				String demandno =jdbc.queryForObject(sql, String.class);
				sql = "SELECT DATE_FORMAT(DAT_ISSUEDDATE,'%d-%b-%Y %H:%i:%s %p') FROM spare_t_demanditem  WHERE CHR_ISSUENO ='"+row+"' GROUP BY CHR_ISSUENO";
				String issuetime =jdbc.queryForObject(sql, String.class);
				
				model.addAttribute("demandrefno",demandno);
				model.addAttribute("issueno",row);
				model.addAttribute("issuetime",issuetime);
				return "spare/IssueSlipPrint";
				 
			}
			catch(Exception e)
			{
				model.addAttribute("errmessage",  e.getMessage());
				return "error/error";
			}
				
		}
	
		
		@RequestMapping("IssueHome")
		public String Demand(Model model)
		{
			try
			{
				 
				result(model);
				return "spare/IssueSlip";
			}
			catch(Exception e)
			{
				model.addAttribute("errmessage",  e.getMessage());
				return "error/error";
			}
			
		}
	 
		
		@RequestMapping("IssueSlipPrint")
		public String IssueSlipPrint(Locale locale, Model model) 
		{
			 
			return "spare/IssueSlipPrint";
		}
		
	 
	
		
		//ISSUE SLIP RETURN UPDATE
		@RequestMapping("IssueSlipeReturnUpdate")
		public String IssueSlipeReturnUpdate(HttpServletRequest r, Model model,HttpSession session)
		{
			try
			{
				
				String issuenumber = r.getParameter("issuenumber");
				String userid = (String) session.getAttribute("USERID");
				
				int rowcount = Integer.parseInt(r.getParameter("itemrowcount"));
				for(int z=0;z<rowcount;z++)
				{
					if("Y".equals( r.getParameter("return"+(z+1)) ))
					{
						sql ="";
						sql = " UPDATE spare_t_demanditem SET INT_RETURN_PARTMASTERID=?,CHR_RETURN_SERIALNUMBER=?, ";
						sql =sql+ " CHR_RETURNSTATUS='Y',INT_RETURNSTATUSTYPE=?,DAT_RETURN_DATE=DATE(NOW()) ,CHR_RETUNED_BY=? WHERE INT_ITEMROWID=? ";
						Object[] params = new Object[] {
								r.getParameter("partcode"+(z+1)),r.getParameter("iserialnumber"+(z+1)),
								r.getParameter("returntatus"+(z+1)),
								session.getAttribute("EMPID"), r.getParameter("itemrowid"+(z+1))};
						int row = jdbc.update(sql, params);
						System.out.println("UPDATE ISSUE SLIP RETURN NUMBER");
						
						int iqty = Integer.parseInt(r.getParameter("iqty"+(z+1)));
						for(int x=0;x<iqty;x++)
						{
							String returnnumber =jdbc.queryForObject("SELECT FUN_SPR_RETURN_NUMBER()", String.class);
							sql = " INSERT INTO spare_t_issueslipreturn   (CHR_RETURNNO,CHR_ISSUENO,CHR_ISSUESERIALNUMBER,CHR_SERIALNUMBER,CHR_RETURNDATE, ";
							sql = sql + " CHR_USRNAME,DT_UPDATEDATE,CHR_UPDATESTATUS) "; 
							sql = sql + " VALUES(?,?,?,?,NOW(),?,DATE(NOW()),'Y') ";
							params = new Object[] { returnnumber,issuenumber, r.getParameter("iserialnumber"+(z+1)), (x==0 ? r.getParameter("iserialnumber1") :"null"),userid};
							row = jdbc.update(sql, params);
							System.out.println("INSERT RETURN SERIAL NUMBER");
						}
					}
				}
				
				
				
				
				return "redirect:IssueSlip";
				 
			}
			catch(Exception e)
			{
				model.addAttribute("errmessage",  e.getMessage());
				return "error/error";
			}
				
		}

	
	public void result(Model model)
	{
		
		List<IssueSlip> li = issueslipdao.getItems();
		Collections.sort(li,new Comparator<IssueSlip>(){
			@Override
			public int compare(IssueSlip s1, IssueSlip s2) {
				// TODO Auto-generated method stub
				return s1.getCustomer().compareToIgnoreCase(s2.getCustomer());
			}
		});
		model.addAttribute("result",li);
	}

	
  
	@RequestMapping(value = "sortIssueSlip",method = RequestMethod.POST) 
	@ResponseBody
	public List<IssueSlip> sortData(@RequestParam Map<String,String> request,Model model) 
	{
		List<IssueSlip> equ = issueslipdao.sortData(request );
		System.out.println(equ.isEmpty());
		Collections.sort(equ,new Comparator<IssueSlip>(){
			@Override
			public int compare(IssueSlip s1, IssueSlip s2) {
				return s1.getCustomer().compareToIgnoreCase(s2.getCustomer());
			}
		});
		return equ;
	}

	 
	
	 
	 
	
	
		

}
