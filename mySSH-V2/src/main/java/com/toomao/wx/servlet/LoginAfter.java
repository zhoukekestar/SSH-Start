package com.toomao.wx.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.toomao.wx.model.Data;
import com.toomao.wx.model.UserAccessToken;
import com.toomao.wx.model.UserInfo;
import com.toomao.wx.utils.HttpRequest;
import com.toomao.wx.utils.Json;


@SuppressWarnings("serial")
@WebServlet(name="com_toomao_wx_servlet_LoginAfter", urlPatterns="/wx_login_after")
public class LoginAfter extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=" + Data.appID
				+ "&secret="+Data.appsecret
				+ "&code="+request.getParameter("code")
				+ "&grant_type=authorization_code";
		
		String result = HttpRequest.get(url).body();
		UserAccessToken userAccessToken = (UserAccessToken)Json.Json2Object(result, UserAccessToken.class);
		
		url = "https://api.weixin.qq.com/sns/userinfo?"
		+"access_token="+ userAccessToken.access_token
		+"&openid="+ userAccessToken.openid
		+"&lang=zh_CN";
		
		result = HttpRequest.get(url).body();
		
		UserInfo userInfo = (UserInfo)Json.Json2Object(result, UserInfo.class);
		
		request.setAttribute("userinfo", userInfo);
		request.getRequestDispatcher("afterLogin.jsp").forward(request, response);
	}
}
