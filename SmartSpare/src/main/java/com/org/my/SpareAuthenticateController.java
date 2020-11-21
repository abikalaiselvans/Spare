package com.org.my;


import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.my.architecture.Authenticator;

@Controller
public class SpareAuthenticateController
{

	private static final Logger logger = LoggerFactory.getLogger(SpareAuthenticateController.class);
	
	@RequestMapping(value="SpareAuthenticate", method = RequestMethod.POST)
	public String Handle(Locale locale, Model model,  @RequestParam Map<String,String> requestparam, HttpSession session) 
	{
		String username = requestparam.get("username");
		String password = requestparam.get("password");
		String filename = requestparam.get("filename");
		if (Authenticator.isAuthenticated(session)	|| filename.equalsIgnoreCase("Login")) 
			return Login.checkLogin(model,username, password, session);
		else
			return "login";
	}
	
	
	 
	
	@RequestMapping("/logout")
	public String logout(HttpSession session,HttpServletResponse   response,HttpServletRequest request) throws IOException  
	{
		Login.Logout(session, response, request);
		return "login";
	}

	
	@RequestMapping("/home")
	public String homePage(Model model) 
	{
		return "home";
	}
	
	
	@RequestMapping("/erlogin")
	public String loginPage(Model model) 
	{
		model.addAttribute("message","Kindly check your username & password");
		return "login";
	}
	
	@RequestMapping("/form")
	public String formPage(Model model) 
	{
		return "form";
	}
}
