package com.bridgelabz.addressbooktest;

import com.bridgelabz.addressbook.service.AddressBookDetails;
import org.junit.Before;
import org.junit.Test;

public class AddressBookDetailsTest
{
    AddressBookDetails addressBookDetails;

    @Before
    public void setUp()
    {
        addressBookDetails = new AddressBookDetails();
    }

    @Test
    public void givenPersonDetails_WhenAddedToAddressBook_ShouldReturnTrue()
    {
        addressBookDetails.addName();
    }
}
