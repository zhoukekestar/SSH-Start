package com.util.jackson;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class Json2Obj {
	public static void main(String[] args) {
		String str = "{\"student\":[{\"name\":\"leilei\",\"age\":23},{\"name\":\"leilei02\",\"age\":23}]}";

		List<Student> list = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			StudentList studentList = objectMapper.readValue(str,
					StudentList.class);

			list = studentList.getStudent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Student s : list) {
			System.out.println(s.getName() + "   " + s.getAge());
		}
	}
}
