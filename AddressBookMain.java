public class AddressBookMain
{
	 //Main Method
    public static void main(String[] args)
    {
        System.out.println("Welcome to Address Book Program");
        AddressBookDetails addressBookDetails=new AddressBookDetails("Aniket", "Dakhare","Dhanori", "Pune","Maharashtra",442502,"9978659035");
        addressBookDetails.display();
    }
}
