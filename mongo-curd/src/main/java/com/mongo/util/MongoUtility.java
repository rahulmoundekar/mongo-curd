package com.mongo.util;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

public class MongoUtility {

	public static MongoCollection getMongo() {

		// Creating a Mongo client
		MongoClient mongo = new MongoClient("localhost", 27017);

		// Creating Credentials
		MongoCredential credential = MongoCredential.createCredential("localhost", "curd", "password".toCharArray());
		System.out.println("Connected to the database successfully");

		// Accessing the database
		MongoDatabase database = mongo.getDatabase("curd");

		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("student");
		System.out.println("Collection sampleCollection selected successfully");

		return collection;
	}

}
