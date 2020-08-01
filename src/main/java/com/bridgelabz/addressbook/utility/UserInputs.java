package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.Person;

import java.util.Scanner;

public class UserInputs
{
    Scanner scan = new Scanner(System.in);

    /**
     * Method to take Name from User
     */
    public Person addName()
    {
        Person person = new Person();
        System.out.println("Enter your First Name");
        if (scan.hasNextLine())
            person.firstName = scan.next();
        System.out.println("Enter your Last Name");
        if (scan.hasNextLine())
            person.lastName = scan.next();
        return addDetails(person);
    }

    /**
     * Method to take User details
     * @param person defines particular record
     */
    public Person addDetails(Person person)
    {
        scan.nextLine();
        System.out.println("Enter your Address");
        if (scan.hasNextLine())
            person.address = scan.nextLine();
        System.out.println("Enter your City");
        if (scan.hasNextLine())
            person.city = scan.nextLine();
        System.out.println("Enter your State");
        if (scan.hasNextLine())
            person.state = scan.nextLine();
        System.out.println("Enter your Zip code");
        if (scan.hasNextLine())
            person.zipCode = scan.next();
        //To Check the 6 digit Zipcode
        while (person.zipCode.length() != 6) {
            System.out.println("enter 6 digit number");
            if (scan.hasNextLine())
                person.zipCode = scan.next();
            if (person.zipCode.length() == 6) {
                break;
            }
        }
        System.out.println("Enter your Phone Number");
        if (scan.hasNextLine())
            person.phoneNumber = scan.next();
        return person;
    }

    public String[] editOrDeleteDetails()
    {
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        String[] name = {firstName, lastName};
        return name;
    }

    public String display()
    {
        System.out.println("Enter City Name or State Name");
        String place = scan.next();
        return place;
    }

    public int sortBy()
    {
        System.out.println("Select the type of sorting you want to do. \n1: Sort by name \n2: Sort by ZipCode" +
                " \n3: Sort by City \n4: Sort by State");
        int select = scan.nextInt();
        return select;
    }
}
