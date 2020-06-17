import java.util.*;
import java.util.stream.Collectors;

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
    List<AddressBookDetails> addressBookList= new ArrayList<AddressBookDetails>();

    //Method to take Name from User
    public void addName()
    {
        AddressBookDetails addressBook = new AddressBookDetails();
        System.out.println("Enter your First Name");
        addressBook.firstName = scan.next();
        System.out.println("Enter your Last Name");
        addressBook.lastName = scan.next();
        addDetails(addressBook);
        addressBookList.add(addressBook);
        addressBookList = addressBookList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    //Method to take Name from User
    public void addDetails(AddressBookDetails addressBook)
    {
        scan.nextLine();
        System.out.println("Enter your Address");
        addressBook.address = scan.nextLine();
        System.out.println("Enter your City");
        addressBook.city = scan.nextLine();
        System.out.println("Enter your State");
        addressBook.state = scan.nextLine();
        System.out.println("Enter your Zip code");
        addressBook.zipCode = scan.nextInt();
        int length = (int)(Math.log10(addressBook.zipCode)+1);
        while(length != 6)
        {
            System.out.println("enter 6 digit number");
            addressBook.zipCode = scan.nextInt();
            length = (int)(Math.log10(addressBook.zipCode)+1);
            if(length==6)
            {
                break;
            }
        }
        System.out.println("Enter your Phone Number");
        addressBook.phoneNumber = scan.next();
    }

    @Override
    public String toString() {
        return "NAME: "+firstName+" "+lastName+"  "+"ADDRESS: "+address+"  "+"CITY: "+city+"  "+"STATE: "+state+"  "+"ZIPCODE: "+zipCode+"  "+"PHONE NO.: "+phoneNumber;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBookDetails that = (AddressBookDetails) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    //Method to Edit and Delete record from Address Book
    public void editOrDeleteDetails(int select)
    {
        boolean check = true;
        System.out.println("Enter your First name");
        String firstName = scan.next();
        System.out.println("Enter your Last name");
        String lastName = scan.next();
        for ( AddressBookDetails details : addressBookList)
        {
            if( details.firstName.equals(firstName) )
            {
                if(details.lastName.equals(lastName))
                {
                    check=false;
                    switch (select)
                    {
                        case 0:
                            addDetails(details);
                            break;
                        case 1:
                            for (int num=0; num<addressBookList.size(); num++)
                            {
                                addressBookList.remove(num);
                                break;
                            }
                            break;
                    }
                }
            }
        }
        if (check==true)
        {
            System.out.println("Record does not exist");
        }
    }

    //Method to Display Address Book Details
    public void display()
    {
        System.out.println("ADDRESS BOOK DETAILS : ");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        Iterator details=addressBookList.iterator();
        while (details.hasNext())
        {
            System.out.println(details.next());
        }
    }

}
