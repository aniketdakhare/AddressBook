package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.utility.IFileOperator;
import com.bridgelabz.addressbook.utility.UserInputs;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookDetails implements IAddressBookDetails
{
    private final String addressBook;
    private final IFileOperator fileOperator;
    private List<Person> addressBookList;


    public AddressBookDetails(IFileOperator fileOperator, String addressBook)
    {
        this.addressBook = addressBook;
        this.fileOperator = fileOperator;
        this.addressBookList  = fileOperator.addressBookFileToList(addressBook);
    }

    /**
     * Method to take User details
     * @param person defines particular record
     */
    @Override
    public void addDetails(Person person)
    {
        addressBookList.add(person);
        addressBookList = addressBookList.stream()
                .distinct()
                .collect(Collectors.toList());
        fileOperator.addressBookListToFile(addressBookList, addressBook);
    }

    public boolean isPersonDetailsPresent(Person person)
    {
        return addressBookList.contains(person);
    }

    /**
     * Method to Edit record from Address Book
     */
    @Override
    public void editDetails(String firstName, String lastName)
    {
        Person personDetails = addressBookList.stream()
                .filter(details -> details.getFirstName().equals(firstName) && details.getLastName().equals(lastName))
                .findFirst().orElse(null);
        if (personDetails != null)
            addDetails(new UserInputs().addDetails(personDetails));
        else
            System.out.println("Record does not exist");
        fileOperator.addressBookListToFile(addressBookList, addressBook);
    }

    /**
     * Method to Delete record from Address Book
     */
    @Override
    public void deleteDetails(String firstName, String lastName)
    {
        Person personDetails = addressBookList.stream()
                .filter(details -> details.getFirstName().equals(firstName) && details.getLastName().equals(lastName))
                .findFirst().orElse(null);
        if (personDetails != null)
            addressBookList.remove(personDetails);
        else
            System.out.println("Record does not exist");
        fileOperator.addressBookListToFile(addressBookList, addressBook);
    }

    /**
     * Method to Display Address Book Details based on city name or state name
     */
    @Override
    public void display(String place)
    {
        List<Person> addressBookDetails = fileOperator.addressBookFileToList(addressBook);
        System.out.println("ADDRESS BOOK DETAILS : ");
        System.out.println("--------------------------------------------------------------------------------" +
                "---------------------");
        Person personDetails = addressBookDetails.stream()
                .filter(details -> details.getCity().equals(place) || details.getState().equals(place))
                .findFirst().orElse(null);
        System.out.println(personDetails);
        if (personDetails == null)
        {
            System.out.println("Record does not exist");
        }
    }

    /**
     * Method to Sort Address Book details by name, zipcode, city and state
     */
    @Override
    public void sortBy(int select)
    {
        List<Person> addressBookDetails = fileOperator.addressBookFileToList(addressBook);
        switch (select)
        {
            case 1:
                addressBookDetails.sort(Comparator.comparing(Person::hashCode));
                break;
            case 2:
                addressBookDetails.sort(((Comparator<Person>)
                        (bookDetails1, bookDetails2) -> bookDetails2.getZipCode().compareTo(bookDetails1.getZipCode()))
                        .reversed());
                break;
            case 3:
                addressBookDetails.sort(((Comparator<Person>)
                        (bookDetails1, bookDetails2) -> bookDetails2.getCity().compareTo(bookDetails1.getCity())).reversed());
                break;
            case 4:
                addressBookDetails.sort(((Comparator<Person>)
                        (bookDetails1, bookDetails2) -> bookDetails2.getState().compareTo(bookDetails1.getState())).reversed());
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        System.out.println("ADDRESS BOOK DETAILS : ");
        System.out.println("-----------------------------------------------------------------------------" +
                "------------------------");
        addressBookDetails.forEach(System.out::println);
    }
}