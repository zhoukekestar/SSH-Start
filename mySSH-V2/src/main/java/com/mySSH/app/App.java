package com.mySSH.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class App {
public static void main(String[] args) {
	Log log = LogFactory.getLog("App");
	
	for (int i = 0; i < 600; i++)
	{
		log.info("hello ?zhkk" + i);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
