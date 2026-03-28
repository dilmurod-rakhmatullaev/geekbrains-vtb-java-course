package com.geekbrains.multithreading.homework;
/*
Все участники должны стартовать одновременно, несмотря на разное время подготовки. В тоннель не
может одновременно заехать больше половины участников (условность).
Попробуйте все это синхронизировать.
Когда все завершат гонку, нужно выдать объявление об окончании.
Можно корректировать классы (в том числе конструктор машин) и добавлять объекты классов из
пакета util.concurrent.
Обязательно необходимо объявить победителя гонки, он должен быть только один, и это участник
первым закончивший последний этап
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(CARS_COUNT);
        CountDownLatch finishLatch = new CountDownLatch(CARS_COUNT);
        AtomicBoolean winnerFlag = new AtomicBoolean(false);

        System.out.println("IMPORTANT ANNOUNCEMENT >>> Preparation!!!");

        Race race = new Race(
                new Road(60),
                new Tunnel(CARS_COUNT),
                new Road(40)
        );

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            int speed = 20 + (int) (Math.random() * 10);
            cars[i] = new Car(race, speed, startLatch, finishLatch, winnerFlag);
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        startLatch.await();
        System.out.println("IMPORTANT ANNOUNCEMENT >>> Race started!!!");

        finishLatch.await();
        System.out.println("IMPORTANT ANNOUNCEMENT >>> Race finished!!!");
    }
}