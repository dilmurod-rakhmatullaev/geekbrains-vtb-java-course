package com.geekbrains.lesson1;

import com.geekbrains.lesson1.animals.Animal;

public class Wolf extends Animal {
    public Wolf(String name, String colour, int age) {
        super(name, colour, age);
    }

    public void info() {
        System.out.println(name);
    }
}

