package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.Person;

import java.util.List;

public interface IFileOperator
{
    public void addressBookListToFile(List<Person> addressBook, String filePath);
    public List<Person> addressBookFileToList(String filePath);
}
