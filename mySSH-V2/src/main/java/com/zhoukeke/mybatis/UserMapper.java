package com.zhoukeke.mybatis;

public interface UserMapper {
	public void insertUser(User user); 
    public User getUser(String name); 
}
