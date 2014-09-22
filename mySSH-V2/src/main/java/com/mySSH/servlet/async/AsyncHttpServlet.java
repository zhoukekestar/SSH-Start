package com.mySSH.servlet.async;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class AsyncHttpServlet extends HttpServlet {
	
	ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AsyncContext ctxAsyncContext = request.startAsync();
		executorService.submit(new AsyncRequestBaseHander(ctxAsyncContext));
	}
		
	@Override
	public void destroy() {
		executorService.shutdown();
	}
	
	private class AsyncRequestBaseHander implements Runnable
	{
		private AsyncContext ctx;
		
		public AsyncRequestBaseHander(AsyncContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			asyncService((HttpServletRequest)ctx.getRequest(), (HttpServletResponse)ctx.getResponse());
		}
		
	}
	
	public abstract void asyncService(HttpServletRequest request, HttpServletResponse response);
	
}
