package com.org.my;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	 
	@RequestMapping("dropdowntest")
	public String homes(Locale locale, Model model) 
	{
	
		return "Test/dropdown";
	}
	
	
	@RequestMapping("Test1")
	public String Test1(Locale locale, Model model ,@RequestParam Map<String,String> req) 
	{
		String id =req.get("id");
		
		if("1".equals(id))
			return "Test/1";
		else if("2".equals(id))
			return "Test/2";
		else if("3".equals(id))
			return "Test/set";
		else if("4".equals(id))
			return "Test/display";
		else if("5".equals(id))
			return "Test/remove";
		else if("6".equals(id))
			return "Test/removedisplay";
		else if("7".equals(id))
			return "Test/if";
		else if("8".equals(id))
			return "Test/choose";
		else if("9".equals(id))
			return "Test/catch";
		else if("10".equals(id))
			return "Test/import";
		else
			return "Test/1";
	}
	
	
}
