package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.utility.IFileOperator;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookDetails implements IAddressBookDetails
{
    String addressBook;
    Scanner scan = new Scanner(System.in);
    IFileOperator fileOperator;
    List<Person> addressBookList;

    public AddressBookDetails(IFileOperator fileOperator, String addressBook)
    {
        this.addressBook = addressBook;
        this.fileOperator = fileOperator;
        this.addressBookList  = fileOperator.addressBookFileToList(addressBook);
    }

    /**
     * Method to take Name from User
     */
    @Override
    public void addName()
    {
        Person person = new Person();
        System.out.println("Enter your First Name");
        person.firstName = scan.next();
        System.out.println("Enter your Last Name");
        person.lastName = scan.next();
        addDetails(person);
    }

    /**
     * Method to take User details
     * @param person defines particular record
     */
    @Override
    public void addDetails(Person person)
    {
        scan.nextLine();
        System.out.println("Enter your Address");
        person.address = scan.nextLine();
        System.out.println("Enter your City");
        person.city = scan.nextLine();
        System.out.println("Enter your State");
        person.state = scan.nextLine();
        System.out.println("Enter your Zip code");
        person.zipCode = scan.next();
        //To Check the 6 digit Zipcode
        while(person.zipCode.length() != 6)
        {
            System.out.println("enter 6 digit number");
            person.zipCode = scan.next();
            if(person.zipCode.length() == 6)
            {
                break;
            }
        }
        System.out.println("Enter your Phone Number");
        person.phoneNumber = scan.next();
        addressBookList.add(person);
        addressBookList = addressBookList.stream()
                .distinct()
                .collect(Collectors.toList());
        fileOperator.addressBookListToFile(addressBookList, addressBook);
    }

    /**
     * Method to Edit and Delete record from Address Book
     * @param select is used to select edit or delete
     */
    @Override
    public void editOrDeleteDetails(int select)
    {
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        //To select whether to Edit or Delete the record
        Person personDetails = addressBookList.stream()
                .filter(details -> details.getFirstName().equals(firstName) && details.getLastName().equals(lastName))
                .findFirst().orElse(null);
        switch (select)
        {
            case 0:
                assert personDetails != null;
                addDetails(personDetails);
                break;
            case 1:
                addressBookList.remove(personDetails);
                break;
        }
        if (personDetails == null)
        {
            System.out.println("Record does not exist");
        }
        fileOperator.addressBookListToFile(addressBookList, addressBook);
    }

    /**
     * Method to Display Address Book Details based on city name or state name
     */
    @Override
    public void display()
    {
        List<Person> addressBookDetails = fileOperator.addressBookFileToList(addressBook);
        System.out.println("Enter City Name or State Name");
        String place = scan.next();
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
    public void sortBy()
    {
        List<Person> addressBookDetails = fileOperator.addressBookFileToList(addressBook);
        System.out.println("Select the type of sorting you want to do. \n1: Sort by name \n2: Sort by ZipCode" +
                " \n3: Sort by City \n4: Sort by State");
        int select = scan.nextInt();
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