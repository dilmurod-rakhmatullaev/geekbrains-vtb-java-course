package com.geekbrains.lesson1.animals;

public class Shark extends Animal {
    private static final int SWIM_RANGE_MAX = 27_600_000;
    private static int sharksCount = 0;

    public Shark(String name) {
        super(name);
        sharksCount++;
    }

    public static int getSharksCount() {
        return sharksCount;
    }

    @Override
    public void run(int runRange) {
        System.out.println("Sharks can't run at all");
    }

    @Override
    public void swim(int swimRange) {
        if (swimRange <= SWIM_RANGE_MAX) {
            System.out.println(getName() + " swam " + swimRange + " metres");
        } else {
            System.out.println(getName() + " can't swim so far");
        }
    }
}
