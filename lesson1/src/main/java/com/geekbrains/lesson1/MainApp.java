package com.geekbrains.lesson1;

import com.geekbrains.lesson1.animals.Cat;

public class MainApp {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Thomas", "white", 2, 15);
        Cat cat2 = new Cat("Mario", "red", 3, 13);

        cat1.info();
        cat2.info();


    }
}
