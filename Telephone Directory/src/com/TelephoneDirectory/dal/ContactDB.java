package com.TelephoneDirectory.dal;

import com.TelephoneDirectory.model.Contact;
import java.util.*;

import java.io.*;
import java.util.ArrayList;

public class ContactDB {
    public static final String PATH = "C:\\Users\\USER\\Desktop\\IT\\module2\\Test_Module_2\\Telephone Directory\\data\\contacts.csv";
    public ArrayList<Contact> contacts;
    public ContactDB(){
        contacts = new ArrayList<>();
    }

    public void writeFile() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (Contact contact : contacts) {
            bufferedWriter.write(contact.toStringCSV());
        }
        bufferedWriter.close();
    }

    public void readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] arr = line.split(",");
            add(new Contact(arr[0], arr[1], arr[2], arr[3], arr[4],arr[5], arr[6]));
        }
        bufferedReader.close();
    }

    public void add(Contact contact){
        contacts.add(contact);
    }

    public Contact findByPhoneNumber(String phoneNumber){
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) return contact;
        }
        return null;
    }

    public Contact findByEmail(String email){
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(email)) return contact;
        }
        return null;
    }

    public void printListContact(){
        if (contacts.size() == 0) {
            System.out.println("List is empty.");
        } else
            for (Contact contact : contacts) {
                System.out.println(contact.toString());
            }
    }

    public void printList(){
        if (contacts.size() == 0){
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                if (i % 5 == 0 && i != 0) {
                    System.out.println("Press enter to continue, any key to exit print list.");
                    String choice = new Scanner(System.in).nextLine();
                    if (choice.equals("")) System.out.println(contacts.get(i).toString());
                    else {
                        System.out.println("Return to main menu!");
                        return;
                    }
                } else System.out.println(contacts.get(i).toString());
            }
        }
    }

    public void editPhoneNumber(String phoneNumber, String newPhoneNumber){
        findByPhoneNumber(phoneNumber).setPhoneNumber(newPhoneNumber);
    }

    public void editGroup (String phoneNumber, String group){
        findByPhoneNumber(phoneNumber).setGroup(group);
    }

    public void editName(String phoneNumber, String newName){
        findByPhoneNumber(phoneNumber).setName(newName);
    }

    public void editGender(String phoneNumber, String newGender){
        findByPhoneNumber(phoneNumber).setGender(newGender);
    }

    public void editAddress(String phoneNumber, String newAddress){
        findByPhoneNumber(phoneNumber).setAddress(newAddress);
    }

    public void editDob(String phoneNumber, String newDob){
        findByPhoneNumber(phoneNumber).setDob(newDob);
    }

    public void editEmail(String phoneNumber, String newEmail){
        findByPhoneNumber(phoneNumber).setEmail(newEmail);
    }

    public boolean remove(String phoneNumber){
        return contacts.remove(findByPhoneNumber(phoneNumber));
    }

    public boolean fuzzySearch(String s1, String s2){
        int i,j,errTimes;
        String[] s1Arr = s1.split("");
        String[] s2Arr = s2.split("");
        int err = (int) Math.round(s2.length() * 0.3);
        if (s1.length() < (s2.length() - err) || s1.length() > (s2.length() + err)){
            return false;
        }
        i = j = errTimes = 0;
        while (i < s1.length() && j < s1.length()){
            if (!s2Arr[i].equals(s1Arr[j])){
                errTimes++;
                for (int k = 1; k < err ; k++) {
                    if (i + k < s2.length() && s2Arr[i + k].equals(s1Arr[j])){
                        i+=k;
                        break;
                    } else if (j + k < s1.length() && s2Arr[i].equals(s1Arr[j + k])){
                        j += k;
                        break;
                    }
                }
            }
            i++;
            j++;
        }
        errTimes += s2.length() - i + s1.length() - j;
        return errTimes < err;
    }

    public void findWithPhoneNumber(String phoneNumber){
        for (Contact contact : contacts) {
            if (fuzzySearch(phoneNumber, contact.getPhoneNumber())) {
                System.out.println(contact.toString());
            }
        }
    }

    public void findWithName(String name){
        for (Contact contact : contacts) {
            if (fuzzySearch(name, contact.getName())) {
                System.out.println(contact.toString());
            }
        }
    }

}
