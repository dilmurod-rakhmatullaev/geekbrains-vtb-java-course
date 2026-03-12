package com.geekbrains.lesson1.animals;

public class Cat extends Animal {
    private static final int RUN_RANGE_MAX = 200;
    private static int catsCount = 0;
    public Cat(String name) {
        super(name);
        catsCount++;
    }
    public static int getCatsCount() {
        return catsCount;
    }

    @Override
    public void run(int runRange) {
        if (runRange <= RUN_RANGE_MAX) {
            System.out.println(getName() + " ran " + runRange + " metres");
        } else {
            System.out.println(getName() + " can't run that far");
        }
    }

    @Override
    public void swim(int swimRange) {
        System.out.println(getName() + " can't swim at all");
    }
}
