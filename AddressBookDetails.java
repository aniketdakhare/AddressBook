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
        //To Check the 6 digit Zipcode
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
    //Overridden toString to print the details of address book
    @Override
    public String toString() {
        return "NAME: "+firstName+" "+lastName+"  "+"ADDRESS: "+address+"  "+"CITY: "+city+"  "+"STATE: "+state+"  "+"ZIPCODE: "+zipCode+"  "+"PHONE NO.: "+phoneNumber;

    }

    //Overridden in order to avoid duplicate entries
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
        //To select wheather to Edit or Delete the record
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

    //Method to Display Address Book Details based on city name or state name
    public void display()
    {
        boolean check=true;
        System.out.println("Enter City Name or State Name");
        String place=scan.next();
        System.out.println("ADDRESS BOOK DETAILS : ");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        for ( AddressBookDetails details : addressBookList)
        {
            if(( details.city.equals(place)) || (details.state.equals(place)))
            {
                check=false;
                System.out.println(details);
            }
        }
        if (check==true)
        {
            System.out.println("Record does not exist");
        }
    }

    //Method to Sort Address Book details by name, zipcode, city and state
    public void sortBy()
    {
        System.out.println("Select the type of sorting you want to do. \n1: Sort by name \n2: Sort by ZipCode \n3: Sort by City \n4: Sort by State");
        int select=scan.nextInt();
        switch (select)
        {
            case 1:
                addressBookList.sort(Comparator.comparing(AddressBookDetails::hashCode));
                break;
            case 2:
                Collections.sort(addressBookList, new Comparator<AddressBookDetails>(){

                    public int compare(AddressBookDetails bookDetails1,AddressBookDetails bookDetails2){
                        return bookDetails2.zipCode-bookDetails1.zipCode;
                    }
                }.reversed());
                break;
            case 3:
                Collections.sort(addressBookList, new Comparator<AddressBookDetails>(){

                    public int compare(AddressBookDetails bookDetails1,AddressBookDetails bookDetails2){
                        return bookDetails2.city.compareTo(bookDetails1.city);
                    }
                }.reversed());
                break;
            case 4:
                Collections.sort(addressBookList, new Comparator<AddressBookDetails>(){

                    public int compare(AddressBookDetails bookDetails1,AddressBookDetails bookDetails2){
                        return bookDetails2.state.compareTo(bookDetails1.state);
                    }
                }.reversed());
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        System.out.println("ADDRESS BOOK DETAILS : ");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        Iterator details=addressBookList.iterator();
        while (details.hasNext())
        {
            System.out.println(details.next());
        }
    }

}
