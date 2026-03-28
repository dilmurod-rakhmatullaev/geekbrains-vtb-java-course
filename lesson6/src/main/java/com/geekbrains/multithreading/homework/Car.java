package com.geekbrains.multithreading.homework;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int carsCount = 0;

    private Race race;
    private int speed;
    private String name;

    private CountDownLatch startLatch;
    private CountDownLatch finishLatch;
    private AtomicBoolean winnerFlag;

    public Car(Race race, int speed, CountDownLatch startLatch,
               CountDownLatch finishLatch, AtomicBoolean winnerFlag) {
        this.race = race;
        this.speed = speed;
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
        this.winnerFlag = winnerFlag;

        carsCount++;
        this.name = "Participant #" + carsCount;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " is preparing");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " is ready");

            startLatch.countDown();
            startLatch.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }

            if (winnerFlag.compareAndSet(false, true)) {
                System.out.println(this.name + " - WINNER!!!");
            }

            finishLatch.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}