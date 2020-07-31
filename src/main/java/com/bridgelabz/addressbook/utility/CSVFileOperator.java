package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.Person;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CSVFileOperator implements IFileOperator
{
    @Override
    public void addressBookListToFile(List<Person> addressBook, String filePath)
    {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath)))
        {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(addressBook);
        }
        catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> addressBookFileToList(String filePath)
    {
        List<Person> addressBook = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath)))
        {
            csvReader.readNext();
            String[] data;
            while ((data = csvReader.readNext()) != null)
            {
                addressBook.add(new Person(data[2], data[3], data[0], data[1], data[5], data[6], data[4]));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return addressBook;
    }
}

