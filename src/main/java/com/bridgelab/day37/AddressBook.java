package com.bridgelab.day37;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.bridgelab.day37.ContactPerson;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

public class AddressBook {
	static ArrayList<ContactPerson> persons = new ArrayList<>();
	private static int indexOfPerson;

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

	public static void writeDataToCSV()
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get("Data.csv"));) {
			StatefulBeanToCsvBuilder<ContactPerson> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<ContactPerson> beanWriter = builder.build();
			beanWriter.write(persons);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readDataUsingCSV() throws IOException, CsvValidationException {
		try (Reader reader = Files.newBufferedReader(Paths.get("Data.csv"));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				System.out.println("First Name - " + nextRecord[3]);
				System.out.println("Last Name - " + nextRecord[4]);
				System.out.println("Address - " + nextRecord[0]);
				System.out.println("City - " + nextRecord[2]);
				System.out.println("State - " + nextRecord[6]);
				System.out.println("Phone - " + nextRecord[5]);
				System.out.println("Zip - " + nextRecord[7]);
			}
		}
	}
	public static void writeDataInJSon() throws IOException {
        {
            Path filePath = Paths.get("Data.json");
            Gson gson = new Gson();
            String json = gson.toJson(persons);
            FileWriter writer = new FileWriter(String.valueOf(filePath));
            writer.write(json);
            writer.close();
        }
    }

    public static void readDataFromJson() throws IOException {
        ArrayList<ContactPerson> contactList = null;
        Path filePath = Paths.get("Data.json");
        try (Reader reader = Files.newBufferedReader(filePath);) {
            Gson gson = new Gson();
            contactList = new ArrayList<ContactPerson>(Arrays.asList(gson.fromJson(reader, ContactPerson[].class)));
            for (ContactPerson contact : contactList) {
                System.out.println("Firstname : " + contact.getFirstName());
                System.out.println("Lastname : " + contact.getLastName());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("City : " + contact.getCity());
                System.out.println("State : " + contact.getState());
                System.out.println("Zip : " + contact.getZipCode());
                System.out.println("Phone number : " + contact.getPhoneNumber());
            }

        }
    }

}
