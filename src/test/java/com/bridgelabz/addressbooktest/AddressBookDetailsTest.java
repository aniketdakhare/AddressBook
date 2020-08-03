package com.bridgelabz.addressbooktest;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.service.AddressBookDetails;
import com.bridgelabz.addressbook.utility.JSONFileHandlerOperator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressBookDetailsTest
{
    AddressBookDetails addressBookDetails;
    Person firstPerson;
    Person secondPerson;

    @Before
    public void setUp()
    {
        firstPerson = new Person("Aniket", "Dakhare", "Dhanori", "Pune", "Maharashtra",
                "443445", "9988998898");
        secondPerson = new Person("Bharat", "Rawat", "Dhanori", "Pune", "Maharashtra",
                "443445", "9900099998");
        addressBookDetails = new AddressBookDetails(new JSONFileHandlerOperator(), "AddressBook.json");
    }

    @Test
    public void givenPersonDetails_WhenAddedToAddressBook_ShouldReturnTrue()
    {
        addressBookDetails.addDetails(firstPerson);
        boolean isPresent = addressBookDetails.isPersonDetailsPresent(firstPerson);
        Assert.assertTrue(isPresent);
    }

    @Test
    public void givenPersonDetails_WhenCheckedAddressBookIfNotPresent_ShouldReturnFalse()
    {
        boolean isPresent = addressBookDetails.isPersonDetailsPresent(secondPerson);
        Assert.assertFalse(isPresent);
    }
}
