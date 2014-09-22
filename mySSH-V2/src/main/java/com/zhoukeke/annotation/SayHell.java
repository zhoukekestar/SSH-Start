package com.zhoukeke.annotation;

import com.zhoukeke.annotation.Yts.YtsType;

@Yts(classType =YtsType.util)  
public class SayHell {  
  
    @HelloWorld(name = " 小明 ")  
    @Yts  
    public void sayHello(String name){
    	
        if(name == null || name.equals("")){  
            System.out.println("hello world!");  
        }else{  
            System.out.println(name + "say hello world!");  
        }  
    }  
    
    
    public static void main(String[] args) throws Exception{  
        ParseAnnotation parse = new ParseAnnotation();  
        parse.parseMethod(SayHell.class);  
        parse.parseType(SayHell.class); 
    
    }  
} 
