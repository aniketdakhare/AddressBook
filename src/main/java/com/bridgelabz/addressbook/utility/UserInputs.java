package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.model.Person;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInputs
{
    private final Scanner scan = new Scanner(System.in).useDelimiter("\n");
    private final String PATTERN = "^[a-zA-Z]{1,}$";

    /**
     * Method to take Name from User
     */
    public Person addPerson()
    {
        Person person = new Person();
        this.addFirstName(person);
        this.addLastName(person);
        this.addAddress(person);
        this.addCity(person);
        this.addState(person);
        this.addZipCode(person);
        this.addPhoneNumber(person);
        return person;
    }

    /**
     * Method to take User details
     * @param person defines particular record
     */
    public Person addDetails(Person person)
    {
        boolean flag = true;
        while (flag)
        {
            scan.nextLine();
            int selectField = this.selectFieldToEdit();
            switch (selectField)
            {
                case 1:
                    this.addAddress(person);
                    break;
                case 2:
                    this.addCity(person);
                    break;
                case 3:
                    this.addState(person);
                    break;
                case 4:
                    this.addZipCode(person);
                    break;
                case 5:
                    this.addPhoneNumber(person);
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Please Select valid Input.");
            }
        }
        return person;
    }

    private boolean validateInput(String input, String validator)
    {
        Pattern pattern = Pattern.compile(validator);
        if (pattern.matcher(input).matches())
            return true;
        else
            System.out.print("Invalid Input. Please Enter Again.\n");
        return false;
    }

    private void addFirstName(Person person)
    {
        do {
            System.out.println("Enter your First Name");
            person.firstName = scan.next();
        } while (!validateInput(person.firstName, PATTERN));
    }

    private void addLastName(Person person)
    {
        do {
            System.out.println("Enter your Last Name");
            person.lastName = scan.next();
        } while (!validateInput(person.lastName, PATTERN));
    }

    public void addAddress(Person person)
    {
        do {
            System.out.println("Enter your Address");
            person.address = scan.next();
        } while (!validateInput(person.address, "^[a-zA-Z0-9,-/#&@. ]{1,}$"));
    }

    public void addCity(Person person)
    {
        do {
            System.out.println("Enter your City");
            person.city = scan.next();
        } while (!validateInput(person.city, PATTERN));
    }

    public void addState(Person person)
    {
        do {
            System.out.println("Enter your State");
            person.state = scan.next();
        } while (!validateInput(person.state, PATTERN));
    }

    public void addZipCode(Person person)
    {
        do {
            System.out.println("Enter your Zip code");
            person.zipCode = scan.next();
        } while (!validateInput(person.zipCode, "^[1-9][0-9]{5}$"));
    }

    public void addPhoneNumber(Person person)
    {
        do {
            System.out.println("Enter your Phone Number");
            person.phoneNumber = scan.next();
        } while (!validateInput(person.phoneNumber, "^[1-9][0-9]{9}$"));
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

    public int selectFieldToEdit()
    {
        System.out.println("Select field to edit details. \n1: Address \n2: City" +
                " \n3: State \n4: Zipcode \n5: Phone Number \n6: Save and Exit");
        int select = scan.nextInt();
        return select;
    }
}
