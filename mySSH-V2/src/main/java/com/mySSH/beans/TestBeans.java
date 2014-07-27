package com.mySSH.beans;

import javax.servlet.http.HttpServletRequest;

public class TestBeans {
	public void sayHello(HttpServletRequest req)
	{
		System.out.println(req.getParameter("s"));
		//System.out.println(msg);
	}
}
