package com.mySSH.actions;



import com.mySSH.factory.Language;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class i18n extends ActionSupport {
	@Override
	public String execute() throws Exception {
		
		Language lan = new Language();
		System.out.println(lan.getText("hello"));
		
		Language lan2 = new Language("zh");
		System.out.println(lan2.getText("hello"));
		
		return SUCCESS;
	}
}
