package com.geekbrains.multithreading.p1_thread_creation_and_base;

public class RunnableExampleClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new RunnableExampleClass());
        t.start();
    }
}
/*
0
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
 */