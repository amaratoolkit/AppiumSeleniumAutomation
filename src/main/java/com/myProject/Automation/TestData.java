package com.myProject.Automation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 

public class TestData {
	
	
	public static String readData(String key, String value) throws FileNotFoundException, IOException, ParseException {
		String keyValue = null;
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("noBrokerData.json")) {
			Object obj = jsonParser.parse(reader);
			JSONArray employeeList = (JSONArray) obj;			
			for(Object emp: employeeList) {
				keyValue = getData((JSONObject) emp, key, value);
			}
		}
		return keyValue;
	}

	public static String getData(JSONObject employee, String key, String value) {

		JSONObject employeeObject = (JSONObject) employee.get(key);
		String firstName = (String) employeeObject.get(value);
		System.out.println(firstName);
		return firstName;
	}
	  
}
