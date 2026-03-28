package com.geekbrains.multithreading.homework;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore semaphore;

    public Tunnel(int carsCount) {
        this.length = 80;
        this.description = "Tunnel " + length + " meters";
        this.semaphore = new Semaphore(carsCount / 2);
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " is waiting for stage: " + description);

            semaphore.acquire();

            System.out.println(c.getName() + " started stage: " + description);
            Thread.sleep(length / c.getSpeed() * 1000L);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " finished stage: " + description);
            semaphore.release();
        }
    }
}