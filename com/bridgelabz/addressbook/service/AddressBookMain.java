package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.AddressBookContent;
import com.bridgelabz.addressbook.utility.AddressBookDetails;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBookMain
{
    AddressBookContent addressBookContent = new AddressBookDetails();
    Scanner scan = new Scanner(System.in);

    /**
     * Method to Select Task
     */
    public void selectTask()
    {
        boolean value = true;
        while(value)
        {
            try
            {
                System.out.println("\nSelect & enter the task you want to do: \n1: Add details \n2: Display details " +
                        "\n3: Edit details \n4: Delete details \n5: Sort by name, city, zipcode or state \n6: Exit");
                int num=scan.nextInt();
                switch (num)
                {
                    case 1:
                        addressBookContent.addName();
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 2:
                        addressBookContent.display();
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 3:
                        addressBookContent.editOrDeleteDetails(0);
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 4:
                        addressBookContent.editOrDeleteDetails(1);
                        System.out.println("===============================================================" +
                                "===============================");
                        break;
                    case 5:
                        addressBookContent.sortBy();
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
        addressBookMain.selectTask();
    }
}
