package com.zhoukeke.util;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;


public class Json {
	public static <T> Object Json2Object(String str, Class<T> obj)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Object object = null;
		try {
			object = objectMapper.readValue(str, obj);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public static String Object2Json(Object obj)
	{
		StringWriter str = new StringWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(str, obj);
		} catch (Exception e) {
			
		}
		return str.toString();
	}
}
