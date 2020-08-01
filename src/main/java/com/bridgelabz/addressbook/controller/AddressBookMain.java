package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.service.AddressBookDetails;
import com.bridgelabz.addressbook.service.IAddressBookDetails;
import com.bridgelabz.addressbook.utility.CSVFileOperator;
import com.bridgelabz.addressbook.utility.JSONFileHandlerOperator;
import com.bridgelabz.addressbook.utility.JSONGsonLibraryOperator;
import com.bridgelabz.addressbook.utility.UserInputs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBookMain
{
    IAddressBookDetails addressBookDetails;
    UserInputs userInputs = new UserInputs();
    Scanner scan = new Scanner(System.in);

    private void selectFileOperator()
    {
        System.out.println("\nSelect & enter the file operation you want to do: \n1: JSON File using file handler." +
                " \n2: CSV File. \n3: JSON File using GSON Library.");
        int value = scan.nextInt();
        switch (value)
        {
            case 1:
                addressBookDetails = new AddressBookDetails(new JSONFileHandlerOperator(), "AddressBook.json");
                break;
            case 2:
                addressBookDetails = new AddressBookDetails(new CSVFileOperator(), "C:\\Users\\Aniket\\IdeaProjects\\AddressBookProblem\\AddressBook.csv");
                break;
            case 3:
                addressBookDetails = new AddressBookDetails(new JSONGsonLibraryOperator(), "C:\\Users\\Aniket\\IdeaProjects\\AddressBookProblem\\AddressBookDetails.json");
        }
        this.selectTask();
    }

    /**
     * Method to Select Task
     */
    private void selectTask()
    {
        boolean value = true;
        while(value)
        {
            try
            {
                System.out.println("\nSelect & enter the task you want to do: \n1: Add details \n2: Display details " +
                        "\n3: Edit details \n4: Delete details \n5: Sort by name, city, zipcode or state \n6: Exit");
                int num = scan.nextInt();
                switch (num)
                {
                    case 1:
                        addressBookDetails.addDetails(userInputs.addName());
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 2:
                        addressBookDetails.display(userInputs.display());
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 3:
                        String[] name = userInputs.editOrDeleteDetails();
                        addressBookDetails.editDetails(name[0], name[1]);
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 4:
                        String[] personName = userInputs.editOrDeleteDetails();
                        addressBookDetails.deleteDetails(personName[0], personName[1]);
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 5:
                        addressBookDetails.sortBy(userInputs.sortBy());
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 6:
                        value=false;
                        break;
                    default:
                        System.out.println("Invalid Input");
                        System.out.println("===============================================================" +
                                "===============================");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Enter the valid required input.");
                scan.next();
            }
        }
    }

    /**
     * Main Method
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome to Address Book Program");
        AddressBookMain addressBookMain = new AddressBookMain();
        addressBookMain.selectFileOperator();
    }
}
