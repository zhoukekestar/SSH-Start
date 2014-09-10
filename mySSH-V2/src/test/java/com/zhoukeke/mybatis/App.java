package com.zhoukeke.mybatis;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class App {
	public static void main(String[] args) throws IOException {

		SqlSessionFactory sqlSessionFactory = MyBatisUtil
				.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User user = userMapper.getUser("abc");
		System.out
				.println("name: " + user.getName() + "|age: " + user.getAge());

	}
}
