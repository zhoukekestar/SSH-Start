package com.toomao.wx.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toomao.wx.model.Data;


@SuppressWarnings("serial")
@WebServlet(name="com_toomao_wx_servlet_Login", urlPatterns="/wx_login")
public class Login extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=" + Data.appID
				+ "&redirect_uri=" + URLEncoder.encode(Data.LoginURL)
				+ "&response_type=" + "code"
				//+ "&scope=" + "snsapi_base"
				+ "&scope=" + "snsapi_userinfo"
				+ "&state=" + "zhoukekestar123"
				+ "#wechat_redirect";
		
		response.sendRedirect(url);
	}
}
