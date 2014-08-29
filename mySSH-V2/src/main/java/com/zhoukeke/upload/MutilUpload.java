package com.zhoukeke.upload;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@SuppressWarnings("serial")
@WebServlet(name = "com_zhoukeke_upload_MutilUpload", urlPatterns = "/servlet/mutilupload")
public class MutilUpload extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String filePath = request.getRealPath("/upload");
		System.out.println(filePath);

		File fileP = new File(filePath);
		if (!fileP.exists())
			fileP.mkdirs();

		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			
			if (part.getContentType() != "image/png")
				continue;
			String upname = getFilename(part);
			if (null == upname || upname.length() <= 0)
				continue;
			File filename = new File(filePath + "/" + upname);
			OutputStream os = new FileOutputStream(filename);
			InputStream inputStream = part.getInputStream();

			byte buffer[] = new byte[4 * 1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1)
				os.write(buffer, 0, len);
			os.flush();
		}
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