package com.bridgelabz.addressbook.model;

import com.bridgelabz.addressbook.utility.AddressBookDetails;

public interface AddressBookContent
{
    void addName();
    void addDetails(AddressBookDetails addressBook);
    void editOrDeleteDetails(int select);
    void display();
    void sortBy();
}
