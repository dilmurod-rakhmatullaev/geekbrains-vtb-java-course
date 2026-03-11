package com.geekbrains.lesson1.animals;

public class Cat extends Animal {
    int tailLength;
    public Cat(String name, String colour, int age, int tailLength) {
        super(name, colour, age);
        this.tailLength = tailLength;
    }

    @Override
    public void voice() {
        System.out.println(name + ": meows");
    }

    public void catMethod() {
        System.out.println("catMethod");
    }

    @Override
    public String toString() {
        return "CAT [" + name + " " + colour + " " + age + " " + tailLength + "]";
    }
}
