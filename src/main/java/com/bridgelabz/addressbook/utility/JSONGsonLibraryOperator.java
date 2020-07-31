package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.Person;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONGsonLibraryOperator implements IFileOperator
{
    @Override
    public void addressBookListToFile(List<Person> addressBook, String filePath)
    {
        String personDetail = new Gson().toJson(addressBook);
        try(FileWriter writer = new FileWriter(filePath))
        {
            writer.write(personDetail);
        }
        catch (IOException | NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> addressBookFileToList(String filePath)
    {
        List<Person> addressBook = null;
        try
        {
            Person[] personDetails = new Gson().fromJson(new FileReader(filePath), Person[].class);
            addressBook = new ArrayList<>(Arrays.asList(personDetails));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return addressBook;
    }
}