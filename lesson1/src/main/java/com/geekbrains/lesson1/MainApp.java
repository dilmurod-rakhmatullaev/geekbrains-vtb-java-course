package com.geekbrains.lesson1;

import com.geekbrains.lesson1.animals.Animal;
import com.geekbrains.lesson1.animals.Cat;
import com.geekbrains.lesson1.animals.Dog;

public class MainApp {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Thomas", "white", 2, 15);
        Cat cat2 = new Cat("Mario", "red", 3, 13);

        Dog dog1 = new Dog("Rex", "brown", 5);

        cat1.info();
        cat2.info();

        cat1.voice();
        dog1.voice();

        Animal[] catsAndDogs = {
                new Cat("Charlie", "black", 4, 16),
                new Dog("Bella", "grey", 4)
        };

        for (Animal o : catsAndDogs) {
            o.voice();
        }

        Animal wildCat = new Dog("Lucy", "pink", 8);
        if (wildCat instanceof Cat) {
            ((Cat) wildCat).catMethod();
        } else {
            System.out.println("not cat");
        }

        Object obj = new Cat("Justin", "red", 7, 20);
        System.out.println(obj);

        Box box1 = new Box("Brown", 5);
        Box box2 = new Box("Brown", 5);

        System.out.println(box1 == box2);
        System.out.println(box1.equals(box2));
    }
}
