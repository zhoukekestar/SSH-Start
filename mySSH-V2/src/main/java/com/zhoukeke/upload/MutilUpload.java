package com.zhoukeke.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

//5MB 
@MultipartConfig(maxFileSize=1024*1024*5)
@SuppressWarnings("serial")
@WebServlet(name = "com_toomao_web_upload_MutilUpload", urlPatterns = "/jqueryFileUpload")
public class MutilUpload extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(MutilUpload.class);
	private static String uploadPath = "upload/image/";
	private static String realPath = "";
	private static String webPath = "";
	
	@SuppressWarnings("deprecation")
	private void initPath(HttpServletRequest request)
	{
		try {
			Date date=new Date();
			DateFormat df=new SimpleDateFormat("yyyyMMdd");
			webPath = uploadPath + df.format(date) + "/";
			
			realPath = request.getRealPath(webPath) + "/";
			
			File fileP = new File(realPath);
			if (!fileP.exists())
				fileP.mkdirs();

		} catch (Exception e) {
			logger.error("File Path init Error.");
			e.printStackTrace();
		}
	}

	/**
	 * */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		initPath(request);

		if (request.getMethod().equalsIgnoreCase("post")) {
			doPost(request, response);
		} else if (request.getMethod().equalsIgnoreCase("get")) {
			doGet(request, response);
		} else if (request.getMethod().equalsIgnoreCase("options")) {
			doOptions(request, response);
		} else {
			logger.error("Method don't support:" + request.getMethod());
		}
	}
	
	
	protected void doOptions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "30");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "30");
		PrintWriter out = response.getWriter();
		try{
			
			String temp = "";
			
			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				if (isImage(part.getContentType()))
				{
					String filename = getFilename(part);
					
					if (filename.length() > 0)
					{
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						String tempName = uuid + filename.substring(filename.indexOf("."));
						
						part.write(realPath + tempName);
						logger.info("A new file saved to:	" + realPath + tempName);
						logger.info("You can visit it by:	" + webPath + tempName);
						tempName = "\"" + webPath + tempName + "\"";
						temp += temp.equals("")?(tempName):("," + tempName);
					}
				}
			}

			String resString = "{\"files\":["+temp+"]}";
			out.println(resString);

		}
		catch (Exception e)
		{
			out.println("{post error}");
		}
		finally{
			out.flush();
			out.close();
		}
		
	}
	
	private boolean isImage(String type)
	{
		if (null == type || type.length() <= 0)
			return false;
		String[] types = {"image/png","image/jpeg","image/jpeg"};
		for (int i = 0; i < types.length; i++)
		{
			if (type.equals(types[i]))
				return true;
		}
		return false;
	}
	
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
}