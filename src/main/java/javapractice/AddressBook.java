package javapractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.ConfigurationFactoryData;

public class AddressBook {
    // List of contacts in a addressbook
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    private HashMap<String, ArrayList<Contact>> cityDirectory = new HashMap<>();
    private HashMap<String, ArrayList<Contact>> stateDirectory = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    // create a contact in address book
    public Contact createContact() {
        // enters all the details of the contact
        log.info("-------- Enter the details of contact ---------");
        log.info("Enter The first name: ");
        String firstName = sc.nextLine();
        log.info("Enter the last name: ");
        String lastName = sc.nextLine();
        log.info("Enter the Address: ");
        String address = sc.nextLine();
        log.info("Enter the city: ");
        String city = sc.nextLine();
        log.info("Enter the state: ");
        String state = sc.nextLine();
        log.info("Enter the zip code: ");
        int zip = sc.nextInt();
        log.info("Enter the phone number: ");
        long phoneNumber = sc.nextLong();
        sc.nextLine();
        log.info("Enter the email: ");
        String email = sc.nextLine();

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);// craetes new
                                                                                                          // contact
                                                                                                          // with
                                                                                                          // contact
                                                                                                          // constructor
        if (!checkDuplicacy(contact)) {
            contacts.add(contact);
            if (cityDirectory.containsKey(city)) {
                ArrayList<Contact> cityPersonList = cityDirectory.get(city);
                cityPersonList.add(contact);
            } else {
                ArrayList<Contact> cityPersonList = new ArrayList<>();
                cityPersonList.add(contact);
                cityDirectory.put(city, cityPersonList);
            }

            if (stateDirectory.containsKey(state)) {
                ArrayList<Contact> statePersonList = stateDirectory.get(state);
                statePersonList.add(contact);
            } else {
                ArrayList<Contact> statePersonList = new ArrayList<>();
                statePersonList.add(contact);
                stateDirectory.put(state, statePersonList);
            }

            log.info("A new contact has been created in the address book");
            log.info("first name: " + contact.getFirstName() + "\nlast name: " + contact.getLastName() + "\naddress: "
                    + contact.getAddress() + "\ncity: " + contact.getCity() + "\nstate: " + contact.getZip()
                    + "\nphone number: " + contact.getPhoneNumber() + "\nEmail: " + contact.getEmail());// prints newly
                                                                                                        // added contact
            return contact;
        }
        log.info("The provided contact already exists");
        return null;
    }

    /*
     * @param - contact
     * 
     * @describe method to check uniqueness of contact by name in contact list
     * 
     * @returns ubniqueness - boolean value
     */
    public boolean checkDuplicacy(Contact contact) {
        boolean[] duplicate = new boolean[1];
        Contact[] contactArr = new Contact[1];
        duplicate[0] = false;
        contactArr[0] = contact;

        contacts.forEach(currentContact -> {
            if (currentContact.equals(contactArr[0])) {
                duplicate[0] = true;
            }
        });

        return duplicate[0];
    }

    // search a contact in address book with the name of the contact
    public Contact searchContact(String name) {
        Contact matchedContact = null;
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            String currentName = contact.getFirstName() + " " + contact.getLastName();
            if (currentName.equals(name)) {
                matchedContact = contact;
                break;
            }
        }
        // searches for the contact with name if found returns the contact object or
        // null
        return matchedContact;
    }

    // edit a contact with the name provided.
    public void editContact(String name) {
        Contact contact = searchContact(name);// searches for the contact in the address book first
        if (contact != null) {// if there is contact the asks for the relevant field to be edited and asks for
                              // the field to be updated
            log.info("1.Change the first name");
            log.info("2.Change the last name");
            log.info("3.Change the address");
            log.info("4.Change the city");
            log.info("5.Change the phone number");
            log.info("6.Change the  state");
            log.info("7.Change the  zip code");
            log.info("8.Change the email");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {

                case 1:
                    log.info("Enter The first name: ");
                    String firstName = sc.nextLine();
                    log.info("changing first name");
                    contact.setFirstName(firstName);
                    break;
                case 2:
                    log.info("Enter the last name: ");
                    String lastName = sc.nextLine();
                    log.info("changing last name");
                    contact.setLastName(lastName);
                    break;
                case 3:
                    log.info("Enter the Address: ");
                    String address = sc.nextLine();
                    log.info("changing address");
                    contact.setAddress(address);
                    break;
                case 4:
                    log.info("Enter the city: ");
                    String city = sc.nextLine();
                    log.info("changing city");
                    contact.setCity(city);
                    break;
                case 5:
                    log.info("Enter the phone number: ");
                    Long phoneNumber = sc.nextLong();
                    log.info("changing phone number");
                    contact.setPhoneNumber(phoneNumber);
                    break;
                case 6:
                    log.info("Enter the state: ");
                    String state = sc.nextLine();
                    log.info("changing state");
                    contact.setState(state);
                    break;
                case 7:
                    log.info("Enter the zip code: ");
                    int zip = sc.nextInt();
                    log.info("changing zip code");
                    contact.setZip(zip);
                    break;
                case 8:
                    log.info("Enter the email");
                    String email = sc.nextLine();
                    log.info("changing email");
                    contact.setEmail(email);
                    break;
                default:
                    break;

            }
            log.info("contact edited successfully");

            log.info("first name: " + contact.getFirstName() + "\nlast name: " + contact.getLastName() + "\naddress: "
                    + contact.getAddress() + "\ncity: " + contact.getCity() + "\nstate: " + contact.getZip()
                    + "\nphone number: " + contact.getPhoneNumber() + "\nEmail: " + contact.getEmail());
        } else {
            log.info("contact does not exist");
        }

    }

    // delete a contact with nme of the contact
    void deleteContact(String name) {

        Contact contact = searchContact(name);
        if (contact != null) {// if contact does exist then contact is removed from contcts arraylist
            int index = contacts.indexOf(contact);// gets the index of the contact in contacts array list
            Contact deletedContact = contacts.remove(index);// removes contact
            if (deletedContact.equals(contact)) {
                log.info("contact was deleted succesfully");
            }
        } else {
            log.info("contact does not exist");
        }

    }

    // add multiple contacts in the address book
    void addMultipleContacts() {
        String answer = "Y";
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        // Asks the user if you want to create anotehr contact.if answer is yes .new
        // contact is created by calling the create contact method which creates
        // invidual contact
        while (answer.equals("Y") || answer.equals("Yes") || answer.equals("yes") || answer.equals("y")
                || answer.equals("yes")) {
            Contact contact = createContact();
            contacts.add(contact);
            log.info("do you want to create another contact(Y/N)");
            answer = sc.nextLine();
        }

    }

    /*
     * @param location-String doesn't matter city or state,persons
     * -ArrayList<Contact>
     * 
     * @describe prints all the people in the require city or stae
     */
    public void getPersonsByCityState(ArrayList<Contact> persons, String locationName) {
        contacts.stream().filter(contact -> {

            return (contact.getCity().equals(locationName) || contact.getState().equals(locationName));
        }).forEach(contact -> {
            persons.add(contact);
        });
    }

    /*
     * @describe view persons in a city or state
     * 
     * @param-location name String,location type-city/state String
     */
    public void getPersonsByLocationDictionary(String locationName, String locationType) {
        if (locationType == "city") {
            ArrayList<Contact> cityPersonList = cityDirectory.get(locationName);
            log.info("persons in the city " + locationName + " are: ");
            cityPersonList.forEach(person -> {
                log.info("first name: " + person.getFirstName() + "\nlast name: " + person.getLastName() + "\naddress: "
                        + person.getAddress() + "\ncity: " + person.getCity() + "\nstate: " + person.getZip()
                        + "\nphone number: " + person.getPhoneNumber() + "\nEmail: " + person.getEmail());

            });
        } else {
            ArrayList<Contact> statePersonList = stateDirectory.get(locationName);
            log.info("person in the State " + locationName + " are: ");
            statePersonList.forEach(person -> {
                log.info("first name: " + person.getFirstName() + "\nlast name: " + person.getLastName() + "\naddress: "
                        + person.getAddress() + "\ncity: " + person.getCity() + "\nstate: " + person.getZip()
                        + "\nphone number: " + person.getPhoneNumber() + "\nEmail: " + person.getEmail());

            });
        }
    }

    /*
     * @describe count persons in a city or state
     * 
     * @param-location name String,location type-city/state String
     */
    private void countpersonByCityState(String locationName, String locationType) {
        if (locationType == "city") {
            ArrayList<Contact> cityPersonList = cityDirectory.get(locationName);
            log.info("number of persons in the city " + locationName + " are: " + cityPersonList.stream().count());

        } else {
            ArrayList<Contact> statePersonList = stateDirectory.get(locationName);
            log.info("person in the State " + locationName + " are: " + statePersonList.stream().count());

        }
    }

    /*
     * @describe sort the address book by first name and print the address book
     * 
     */
    private void sortByName() {
        contacts.stream()
                .sorted(Comparator.comparing(Contact::getFirstName))// use streams and comparator
                .forEach(contact -> {
                    log.info(contact.toString());
                });
        log.info("printing the sorted address book by name");
    }

    /*
     * @describe sort the address book by city/state/zip code and print the address
     * book
     * 
     */
    private void sortByCityStateZip() {
        log.info("Sort by: \n 1.city\n2.state\3.ZIP\nenter your choice: ");
        int sortBy = sc.nextInt();
        sc.nextLine();

        switch (sortBy) {
            case 1:
                log.info("printing the sorted address book by city");
                contacts.stream()
                        .sorted(Comparator.comparing(Contact::getCity))// use streams and comparator
                        .forEach(contact -> {
                            log.info(contact.toString());
                        });
                break;
            case 2:
                log.info("printing the sorted address book by state");
                contacts.stream()
                        .sorted(Comparator.comparing(Contact::getState))// use streams and comparator
                        .forEach(contact -> {
                            log.info(contact.toString());
                        });
                break;
            case 3:
                log.info("printing the sorted address book by ZIP");
                contacts.stream()
                        .sorted(Comparator.comparing(Contact::getZip))// use streams and comparator
                        .forEach(contact -> {
                            log.info(contact.toString());
                        });
                break;
            default:
                log.info("enter a valid option");
        }

    }

    // print the address book
    void printAddressBook() {
        if (contacts.size() > 0) {// iterate through contacts list and print he details of each contact if there
                                  // are any contacts
            for (int i = 0; i < contacts.size(); i++)
                log.info("first name: " + contacts.get(i).getFirstName() + "\nlast name: "
                        + contacts.get(i).getLastName()
                        + "\naddress: " + contacts.get(i).getAddress() + "\ncity: " + contacts.get(i).getCity()
                        + "\nstate: " + contacts.get(i).getZip() + "\nphone number: " + contacts.get(i).getPhoneNumber()
                        + "\nEmail: " + contacts.get(i).getEmail());
        } else {
            log.info("Address book is empty");// else print empty address book
        }

    }

    // program starts from here .asks for option from the user to perform a task
    public void startProgram() {
        while (true) {
            String name, city, state;
            log.info("1.Create a new contact in the address book");
            log.info("2.Edit a contact");
            log.info("3.Delete a contact");
            log.info("4.Create multiple contacts");
            log.info("5.Print the address book");
            log.info("6.View Persons by city");
            log.info("7.View Persons by state");
            log.info("8.View count of Persons by city");
            log.info("9.View count of Persons by state");
            log.info("10.Sort addressbook by name");
            log.info("11.Sort by city/state/zip");
            log.info("12.Close Address Book");
            log.info("enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    createContact();
                    break;
                case 2:
                    log.info("enter the name of the contact to edit:");
                    name = sc.nextLine();
                    editContact(name);
                    break;
                case 3:
                    log.info("enter the name of the contact to delete: ");
                    name = sc.nextLine();
                    deleteContact(name);
                    break;
                case 4:
                    log.info("Add multiple contacts");
                    addMultipleContacts();
                    break;
                case 5:
                    log.info("Printing the address book");
                    printAddressBook();
                    break;
                case 6:
                    log.info("enter the city: ");
                    city = sc.nextLine();
                    getPersonsByLocationDictionary(city, "city");
                    break;
                case 7:
                    log.info("enter the state: ");
                    state = sc.nextLine();
                    getPersonsByLocationDictionary(state, "state");
                    break;
                case 8:
                    log.info("enter the state: ");
                    city = sc.nextLine();
                    countpersonByCityState(city, "city");
                    break;
                case 9:
                    log.info("enter the state: ");
                    state = sc.nextLine();
                    countpersonByCityState(state, "state");
                    break;
                case 10:
                    log.info("sorting address book by name: ");
                    sortByName();
                    break;
                case 11:
                    sortByCityStateZip();
                    break;
                case 12:
                    log.info("Closing address book......");
                    return;
                default:
                    log.info("enter one of the options above");
                    break;
            }

        }

    }

    private static final Logger log = LogManager.getLogger(AddressBook.class);

    public static void main(String[] args) {

        log.info("Welcome to Address Book Program");
        AddressBook addressBook = new AddressBook();
        addressBook.startProgram();

    }
}
