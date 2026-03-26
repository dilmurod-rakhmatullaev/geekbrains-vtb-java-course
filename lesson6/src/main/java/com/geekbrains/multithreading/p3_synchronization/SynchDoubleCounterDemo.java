package com.geekbrains.multithreading.p3_synchronization;

public class SynchDoubleCounterDemo {
    public static void main(String[] args) throws InterruptedException {
        final SynchDoubleCounter store = new SynchDoubleCounter();

        Thread applesThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                store.inc1();
                if (i % 2 == 0) {
                    store.dec1();
                }
            }
        });
        Thread pearsThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                store.inc2();
                store.inc2();
                store.dec2();
            }
        });
        applesThread.start();
        pearsThread.start();

        applesThread.join();
        pearsThread.join();

        System.out.println("Apple's amount: " + store.value1());
        System.out.println("Pear's amount: " + store.value2());
    }
}
/*
Apple's amount: 5000
Pear's amount: 10000
 */