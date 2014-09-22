package com.zhoukeke.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil {

	public static String getProperty(String key, String fileURL) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(fileURL);
			prop.load(in);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void writeProperty(String key, String value, String fileURL)
	{
		Properties prop = new Properties();
		InputStream fis = null;
		OutputStream fos = null;
		try {
			File file = new File(fileURL);
			if (!file.exists()) {
				file.createNewFile();
			}
			fis = new FileInputStream(file);
			prop.load(fis);
			fis.close();
			fos = new FileOutputStream(file);
			prop.setProperty(key, value);
			prop.store(fos, "Update '" + key + "' value");
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
    public static Map<String, String> getAllProperties(String filePath)
    {
    	Map<String, String> map = new HashMap<String, String>();
        Properties pps = new Properties();
        InputStream in = null;
        try {
	        in = new FileInputStream(filePath);
	        pps.load(in);
	        Enumeration<?> en = pps.propertyNames(); 
	        
	        while(en.hasMoreElements()) {
	            String strKey = (String) en.nextElement();
	            String strValue = pps.getProperty(strKey);
	            map.put(strKey, strValue);
	        }
        } catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        finally{
        	try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }
        return map;
    }

	public static void main(String[] args) throws Exception {
		//System.out.println(ConfigUtil.getProperty("zkk", "F:/eclipse/session/src/App/test.properties"));
		//ConfigUtil.writeProperty("zkk","zkkzkk", "F:/eclipse/session/src/App/zkk.properties");
		//ConfigUtil.writeProperty("zkk2","zkkzkk", "F:/eclipse/session/src/App/test.properties");
		
		Map<String, String> map = ConfigUtil.getAllProperties("F:/eclipse/session/src/App/test.properties");
	}

}
