package com.toomao.wx.model;

import java.util.Date;

import com.toomao.wx.utils.HttpRequest;
import com.toomao.wx.utils.Json;

public class AccossToken {
	public String access_token = "";
	public int expires_in = 0;
	
	public static Date access_token_expire = null;
	
	@SuppressWarnings("deprecation")
	public static String get()
	{
		int debug = 1;
		if (debug != 0)
		{
			Data.access_token = "AwK9bXk8Uc-s_pJ8Vjj3vWuEOzxL9lTQXlaOmKyqbqnSIJrllo9ltv2TEhwQAElsRaIlnUNtheouHtNcjO0C1Q";
			access_token_expire = new Date();
			access_token_expire.setHours(access_token_expire.getHours() + 2);
		}
		
		
		//判断是否存在 或者 是否过期
		// 如果条件成立的，则需要重新获取access_token
		if (isPast() || Data.access_token.equals(""))
		{
			String response =
					HttpRequest.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7d7b73a6b545f4d4&secret=e3ececf744f04f0eea10a15939388e21").body();

			
			//当调用接口出现错误的时候
			if (response.indexOf("errcode") != -1)
			{
				System.err.println(" Error:" + response);
				return "";
			}
			AccossToken temp = new AccossToken();
			temp = (AccossToken)Json.Json2Object(response, AccossToken.class);
			Data.access_token = temp.access_token;
			access_token_expire = new Date();
			access_token_expire.setSeconds(access_token_expire.getSeconds() + temp.expires_in);
			System.out.println(" Data's output: access_token has refresh to -->" + Data.access_token);
		}
		return Data.access_token;
	}
	
	private static boolean isPast()
	{
		if (access_token_expire == null)
			return true;
		return new Date().after(access_token_expire);
	}
}
