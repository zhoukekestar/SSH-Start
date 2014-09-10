package com.toomao.wx.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toomao.wx.model.Data;
import com.toomao.wx.utils.SHA;


@SuppressWarnings("serial")
@WebServlet(name="com_toomao_wx_servlet_Token", urlPatterns="/wx")
public class Token extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String[]> map = request.getParameterMap();
		/*for (Entry<String, String[]> en : map.entrySet()) {
			System.out.println(en.getKey() + " = " + en.getValue()[0]);
		}*/
		
		String signature 	= map.get("signature")[0];
		String timestamp 	= map.get("timestamp")[0];
		String nonce 		= map.get("nonce")[0];
		String echostr		= map.get("echostr")[0];
		
		String sha = "";
		String[] ss = new String[] { Data.Token, timestamp, nonce };
		Arrays.sort(ss);
		for (String s : ss) {
			sha += s;
		}
		
		try {
			sha = SHA.encodeBySHA(sha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("#################");
		System.out.println(signature 	+ "		->sign");
		System.out.println(sha 			+ "		->sha");
		System.out.println("#################");

		if (sha.equalsIgnoreCase(signature)) {
			response.getWriter().write(echostr);
		}
		else {
			System.out.println("Error:" + signature + " != " + sha);
		}
	}
}
