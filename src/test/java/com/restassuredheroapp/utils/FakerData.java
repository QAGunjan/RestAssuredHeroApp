package com.restassuredheroapp.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class FakerData {

	public static String GetName() {
		Faker faker = new Faker();
//		return faker.regexify("[A-Za-z0-9 ,_-]{10}");
		return faker.regexify("[A-Z]{10}");

	}
	
	public static int GetRandomNumber() {
		Faker faker = new Faker();
//		return faker.regexify("[A-Za-z0-9 ,_-]{10}");
//		 faker.regexify("{10}");

		return Integer.parseInt(faker.regexify("[0-9]{5}"));
		
	}
	
	
}
