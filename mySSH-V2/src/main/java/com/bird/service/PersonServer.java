package com.bird.service;

public interface PersonServer {  
	  
    public void save(String name);  
    public void update(String name, Integer id);  
    public String getPersonName(Integer id);  
      
} 
