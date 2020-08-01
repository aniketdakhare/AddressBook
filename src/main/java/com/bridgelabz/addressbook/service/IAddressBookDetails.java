package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;

public interface IAddressBookDetails
{
    void addDetails(Person person);
    void editDetails(String firstName, String lastName);
    void deleteDetails(String firstName, String lastName);
    void display(String place);
    void sortBy(int select);
}
