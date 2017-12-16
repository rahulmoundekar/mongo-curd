package com.mongo.util;

import java.util.Scanner;

public class ScannerUtility {

	private static Scanner scanner = new Scanner(System.in);

	public static Scanner getScanner() {
		if (scanner == null) {
			return scanner;
		}
		return scanner;
	}

}
