package com.geekbrains.lesson5.collections2;

public class Homework2 {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ronaldo", "+7-999-123-45-67");
        phoneBook.add("Messi", "+7-999-234-56-78");
        phoneBook.add("Ronaldo", "+1-989-123-45-67");
        phoneBook.add("Zidane", "+7-999-345-67-89");
        phoneBook.add("Muller", "+7-999-456-78-90");
        phoneBook.add("Ronaldo", "+4-909-123-45-67");
        phoneBook.add("Messi", "+1-909-234-56-78");
        phoneBook.add("Henry", "+7-999-567-89-01");

        System.out.println("Ronaldo's phone number(s): " + phoneBook.get("Ronaldo"));
        System.out.println("Zidane's phone number(s): " + phoneBook.get("Zidane"));
        System.out.println("Messi's phone number(s): " + phoneBook.get("Messi"));
        System.out.println("Rooney's phone numbers (absent in phonebook): " + phoneBook.get("Rooney"));


        System.out.println("\nAll phonebook:");
        phoneBook.printAll();
    }
}
