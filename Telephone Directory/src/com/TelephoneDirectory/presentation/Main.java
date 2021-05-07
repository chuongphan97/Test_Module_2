package com.TelephoneDirectory.presentation;

import com.TelephoneDirectory.Service.ContactService;
import com.TelephoneDirectory.model.Contact;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static ContactService contactService = new ContactService();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO TELEPHONE DIRECTORY SYSTEM");
        System.out.println("_____________________________________");
        String choice = "";
        while (!choice.equals("8")){
            createMenu();
            System.out.println("Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice){
                case "1":
                    printList();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    edit();
                    break;
                case "4":
                    remove();
                    break;
                case "5":
                    find();
                    break;
                case "6":
                    try {
                        loadFile();
                        break;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                case "7":
                    try {
                        updateFile();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

            }
        }


    }

    public static void createMenu(){
        System.out.println("MENU:");
        System.out.println("\t 1. Print list contact.");
        System.out.println("\t 2. Add new contact.");
        System.out.println("\t 3. Edit contact.");
        System.out.println("\t 4. Remove contact.");
        System.out.println("\t 5. Find contact.");
        System.out.println("\t 6. Load file.");
        System.out.println("\t 7. Update file.");
        System.out.println("\t 8. Exit system.");
    }

    public static void printList(){
        contactService.printList();
        System.out.println("--------------------------");
    }

    public static void add(){
        System.out.println("Enter your phone number: ");
        String phoneNumber = new Scanner(System.in).nextLine();
        while (!checkPhoneNumber(phoneNumber)){
            System.out.println("Please enter phone number again: (phone number: 0xxxxxxxxx)");
            phoneNumber = new Scanner(System.in).nextLine();
        }


        while (contactService.findByPhoneNumber(phoneNumber) != null){
            System.out.println("This phone number already exist, please enter new number!!");
            phoneNumber = new Scanner(System.in).nextLine();
            while (!checkPhoneNumber(phoneNumber)){
                System.out.println("Please enter phone number again: (phone number: 0xxxxxxxxx)");
                phoneNumber = new Scanner(System.in).nextLine();
            }
        }


        System.out.println("Enter group: ");
        String group = new Scanner(System.in).nextLine();
        System.out.println("Enter full name: ");
        String name = new Scanner(System.in).nextLine();
        while (!checkName(name)){
            System.out.println("Please enter your name again!");
            name = new Scanner(System.in).nextLine();
        }
        System.out.println("Enter gender: ");
        String gender = new Scanner(System.in).nextLine();
        System.out.println("Enter address: ");
        String address = new Scanner(System.in).nextLine();
        System.out.println("Enter your birthday: ");
        String dob = new Scanner(System.in).nextLine();
        while (!checkDoB(dob)){
            System.out.println("Please enter birthday again: (birthday: dd/mm/yyyy)");
            dob = new Scanner(System.in).nextLine();
        }
        System.out.println("Enter your email: ");
        String email = new Scanner(System.in).nextLine();
        while (!checkEmail(email)){
            System.out.println("Please enter email again: (email: abc@amail.com)");
            email = new Scanner(System.in).nextLine();
        }

        while (contactService.findByEmail(email) != null){
            System.out.println("This email already exist, please enter new email!!");
            email = new Scanner(System.in).nextLine();
            while (!checkEmail(email)){
                System.out.println("Please enter email again: (email: abc@amail.com)");
                email = new Scanner(System.in).nextLine();
            }
        }

        contactService.add(new Contact(phoneNumber,group,name,gender,address,dob,email));
    }

    public static void edit(){
        System.out.println("Enter your phone number: ");
        String phoneNumber = new Scanner(System.in).nextLine();
        while (contactService.findByPhoneNumber(phoneNumber) == null){
            System.out.println("Your phone number don't exist, please enter again!!");
            phoneNumber = new Scanner(System.in).nextLine();
        }


        String choose = "";
        while (!choose.equals("g")){
            System.out.println("-----------EDIT MENU-----------");
            System.out.println("\t a. Edit group.");
            System.out.println("\t b. Edit name.");
            System.out.println("\t c. Edit gender.");
            System.out.println("\t d. Edit address.");
            System.out.println("\t e. Edit birthday.");
            System.out.println("\t f. Edit email.");
            System.out.println("\t g. Exit.");
            System.out.println("-----------------------------------");
            System.out.println("What is your choose: ");
            choose = new Scanner(System.in).nextLine();
            switch (choose){
                case "a":
                    System.out.println("Enter new group: ");
                    String newGroup = new Scanner(System.in).nextLine();
                    contactService.editGroup(phoneNumber,newGroup);
                    break;
                case "b":
                    System.out.println("Enter new name: ");
                    String newName = new Scanner(System.in).nextLine();
                    contactService.editName(phoneNumber,newName);
                    break;
                case "c":
                    System.out.println("Enter new gender: ");
                    String newGender = new Scanner(System.in).nextLine();
                    contactService.editGender(phoneNumber,newGender);
                    break;
                case "e":
                    System.out.println("Enter new birthday: ");
                    String newDob = new Scanner(System.in).nextLine();
                    while (!checkDoB(newDob)){
                        System.out.println("Please enter birthday again: (birthday: dd/mm/yyyy)");
                        newDob = new Scanner(System.in).nextLine();
                    }
                    contactService.editDob(phoneNumber,newDob);
                    break;
                case "f":
                    System.out.println("Enter new email: ");
                    String newEmail = new Scanner(System.in).nextLine();
                    while (!checkEmail(newEmail)){
                        System.out.println("Please enter email again: (email: abc@amail.com)");
                        newEmail = new Scanner(System.in).nextLine();
                    }
                    while (contactService.findByEmail(newEmail) != null){
                        System.out.println("This email already exist, please enter new email!!");
                        newEmail = new Scanner(System.in).nextLine();
                        while (!checkEmail(newEmail)){
                            System.out.println("Please enter email again: (email: abc@amail.com)");
                            newEmail = new Scanner(System.in).nextLine();
                        }
                    }
                    contactService.editEmail(phoneNumber,newEmail);
                    break;
            }
        }
        System.out.println("Exit edit system!");

    }

    public static void remove(){
        System.out.println("Enter phone number: ");
        String phoneNumber = new Scanner(System.in).nextLine();
        contactService.remove(phoneNumber);
    }

    public static void find(){
        String choose = "";
        while (!choose.equals("c")){
            System.out.println("FIND MENU: ");
            System.out.println("\t a. Find with phone number.");
            System.out.println("\t b. Find with name.");
            System.out.println("\t c. Exit.");
            System.out.println("-------------------------------");
            System.out.println("Enter your choice: ");
            choose = new Scanner(System.in).nextLine();
            switch (choose){
                case "a":
                    System.out.println("Enter phone number: ");
                    String phoneNumber = new Scanner(System.in).nextLine();
                    contactService.findWithPhoneNumber(phoneNumber);
                    break;
                case "b":
                    System.out.println("Enter name: ");
                    String name = new Scanner(System.in).nextLine();
                    contactService.findWithName(name);
                    break;
            }
        }
        System.out.println("Exit find system!");
    }

    public static void loadFile() throws IOException {
        System.out.println("Do you want load file?");
        System.out.println("Press Y to continue, N to exit.");
        String choose = new Scanner(System.in).nextLine();
        if (choose.equals("Y")){
            contactService.loadFile();
        }
    }

    public static void updateFile() throws IOException {

        System.out.println("Do you want load file?");
        System.out.println("Press Y to continue, N to exit.");
        String choose = new Scanner(System.in).nextLine();
        if (choose.equals("Y")){
            contactService.updateFile();
        }
    }

    public static boolean checkName(String name){
        String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean checkPhoneNumber(String phoneNumber){
        String regex = "^0[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean checkEmail(String email){
        String regex = "^[a-zA-Z]+[a-zA-Z0-9]*@\\w+mail.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkDoB(String dob){
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dob);
        return matcher.matches();
    }

}
