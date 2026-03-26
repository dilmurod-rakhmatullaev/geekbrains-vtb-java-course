package com.geekbrains.multithreading.p3_synchronization;

public class SynchMethodsApp {
    public static void main(String[] args) {
        SynchMethodsApp e1 = new SynchMethodsApp();
        SynchMethodsApp e2 = new SynchMethodsApp();
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method2()).start();
        new Thread(() -> e1.method3()).start();
        new Thread(() -> e2.method2()).start();
        new Thread(() -> e2.method1()).start();
        new Thread(() -> e2.method3()).start();
    }

    public synchronized void method1() {
        System.out.println("M1-Start");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M1-End");
    }

    public synchronized void method2() {
        System.out.println("M2-Start");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2-End");
    }
    public void method3() {
        System.out.println("M3-Start");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M3-End");
    }
}
