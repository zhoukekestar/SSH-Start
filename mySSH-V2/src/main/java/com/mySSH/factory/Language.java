package com.mySSH.factory;

import java.util.Locale;
import java.util.ResourceBundle;


public class Language {
	static factory_language_englishBeans eBeans = null;
	static factory_language_chineseBeans cBeans = null;

	factory_language_languageBeans beans = null;
	
	public Language() {
		if (eBeans == null && cBeans == null){
			eBeans = new factory_language_englishBeans();
			cBeans = new factory_language_chineseBeans();
		}
		beans = eBeans;
	}
	
	public Language(String lan) {
		if (eBeans == null && cBeans == null){
			eBeans = new factory_language_englishBeans();
			cBeans = new factory_language_chineseBeans();
		}
		
		switch (lan) {
		case "zh":
			beans = cBeans;
			break;

		default:
			beans = eBeans;
			break;
		}
	}
	
	public String getText(String key){
		return beans.getText(key);
	}
}

abstract class factory_language_languageBeans {
	ResourceBundle bundle;
	
	public String getText(String key){
		return bundle.getString(key);
	}
}

class factory_language_englishBeans extends factory_language_languageBeans {

	
	public factory_language_englishBeans() {
		Locale lan = new Locale("en","US");
		bundle = ResourceBundle.getBundle("config/i18n/i18n", lan);
	}

}

class factory_language_chineseBeans extends factory_language_languageBeans {


	public factory_language_chineseBeans(){
		Locale lan = new Locale("zh","CN");
		bundle = ResourceBundle.getBundle("config/i18n/i18n", lan);
	}

}
