package com.zhoukeke.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


//5MB 
@MultipartConfig(maxFileSize=1024*1024*5)
@SuppressWarnings("serial")
@WebServlet(name = "com_toomao_web_upload_MutilUpload", urlPatterns = "/jqueryFileUpload")
public class MutilUpload extends HttpServlet {
	
	private static boolean isInit = false;
	private static String uploadPath = "";
	
	@SuppressWarnings("deprecation")
	private void initPath(HttpServletRequest request)
	{
		try {
			
			uploadPath = getServletContext().getInitParameter("uploadPath");
			if (uploadPath.trim().equals(""))
				uploadPath = request.getRealPath("/upload");
			
			File fileP = new File(uploadPath);
			if (!fileP.exists())
				fileP.mkdirs();
			
			System.out.println("File will be saved to :" + uploadPath);
			isInit = true;
		} catch (Exception e) {
			System.err.println("File Path init Error.");
			e.printStackTrace();
		}
	}

	/**
	 * Post URL : /upload
	 * 
	 * Get URL : /upload?f=value
	 * */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if (!isInit)
			initPath(request);

		if (request.getMethod().equalsIgnoreCase("post")) {
			doPost(request, response);
		} else if (request.getMethod().equalsIgnoreCase("get")) {
			doGet(request, response);
		} else if (request.getMethod().equalsIgnoreCase("options")) {
			//System.out.println("ooptionss");
			doOptions(request, response);
		} else {
			System.out.println("Some Error Here.");
			System.out.println(request.getMethod());
		}
	}
	
	
	protected void doOptions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*This is for ajax accoss domain.*/
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
		/* This is for ajax accoss domain.*/
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "30");
		PrintWriter out = response.getWriter();
		try{
			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				if (isImage(part.getContentType()))
				{
					String filename = getFilename(part);
					
					if (filename.length() > 0)
					{
						String uuid = UUID.randomUUID().toString().replaceAll("-", "");
						part.write(uploadPath + "/" + uuid + filename);
					}
				}
			}
			
			
			//ObjectMapper mapper = new ObjectMapper();
			//StringWriter str = new StringWriter();
			//mapper.writeValue(str, temp);
			String resString = "{\"files\":[\"name1\",\"myname2\"]}";
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