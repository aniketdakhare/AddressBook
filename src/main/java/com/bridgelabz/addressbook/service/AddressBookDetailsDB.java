package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.utility.UserInputs;

import java.sql.*;

public class AddressBookDetailsDB extends DBConnection implements IAddressBookDetails
{
    private UserInputs userInputs = new UserInputs();

    @Override
    public void addDetails(Person person)
    {
        String query ="INSERT INTO person VALUES(0,'" + person.firstName + " " + person.lastName + "','"
                + person.city + "','" + person.zipCode + "','" + person.state + "','" + person.address
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
        String query = "SELECT * FROM person ORDER BY "+ (select+1) +" ASC";
        this.displayPersonDetails(query);
    }

    private void manipulatingPersonDetails(String query)
    {
        try(Connection connection = super.getConnection();
            Statement statement = connection.createStatement())
        {
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void displayPersonDetails(String query)
    {
        try(Connection connection = super.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query))
        {
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
