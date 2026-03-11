package com.geekbrains.lesson1.animals;

public class Animal {
    protected String name;
    protected String colour;
    protected int age;


    public Animal() {
        this.name = "Unknown";
        this.colour = "Unknown";
        this.age = 1;
    }

    /*
        public Animal (String name) {
            this.name = name;
            this.color = "Unknown";
            this.age = 1;
        }
    */
    public Animal(String name, String colour, int age) {
        this.name = name;
        this.colour = colour;
        this.age = age;
    }


    public void voice() {
        System.out.println(name + "meow");
    }

    public void info() {
        System.out.println(name + " " + colour + " " + age);
    }
}
