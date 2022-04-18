package com.bridgelab.day37;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AddressBook {
	static ArrayList<ContactPerson> persons = new ArrayList<>();
	
	 public static void writeData() {
	        StringBuffer personBuffer = new StringBuffer();
	        persons.forEach(person -> {
	            String personDataString = person.toString().concat("\n");
	            personBuffer.append(personDataString);
	        });
	        try {
	            Files.write(Paths.get("Data1.txt"), personBuffer.toString().getBytes());

	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	    }

	    public static void readFileData() {
	        try {
	            Files.lines(new File("Data1.txt").toPath()).map(String::trim).forEach(System.out::println);

	        } catch (IOException e) {
	            e.printStackTrace();

	        }
	    }



}
