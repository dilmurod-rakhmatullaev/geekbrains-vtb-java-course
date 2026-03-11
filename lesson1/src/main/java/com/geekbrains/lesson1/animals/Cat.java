package com.geekbrains.lesson1.animals;

public class Cat extends Animal {
    int tailLength;
    public Cat(String name, String colour, int age, int tailLength) {
        super(name, colour, age);
        this.tailLength = tailLength;
    }
}
