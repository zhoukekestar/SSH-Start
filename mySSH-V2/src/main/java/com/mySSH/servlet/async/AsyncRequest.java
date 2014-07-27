package com.mySSH.servlet.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet(name="com_mySSH_servlet_async_AsyncRequest", urlPatterns="/servlet/async/AsyncRequest", asyncSupported=true)
public class AsyncRequest extends HttpServlet {
	
	ExecutorService executorService = 
		Executors.newFixedThreadPool(100);
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AsyncContext ctxAsyncContext = request.startAsync();
		executorService.submit(new MyAsyncRequest(ctxAsyncContext));
		
	}
	
	@Override
	public void destroy() {
		executorService.shutdown();
	}
}


class MyAsyncRequest implements Runnable{

	private AsyncContext ctx;
	
	public MyAsyncRequest(AsyncContext ctx)
	{
		this.ctx = ctx;
	}
	
	@Override
	public void run() {
		try {
			
			Thread.sleep(5000);
			PrintWriter out = ctx.getResponse().getWriter();
			out.println("久等。。。");
			ctx.complete();
		} catch (Exception e) {
			System.out.println("my asyncrequest error.");
		}
	}

}