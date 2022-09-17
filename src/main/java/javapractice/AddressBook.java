package javapractice;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


class Contact{
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int zip;
    private long phoneNumber;
    private String email;


    public Contact(String firstName, String lastName, String address, String city, String state, int zip,
        long phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public int getZip() {
        return zip;
    }


    public void setZip(int zip) {
        this.zip = zip;
    }


    public long getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    
}

public class AddressBook 
{
    private ArrayList<Contact> contacts=new ArrayList<Contact>();
    Scanner sc=new Scanner(System.in);


    public void createContact(){
     
        log.info("-------- Enter the details of contact ---------");
        log.info("Enter The first name: ");
        String firstName=sc.nextLine();
        log.info("Enter the last name: ");
        String lastName=sc.nextLine();
        log.info("Enter the Address: ");
        String address=sc.nextLine();
        log.info("Enter the city: ");
        String  city=sc.nextLine();
        log.info("Enter the state: ");
        String state=sc.nextLine();
        log.info("Enter the zip code: ");
        int zip=sc.nextInt();
        log.info("Enter the phone number: ");
        long phoneNumber=sc.nextLong();
        log.info("Enter the email: ");
        String email=sc.nextLine();
        
      
        Contact contact=new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
        contacts.add(contact);
        log.info("A new contact has been created in the address book");
        log.info("first name: "+contact.getFirstName()+"\nlast name: "+contact.getLastName()+"\naddress: "+contact.getAddress()+"\ncity: "+contact.getCity()+"\nstate: "+contact.getZip()+"\nphone number: "+contact.getPhoneNumber()+"\nEmail: "+contact.getEmail());     
       
    }

    void printAddressBook(){
        for (int i = 0; i < contacts.size(); i++) 
        log.info("first name: "+contacts.get(i).getFirstName()+"\nlast name: "+contacts.get(i).getLastName()+"\naddress: "+contacts.get(i).getAddress()+"\ncity: "+contacts.get(i).getCity()+"\nstate: "+contacts.get(i).getZip()+"\nphone number: "+contacts.get(i).getPhoneNumber()+"\nEmail: "+contacts.get(i).getEmail());           
    }
    public void startProgram(){
        while(true){
            log.info("1.Create a new contact in the address book");
            log.info("6.Print the address book");
            log.info("7.Exit");
            log.info("enter your choice: ");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                createContact();
                break;
                case 6:
                log.info("Printing the address book");
                printAddressBook();
                break;
                case 7:
                log.info("Exiting program......");
                return;
                default:
                log.info("enter one of the options above");
                break;
            }
           
        }
       
    }
    private static final Logger log = LogManager.getLogger(AddressBook.class);
    public static void main( String[] args )
    {
        
        log.info("Welcome to Address Book Program");
        AddressBook addressBook=new AddressBook();
        addressBook.startProgram();
        
    }
}
