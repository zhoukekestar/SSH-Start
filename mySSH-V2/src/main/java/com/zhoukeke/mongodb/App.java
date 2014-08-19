package com.zhoukeke.mongodb;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class App {
public static void main(String[] args) throws UnknownHostException {
	MongoClient mongoClient = new MongoClient("localhost");
	DB db = mongoClient.getDB("mydb");
	Set<String> colls = db.getCollectionNames();
	for (String s : colls){
		System.out.println(s);
	}
	DBCollection coll = db.getCollection("hello");
	System.out.println(coll.find());
}
}
