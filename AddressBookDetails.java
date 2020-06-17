import java.util.Scanner;

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
    Scanner scan=new Scanner(System.in);

    //Method to take Name from User
    public void addName()
    {
        System.out.println("Enter your First Name");
        this.firstName = scan.nextLine();
        System.out.println("Enter your Last Name");
        this.lastName = scan.nextLine();
        addDetails();
    }

    //Method to take Details from User
    public void addDetails()
    {
        scan.nextLine();
        System.out.println("Enter your Address");
        this.address = scan.nextLine();
        System.out.println("Enter your City");
        this.city = scan.nextLine();
        System.out.println("Enter your State");
        this.state = scan.nextLine();
        System.out.println("Enter your Zip code");
        this.zipCode = scan.nextInt();
        int length = (int)(Math.log10(this.zipCode)+1);
        while(length != 6)
        {
            System.out.println("enter 6 digit number");
            this.zipCode = scan.nextInt();
            length = (int)(Math.log10(this.zipCode)+1);
            if(length==6)
            {
                break;
            }
        }
        System.out.println("Enter your Phone Number");
        this.phoneNumber = scan.next();
    }

    //Method to Edit Address Book
    public void editDetails()
    {
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        if( this.firstName.equals(firstName) )
        {
            if(this.lastName.equals(lastName))
            {
                addDetails();
            }
            else
            {
                System.out.println("Record does not exist");
            }
        }
    }

    //Method to Display Address Book Details
    public void display()
    {
        System.out.println("ADDRESS BOOK DETAILS : ");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("NAME: "+this.firstName+" "+this.lastName+"  "+"ADDRESS: "+this.address+"  "+"CITY: "+this.city+"  "+"STATE: "+this.state+"  "+"ZIPCODE: "+this.zipCode+"  "+"PHONE NO.: "+this.phoneNumber);
    }

}
