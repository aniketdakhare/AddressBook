package com.bridgelabz.addressbooktest;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.service.AddressBookDetails;
import com.bridgelabz.addressbook.utility.JSONFileHandlerOperator;
import org.junit.Before;
import org.junit.Test;

public class AddressBookDetailsTest
{
    AddressBookDetails addressBookDetails;

    @Before
    public void setUp()
    {
        addressBookDetails = new AddressBookDetails(new JSONFileHandlerOperator(), "AddressBook.json");
    }

    @Test
    public void givenPersonDetails_WhenAddedToAddressBook_ShouldReturnTrue()
    {
        addressBookDetails.addDetails(new Person());
    }
}
