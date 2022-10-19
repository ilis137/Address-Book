package javapractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressbookCollection {
  HashMap<String, AddressBook> directories = new HashMap<String, AddressBook>();
  private static final Logger log = LogManager.getLogger(AddressBook.class);
  Scanner sc = new Scanner(System.in);

  // open a address book with name provided as paramater.it searches for the
  // address book and starts it.
  void openAddressBook(String name) {
    AddressBook addressbook = directories.get(name);

    if (addressbook.equals(null)) {
      log.info("Address book does not exist");
    } else {
      log.info("opening address book" + name);
      addressbook.startProgram();
    }
  }

  // Add adddress book accepts a name for the address book and instantiates new
  // address book andadds them to ad ictionary
  void AddAddressBook() {

    log.info("Enter address book name");
    String name = sc.nextLine();
    AddressBook addressBook = new AddressBook();

    this.directories.put(name, addressBook);
    log.info("Address book" + name + "created");
    log.info("Do you want to open the address book(Y/N)");
    String ans = sc.nextLine();

    if (ans.equals("Y") || ans.equals("y")) {
      this.openAddressBook(name);
    } else {
      return;
    }
  }
  /*
   * @param location-String doesn't matter city or state
   * @describe prints all the people in the require city or stae
   */
  public void getByCityState(String locationName) {
    ArrayList<Contact> persons = new ArrayList<Contact>();
    for (Map.Entry<String, AddressBook> directory : directories.entrySet()) {
      AddressBook addressBook = directory.getValue();
      addressBook.getPersonsByCityState(persons, locationName);
      log.info("Persons in address book "+directory.getKey()+" in "+locationName+" are: ");
      persons.forEach(person->{
        log.info("first name: " + person.getFirstName() + "\nlast name: " + person.getLastName() + "\naddress: "
        + person.getAddress() + "\ncity: " + person.getCity() + "\nstate: " + person.getZip()
        + "\nphone number: " + person.getPhoneNumber() + "\nEmail: " + person.getEmail());//prints newly added contact
      });
    }
  }

  // first funtion to be called .asks for the option from the user.
  void startProgram() {

    while (true) {
      String name,locationName;
      log.info("1.Open a Address Book");
      log.info("2.Create a Address Book");
      log.info("3.Show persons in a city");
      log.info("4.Show persons in a state");
      log.info("4.Exit");
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
          log.info("Enter the city name: ");
          locationName = sc.nextLine();
          getByCityState(locationName);
          break;
        case 4:
        log.info("Enter the state name: ");
        locationName = sc.nextLine();
        getByCityState(locationName);
          break;
        case 5:
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
