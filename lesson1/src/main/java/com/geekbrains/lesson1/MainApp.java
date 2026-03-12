package com.geekbrains.lesson1;

import com.geekbrains.lesson1.animals.Animal;
import com.geekbrains.lesson1.animals.Cat;
import com.geekbrains.lesson1.animals.Dog;
import com.geekbrains.lesson1.animals.Shark;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        List<Animal> animal = new ArrayList<>();
        animal.add(new Cat("Tom"));
        animal.add(new Dog("Rex"));
        animal.add(new Shark("Blahaj"));

        for (Animal one : animal) {
            one.run(150);
            one.swim(5);
        }

        System.out.println("Amount of animals: " + animal.size());
        System.out.println("Amount of Cats: " + Cat.getCatsCount());
        System.out.println("Amount of Dogs: " + Dog.getDogsCount());
        System.out.println("Amount of Sharks: " + Shark.getSharksCount());
    }
}
