package com.zhoukeke.reflect;

import java.io.*;
import java.util.*;
 
class Factory{
    public static Fruit getInstance(String ClassName){
        Fruit f=null;
        try{
            f=(Fruit)Class.forName(ClassName).newInstance();
            /*通过反射机制，不管有多少继承，只需要写这么一个就行了*/
        }catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
class App{
    public static void main(String[] a) throws FileNotFoundException, IOException{
        Properties pro= getPro();
        Fruit f=Factory.getInstance(pro.getProperty("apple"));
        if(f!=null){
            f.eat();
        }
    }
    
    public static Properties getPro() throws FileNotFoundException, IOException{
        Properties pro=new Properties();
        //App.class.getClassLoader().getResource("fruits.properties");
        File f=new File("src/main/sources/fruits.properties");
        if(f.exists()){
            pro.load(new FileInputStream(f));
        }else{
        	System.out.println("File can't find. Error.");
            /*pro.setProperty("apple", "com.zhoukeke.reflect.Apple");
            pro.setProperty("orange", "com.zhoukeke.reflect.Orange");
            pro.store(new FileOutputStream(f), "FRUIT CLASS");*/
        }
        return pro;
    }
}
