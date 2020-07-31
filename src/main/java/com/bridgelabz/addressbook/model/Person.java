package com.bridgelabz.addressbook.model;

import java.util.Objects;

public class Person
{
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String state;
    public String zipCode;
    public String phoneNumber;

    public Person() { }

    public Person(String firstName, String lastName, String address, String city, String state, String zipCode,
                  String phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     *Overridden toString to print the details of address book
     * @return details of address book
     */
    @Override
    public String toString()
    {
        return "NAME: " + firstName + " " + lastName + "  " + "ADDRESS: " + address + "  " + "CITY: " +
                city + "  " + "STATE: " + state + "  " + "ZIPCODE: " + zipCode + "  " + "PHONE NO.: " + phoneNumber;
    }

    /**
     * Overridden in order to avoid duplicate entries
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(firstName, lastName);
    }
}
