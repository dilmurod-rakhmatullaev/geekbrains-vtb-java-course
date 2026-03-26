package com.geekbrains.multithreading.p5_wait_notify;

public class WaitNotifyApp {
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        WaitNotifyApp waitNotifyApp = new WaitNotifyApp();
        new Thread(() -> {
            waitNotifyApp.printA();
        }).start();
        new Thread(() -> {
            waitNotifyApp.printB();
        }).start();
    }
    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        mon.wait();
                    }
                    System.out.println("A");;
                    currentLetter = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        mon.wait();
                    }
                    System.out.println("B");;
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
