package com.geekbrains.lesson2;

public class Cat implements RunnableAndJumpable {
    private String name;
    private int maxRunDistance;
    private int maxJumpHeight;

    public Cat(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(name + " ran " + distance + " metres");
            return true;
        } else {
            System.out.println(name + " couldn't run " + distance + " metres and finished hurdling");
            return false;
        }
    }

    @Override
    public boolean jump(int height) {
        if (height < maxJumpHeight) {
            System.out.println(name + " jumped " + height + " cm");
            return true;
        } else {
            System.out.println(name + " couldn't jump " + height + "cm and finished hurdling");
            return false;
        }
    }
}
