package com.mySSH.servlet.async;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/test/async")
public class AsyncTest extends AsyncHttpServlet {

	@Override
	public void asyncService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().write("5s wati");
	}

	

}
