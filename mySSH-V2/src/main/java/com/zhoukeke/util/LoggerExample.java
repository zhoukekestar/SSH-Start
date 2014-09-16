package com.zhoukeke.util;

import org.apache.log4j.Logger;


public class LoggerExample {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(LoggerExample.class);
		logger.info("hello world");
		logger.warn("hi");
		
	}
}
