package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.utility.UserInputs;

import java.sql.*;

public class AddressBookDetailsDB implements IAddressBookDetails
{
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private UserInputs userInputs = new UserInputs();

    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook","root","sql@234");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addDetails(Person person)
    {
        String query ="INSERT INTO person VALUES(0,'" + person.firstName + " " + person.lastName + "','"
                + person.address + "','" + person.city + "','" + person.state + "','" + person.zipCode
                + "','" + person.phoneNumber + "');";
        this.manipulatingPersonDetails(query);
    }

    @Override
    public void editDetails(String firstName, String lastName)
    {
        Person person = new Person();
        boolean flag = true;
        while (flag)
        {
            int selectField = userInputs.selectFieldToEdit();
            switch (selectField)
            {
                case 1:
                    userInputs.addAddress(person);
                    this.updateDetails("address", person.getAddress(), firstName, lastName);
                    break;
                case 2:
                    userInputs.addCity(person);
                    this.updateDetails("city", person.getCity(), firstName, lastName);
                    break;
                case 3:
                    userInputs.addAddress(person);
                    this.updateDetails("state", person.getState(), firstName, lastName);
                    break;
                case 4:
                    userInputs.addAddress(person);
                    this.updateDetails("zipcode", person.getZipCode(), firstName, lastName);
                    break;
                case 5:
                    userInputs.addAddress(person);
                    this.updateDetails("phone_number", person.getPhoneNumber(), firstName, lastName);
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Please Select valid Input.");
            }
        }
    }

    private void updateDetails(String... value)
    {
        String query = "UPDATE person SET " + value[0] + " = '" + value[1] + "' WHERE name = '"
                + value[2] + " " + value[3] + "'";
        this.manipulatingPersonDetails(query);
    }

    @Override
    public void deleteDetails(String firstName, String lastName)
    {
        String query="DELETE FROM person WHERE name = '" + firstName + " " + lastName + "'";
        this.manipulatingPersonDetails(query);
    }

    @Override
    public void display(String place)
    {
        String query = "SELECT * FROM person WHERE city = '" + place + "' OR state = '" + place + "'";
        this.displayPersonDetails(query);
    }

    @Override
    public void sortBy(int select)
    {
        String query;
        switch (select)
        {
            case 1:
                query = "SELECT * FROM person ORDER BY name ASC";
                this.displayPersonDetails(query);
                break;
            case 2:
                query = "SELECT * FROM person ORDER BY zipcode ASC";
                this.displayPersonDetails(query);
                break;
            case 3:
                query = "SELECT * FROM person ORDER BY city ASC";
                this.displayPersonDetails(query);
                break;
            case 4:
                query = "SELECT * FROM person ORDER BY state ASC";
                this.displayPersonDetails(query);
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    private void manipulatingPersonDetails(String query)
    {
        try
        {
            statement =connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void displayPersonDetails(String query)
    {
        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {			System.out.println("--------------------------------------------------------" +
                    "--------------------------------------------------------");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zipcode = resultSet.getString("zipcode");
                String phone_number = resultSet.getString("phone_number");
                System.out.println("NAME: " + name + "  " + "ADDRESS: " + address + "  " + "CITY: " + city + "  "
                        + "STATE: " + state + "  " + "ZIPCODE: " + zipcode + "  " + "PHONE NO.: " + phone_number);
            }
            System.out.println("--------------------------------------------------------" +
                    "--------------------------------------------------------");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
