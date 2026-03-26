package com.geekbrains.multithreading.p1_thread_creation_and_base;

public class ThreadExampleClass extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread-" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new ThreadExampleClass();
        t.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main-" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
thread-0
main-0
thread-1
main-1
thread-2
main-2
main-3
thread-3
main-4
thread-4
main-5
thread-5
main-6
thread-6
main-7
thread-7
main-8
thread-8
main-9
thread-9
 */