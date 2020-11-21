package com.org.my.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlingController 
{

	@RequestMapping("/Error")
	public String equipmentType(Model model)
	{
		model.addAttribute("errmessage");
		return "error/error";
	}
}
