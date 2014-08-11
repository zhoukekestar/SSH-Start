package com.mySSH.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter(filterName="com_mySSH_filter_EncodingUTF8", urlPatterns="/*", asyncSupported=true)
public class EncodingUTF8 implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 在TOMCAT中的server.xml中的
		 * <Connector>节点中添加两个设置
		 * useBodyEncodingForURI="true" //设置POST和GET使用相同编码  需要在request.setCharacterEncoding("UTF-8") 中设置编码
		 * URIEncoding="UTF-8" 			//对URI使用utf-8编码处理 针对url编码的
		 * 
		 * 如下：
		 * <Connector 
		 * useBodyEncodingForURI="true" 
		 * URIEncoding="UTF-8" 
		 * connectionTimeout="20000" 
		 * maxThreads="150"
		 * port="8080" 
		 * protocol="HTTP/1.1" 
		 * redirectPort="8443"/>
		 * */	
		/*
		 * Change the tomcat's server.xml file.
		 * You should change the node marked as follows:
		 * <Connector>
		 * 
		 * Add the attribute to "<Connector> node":
		 * useBodyEncodingForURI="true" 
		 * URIEncoding="UTF-8" 			
		 * 
		 * 
		 * 
		 * All configuration like as follows:
		 * <Connector connectionTimeout="20000" useBodyEncodingForURI="true" URIEncoding="UTF-8"  port="8080" protocol="HTTP/1.1" redirectPort="8443"/>
		 * 
		 * */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("UTF-8 ok!");
		
		
		chain.doFilter(request, response);

		System.out.println("UTF-8 over!");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
