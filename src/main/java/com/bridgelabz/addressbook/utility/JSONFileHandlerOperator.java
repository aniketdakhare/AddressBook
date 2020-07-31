package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileHandlerOperator implements IFileOperator
{
    @Override
    public void addressBookListToFile(List<Person> addressBook, String jsonFilePath)
    {
        JSONArray personList = new JSONArray();
        addressBook.forEach(person -> {
            JSONObject personDetails = new JSONObject();
            personDetails.put("First Name", person.getFirstName());
            personDetails.put("Last Name", person.getLastName());
            personDetails.put("Phone Number", person.getPhoneNumber());
            personDetails.put("Address", person.getAddress());
            personDetails.put("City", person.getCity());
            personDetails.put("State", person.getState());
            personDetails.put("Zipcode", person.getZipCode());
            JSONObject personObject = new JSONObject();
            personObject.put("person", personDetails);
            personList.add(personObject);
        });
        try
        {
            FileWriter fileWriter = new FileWriter(jsonFilePath);
            fileWriter.append(personList.toJSONString());
            fileWriter.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> addressBookFileToList(String jsonFilePath)
    {
        JSONParser jsonParser = new JSONParser();
        List<Person> addressBook = new ArrayList<>();
        try
        {
            FileReader fileReader = new FileReader(jsonFilePath);
            Object obj = jsonParser.parse(fileReader);
            JSONArray personList = (JSONArray) obj;
            personList.forEach(person -> addressBook.add(parsePersonObject((JSONObject) person)));
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    private Person parsePersonObject(JSONObject jsonObject)
    {
        JSONObject personObject = (JSONObject) jsonObject.get("person");
        Person person = new Person();
        person.firstName = (String) personObject.get("First Name");
        person.lastName = (String) personObject.get("Last Name");
        person.phoneNumber = (String) personObject.get("Phone Number");
        person.address = (String) personObject.get("Address");
        person.city = (String) personObject.get("City");
        person.state = (String) personObject.get("State");
        person.zipCode = (String) personObject.get("Zipcode");
        return person;
    }
}
