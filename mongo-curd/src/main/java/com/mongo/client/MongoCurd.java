package com.mongo.client;

import com.mongo.model.Student;
import com.mongo.util.MongoUtility;
import com.mongo.util.ScannerUtility;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.Iterator;
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

	public void showMongoCollection() {
		MongoCollection<Document> collection = MongoUtility.getMongo();
		FindIterable<Document> iterDoc = collection.find();
		Iterator it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

	public void updateMongoCollection() {
		DB database = MongoUtility.getMongoDatabase();
		DBCollection col = database.getCollection("student");

		Student student = new Student();
		Scanner scanner = ScannerUtility.getScanner();
		System.out.println("Enter the Name U want to Update");
		String searchName = scanner.next();

		System.out.println("Enter Student Name");
		student.setName(scanner.next());
		System.out.println("Enter Student Mobile");
		student.setMobile(scanner.next());

		// Update multiple field in a single document
		DBObject query = new BasicDBObject("name", searchName);
		DBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("name", student.getName()).append("mobile", student.getMobile()));

		WriteResult result = col.update(query, update);

		System.out.println("Document update successfully...");

	}

	public void deleteMongocollection() {
		MongoCollection<Document> collection = MongoUtility.getMongo();
		Scanner scanner = ScannerUtility.getScanner();
		System.out.println("Enter the Name U want to Delete");
		String searchName = scanner.next();

		// Deleting the documents
		collection.deleteOne(Filters.eq("name", searchName));
		System.out.println("Document deleted successfully...");

	}

	public static void main(String[] args) {
		MongoCurd curd = new MongoCurd();

		Scanner scanner = ScannerUtility.getScanner();

		while (true) {
			System.out.println("1. Add Student");
			System.out.println("2. Show Student");
			System.out.println("3. Update Student");
			System.out.println("4. Delete Student");

			System.out.println("Enter Your choice");
			int selectedOption = scanner.nextInt();

			switch (selectedOption) {
			case 1:
				curd.insertMongoCollection();
				break;
			case 2:
				curd.showMongoCollection();
				break;

			case 3:
				curd.updateMongoCollection();
				break;

			case 4:
				curd.deleteMongocollection();
				break;

			case 5:
				System.exit(0);
				break;
			}

		}

	}

}
