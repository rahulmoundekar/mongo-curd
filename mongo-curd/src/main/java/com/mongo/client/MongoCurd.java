package com.mongo.client;

import com.mongo.model.Student;
import com.mongo.util.MongoUtility;
import com.mongo.util.ScannerUtility;
import com.mongodb.client.MongoCollection;

import java.util.Scanner;

import org.bson.Document;

public class MongoCurd {

	public void insertMongoCollection() {
		MongoCollection<Document> collection = MongoUtility.getMongo();

		System.out.println("Enter How many Student U want to Add");
		Scanner scanner = ScannerUtility.getScanner();
		int noOfStudent = scanner.nextInt();
		for (int i = 0; i < noOfStudent; i++) {
			Student student = new Student();
			System.out.println("Enter Student Name");
			student.setName(scanner.next());
			System.out.println("Enter Student Mobile");
			student.setMobile(scanner.next());

			Document document = new Document("name", student.getName()).append("mobile", student.getMobile());
			System.out.println(document.get("name"));
			collection.insertOne(document);
		}

		System.out.println("Document inserted successfully");
	}

	public static void main(String[] args) {
		MongoCurd curd = new MongoCurd();
		curd.insertMongoCollection();

	}

}
