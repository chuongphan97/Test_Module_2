package com.TelephoneDirectory.Service;

import com.TelephoneDirectory.dal.ContactDB;
import com.TelephoneDirectory.model.Contact;

import java.io.IOException;

public class ContactService {
    public ContactDB contactDB;
    public ContactService(){
        contactDB = new ContactDB();
    }

    public void printList(){
        System.out.println("List contact: ");
        contactDB.printList();
    }

    public void add(Contact contact){
        contactDB.add(contact);
        System.out.println("Add new contact successfully.");
    }

    public Contact findByPhoneNumber(String phoneNumber){
        return contactDB.findByPhoneNumber(phoneNumber);
    }

    public Contact findByEmail(String email){
        return contactDB.findByEmail(email);
    }


    public void editPhoneNumber(String phoneNumber, String newPhoneNumber){
        contactDB.editPhoneNumber(phoneNumber,newPhoneNumber);
        System.out.println("Edit phone number successfully.");
    }

    public void editGroup(String phoneNumber, String newGroup){
        contactDB.editGroup(phoneNumber,newGroup);
        System.out.println("Edit group successfully.");
    }

    public void editName(String phoneNumber, String newName){
        contactDB.editName(phoneNumber,newName);
        System.out.println("Edit name successfully.");
    }

    public void editGender(String phoneNumber, String newGender){
        contactDB.editGender(phoneNumber,newGender);
        System.out.println("Edit gender successfully.");
    }

    public void editAddress(String phoneNumber, String newAddress){
        contactDB.editAddress(phoneNumber, newAddress);
        System.out.println("Edit address successfully.");
    }

    public void editDob(String phoneNumber, String newDob) {
        contactDB.editDob(phoneNumber, newDob);
        System.out.println("Edit birthday successfully.");
    }

    public void editEmail(String phoneNumber, String newEmail){
        contactDB.editEmail(phoneNumber,newEmail);
        System.out.println("Edit email successfully.");
    }

    public boolean remove(String phoneNumber){
        if (contactDB.remove(phoneNumber)) {
            System.out.println("Remove successfully.");
            return true;
        } else {
            System.out.println("Invalid phone number");
            return false;
        }
    }

    public void updateFile() throws IOException {
        contactDB.writeFile();
        System.out.println("Update file successfully.");
    }

    public void loadFile() throws IOException {
        contactDB.readFile();
        System.out.println("Load file successfully.");
    }

    public void findWithPhoneNumber(String phoneNumber){
        contactDB.findWithPhoneNumber(phoneNumber);
    }

    public void findWithName(String name){
        contactDB.findWithName(name);
    }




}
