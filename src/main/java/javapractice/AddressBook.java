package javapractice;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    Scanner sc = new Scanner(System.in);

    public Contact createContact() {

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

        Contact contact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        contacts.add(contact);
        log.info("A new contact has been created in the address book");
        log.info("first name: " + contact.getFirstName() + "\nlast name: " + contact.getLastName() + "\naddress: "
                + contact.getAddress() + "\ncity: " + contact.getCity() + "\nstate: " + contact.getZip()
                + "\nphone number: " + contact.getPhoneNumber() + "\nEmail: " + contact.getEmail());
        return contact;
    }

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
        return matchedContact;
    }

    public void editContact(String name) {
        Contact contact = searchContact(name);
        if (contact != null) {
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

    void deleteContact(String name) {

        Contact contact = searchContact(name);
        if (contact != null) {
            int index = contacts.indexOf(contact);
            Contact deletedContact = contacts.remove(index);
            if (deletedContact.equals(contact)) {
                log.info("contact was deleted succesfully");
            }
        } else {
            log.info("contact does not exist");
        }

    }

    void addMultipleContacts() {
        String answer = "Y";
        ArrayList<Contact> contacts=new ArrayList<Contact>();
        while (answer.equals("Y") || answer.equals("Yes") || answer.equals("yes") || answer.equals("y") || answer.equals("yes")) {
            Contact contact=createContact();
            contacts.add(contact);
            log.info("do you want to create another contact(Y/N)");
            answer = sc.nextLine();
        }
       

    }

    void printAddressBook() {
        if (contacts.size() > 0) {
            for (int i = 0; i < contacts.size(); i++)
                log.info("first name: " + contacts.get(i).getFirstName() + "\nlast name: "
                        + contacts.get(i).getLastName()
                        + "\naddress: " + contacts.get(i).getAddress() + "\ncity: " + contacts.get(i).getCity()
                        + "\nstate: " + contacts.get(i).getZip() + "\nphone number: " + contacts.get(i).getPhoneNumber()
                        + "\nEmail: " + contacts.get(i).getEmail());
        } else {
            log.info("Address book is empty");
        }

    }

    public void startProgram() {
        while (true) {
            String name;
            log.info("1.Create a new contact in the address book");
            log.info("2.Edit a contact");
            log.info("3.Delete a contact");
            log.info("4.Create multiple contacts");
            log.info("6.Print the address book");
            log.info("7.Close Address Book");
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
                case 6:
                    log.info("Printing the address book");
                    printAddressBook();
                    break;
                case 7:
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
