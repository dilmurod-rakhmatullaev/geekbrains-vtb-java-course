package com.geekbrains.lesson1.animals;

public class Dog extends Animal {
    private static final int RUN_RANGE_MAX = 500;
    private static final int SWIM_RANGE_MAX = 10;
    private static int dogsCount;
    public Dog(String name) {
        super(name);
        dogsCount++;
    }
    public static int getDogsCount() {
        return dogsCount;
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
        if (swimRange <= SWIM_RANGE_MAX) {
            System.out.println(getName() + " swam " + swimRange + " metres");
        } else {
            System.out.println(getName() + " can't swim that far");
        }
    }
}
