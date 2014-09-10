package com.toomao.wx.model;

public class Data {
	//public
	public static String Token = "zhoukekestar123";
	public static String URL = "http://zhoukekestar.oicp.net/WX/wx";
	public static String LoginURL = "http://zhoukekestar.oicp.net/WX//wx_login_after";
	public static String appID = "wx7d7b73a6b545f4d4";
	public static String appsecret = "e3ececf744f04f0eea10a15939388e21";
	public static String access_token = "";
	public static String menu = "";
	
	public static String getAccess_token() {
		return AccossToken.get();
	}
	public static void setAccess_token(String access_token) {
		Data.access_token = access_token;
	}
	
	
	public static String getMenu() {
		return Data.menu;
	}
	public static void setMenu(String menu) {
		Data.menu = menu;
		Menu.set();
	}
	
	
}
