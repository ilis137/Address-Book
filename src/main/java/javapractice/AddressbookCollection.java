package javapractice;

import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressbookCollection {
  HashMap<String, AddressBook> directories = new HashMap<String, AddressBook>();
  private static final Logger log = LogManager.getLogger(AddressBook.class);
  Scanner sc = new Scanner(System.in);


  // open a address book with name provided as paramater.it searches for the address book and starts it.
  void openAddressBook(String name) {
    AddressBook addressbook = directories.get(name);

    if (addressbook.equals(null)) {
      log.info("Address book does not exist");
    } else {
      log.info("opening address book" + name);
      addressbook.startProgram();
    }
  }

  // Add adddress book accepts a name for the address book and instantiates new address book andadds them to ad ictionary
  void AddAddressBook() {

    log.info("Enter address book name");
    String name=sc.nextLine();
    AddressBook addressBook=new AddressBook();

    this.directories.put(name,addressBook);
    log.info("Address book"+ name + "created");
    log.info("Do you want to open the address book(Y/N)");
    String ans=sc.nextLine();
   
    if(ans.equals("Y")||ans.equals("y")){
      this.openAddressBook(name);
    }else{
      return;
    }
  }
//first funtion to be called .asks for the option from the user.
  void startProgram() {

    while (true) {
      String name;
      log.info("1.Open a Address Book");
      log.info("2.Create a Address Book");
      log.info("3.Exit");
      log.info("enter your choice: ");

      int choice = sc.nextInt();
      sc.nextLine();
      switch (choice) {
        case 1:
          log.info("Enter the name of the Address book:");
          name = sc.nextLine();
          openAddressBook(name);
          break;
        case 2:
          AddAddressBook();
          break;
        case 3:
          log.info("Exiting the program...........");
          return;
        default:
          log.info("Enter a valid option");
          break;

      }
    }
  }

  public static void main(String[] args) {
    AddressbookCollection obj = new AddressbookCollection();
    obj.startProgram();
  }
}
