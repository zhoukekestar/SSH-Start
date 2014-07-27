package com.mySSH.filter;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class MyLogger implements Interceptor {

	private static Logger logger = Logger.getLogger(MyLogger.class);

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		printMSG("Filter logger info:----------------------begin---");
		printMSG("Action:" + invocation.getAction().getClass().getName());
		printMSG("Method:" + invocation.getProxy().getMethod());
		Map<String, Object> params = invocation.getInvocationContext()
				.getParameters();

		printMSG("Param");
		for (String key : params.keySet()) {
			Object object = params.get(key);

			String msg = "";
			if (object instanceof String[]) {
				String[] arr = (String[]) object;
				msg += "\t" + key + ":";

				for (String value : arr) {
					msg += value;
				}
				printMSG(msg);
			}
		}

		String resultString = invocation.invoke();

		Result rresult = invocation.getResult();

		if (rresult instanceof ServletDispatcherResult) {
			ServletDispatcherResult result = (ServletDispatcherResult) rresult;
			printMSG("JSP:" + result.getLastFinalLocation());
		}

		printMSG("Filter logger info:----------------------over----");
		return resultString;
	}

	private void printMSG(String msg) {
		logger.info(msg);
	}

}
