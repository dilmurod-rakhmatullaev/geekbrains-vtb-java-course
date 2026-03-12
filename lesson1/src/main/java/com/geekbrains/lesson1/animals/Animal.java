package com.geekbrains.lesson1.animals;

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void run(int runRange);

    public abstract void swim(int swimRange);
}
