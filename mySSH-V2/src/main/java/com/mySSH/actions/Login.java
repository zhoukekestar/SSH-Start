package com.mySSH.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings({ "serial", "rawtypes" })
public class Login extends ActionSupport implements ModelDriven {
	
	com.mySSH.beans.Login login = new com.mySSH.beans.Login();

	@Override
	public String execute() throws Exception {
		if(login.getName().equals("res_1"))
			return "res_1";
		if(login.getName().equals("res_2"))
			return "res_2";
		return ERROR;
	}

	@Override
	public Object getModel() {
		return login;
	}

}
