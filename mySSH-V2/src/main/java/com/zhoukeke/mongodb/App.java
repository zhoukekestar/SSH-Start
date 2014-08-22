package com.zhoukeke.mongodb;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@SuppressWarnings("deprecation")
public class App {
public static void main(String[] args) throws UnknownHostException {
	MongoClient mongoClient = new MongoClient("192.168.0.222", 27017);
	DB db = mongoClient.getDB("admin");
	if (db.authenticate("sa", "sa".toCharArray())){
		System.out.println("success");
	}
	
	
	Set<String> colls = db.getCollectionNames();
	for (String s : colls){
		System.out.println(s);
	}
	DBCollection coll = db.getCollection("hello");
	System.out.println(coll.find());
}
}
