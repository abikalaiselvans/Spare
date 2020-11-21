package com.org.my.architecture;

import javax.servlet.http.HttpSession;

public class Authenticator 
{
	public Authenticator()
	{
	}

    public static boolean isAuthenticated(HttpSession session)
    {
        Object object = session.getAttribute("USRID");
        return object != null;
    }
}
