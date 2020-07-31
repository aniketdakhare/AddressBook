package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;

public interface IAddressBookDetails
{
    void addName();
    void addDetails(Person person);
    void editOrDeleteDetails(int select);
    void display();
    void sortBy();
}
