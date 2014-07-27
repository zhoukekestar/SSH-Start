package com.mySSH.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name="com_mySSH_servlet_ShowRequest", urlPatterns="/servlet/ShowRequest")
public class ShowRequest extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String res = "<html><head><title>result</title></head><body><table  border=\"4\">";
		
		
		res += "<tr><td>Method</td><td>" + request.getMethod() + "</td></tr>";
		res += "<tr><td>HttpVersion</td><td>" + request.getProtocol() + "</td></tr>";
		res += "<tr><td>Scheme</td><td>" + request.getScheme() + "</td></tr>";
		res += "<tr><td>Cookies</td><td>";
		
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies)
			res += cookie.getName() + ":" + cookie.getValue() + ";";
		res +=  "</td></tr>";
		
		
		res += "<tr><td>URL</td><td>" + request.getRequestURI() + "</td></tr>";
		res += "<tr><td>ServletPath</td><td>" + request.getServletPath() + "</td></tr>";
		res += "<tr><td>ContextPath</td><td>" + request.getContextPath() + "</td></tr>";
		res += "<tr><td>ServerName</td><td>" + request.getServerName()+ "</td></tr>";
		res += "<tr><td>ServerPort</td><td>" + request.getServerPort() + "</td></tr>";
		
		res += "<tr><td>Encoding</td><td>" + request.getCharacterEncoding()+ "</td></tr>";

		
		
		res += "<tr><td>QueryString</td><td>" + request.getQueryString() + "</td></tr>";
		
		res += "<tr style=\"background-color: #f00;\"><td>.</td><td></td></tr>";
		res += "<tr><td>Head:</td><td></td></tr>";
		Enumeration<String> head = request.getHeaderNames();
		
		for (Enumeration<String> e = head; e.hasMoreElements();)
		{
			String headName = e.nextElement();
			res += "<tr><td>"+headName + "</td><td>" + request.getHeader(headName) + "</td></tr>";
		}
		res += "<table></body></html>";
		out.println(res);
		out.flush();
		out.close();
		
	}
}
