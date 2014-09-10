package com.toomao.wx.model;

import java.io.OutputStream;

import com.toomao.wx.utils.HttpRequest;

public class Menu {
	
	
	
	public static String set()
	{
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + Data.access_token;

		String res = HttpRequest.post(url).send(Data.menu).body();
		System.out.println(res);
		return res;
	}
}
