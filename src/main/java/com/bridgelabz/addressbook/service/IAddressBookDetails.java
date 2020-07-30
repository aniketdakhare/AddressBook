package com.bridgelabz.addressbook.service;

public interface IAddressBookDetails
{
    void addName();
    void addDetails(AddressBookDetails addressBook);
    void editOrDeleteDetails(int select);
    void display();
    void sortBy();
}
