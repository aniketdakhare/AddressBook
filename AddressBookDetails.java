public class AddressBookDetails
{
    //Variables
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    int zipCode;
    String phoneNumber;

    //Method to Display Address Book Details
    public void display()
    {
        System.out.println("ADDRESS BOOK DETAILS : ");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("NAME: "+this.firstName+" "+this.lastName+"  "+"ADDRESS: "+this.address+"  "+"CITY: "+this.city+"  "+"STATE: "+this.state+"  "+"ZIPCODE: "+this.zipCode+"  "+"PHONE NO.: "+this.phoneNumber);
    }

    //Constructor to initialize variables
    AddressBookDetails(String firstName, String lastName, String address, String city, String state, int zipCode,String phoneNumber)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
        this.phoneNumber=phoneNumber;
    }
}
