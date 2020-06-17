import java.util.Scanner;

public class AddressBookMain
{
    AddressBookDetails addressBookDetails = new AddressBookDetails();
    Scanner scan = new Scanner(System.in);

    //Method to Select Task
    public void selectTask()
    {
        boolean value=true;
        while(value==true)
        {
            System.out.println("Select and enter the task you want to do \n1: Add details \n2: Display details \n3: Edit details \n4: Exit");
            int num=scan.nextInt();
            switch (num)
            {
                case 1:
                    addressBookDetails.addName();
                    System.out.println("==============================================================================================");
                    break;
                case 2:
                    addressBookDetails.display();
                    System.out.println("==============================================================================================");
                    break;
                case 3:
                    addressBookDetails.editDetails();
                    System.out.println("==============================================================================================");
                    break;
                case 4:
                    value=false;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.println("==============================================================================================");
            }
        }
    }

    //Main Method
    public static void main(String[] args)
    {
        System.out.println("Welcome to Address Book Program");
        AddressBookMain addressBookMain = new AddressBookMain();
        addressBookMain.selectTask();
    }
}
