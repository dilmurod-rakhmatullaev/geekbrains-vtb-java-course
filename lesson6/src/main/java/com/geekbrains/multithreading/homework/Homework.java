package com.geekbrains.multithreading.homework;

public class Homework {
    private static final int SIZE = 10_000_000;
    private static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        Homework hw = new Homework();
        hw.oneThread();
        hw.twoThreads();
    }

    public void oneThread() {
        float[] array1 = new float[SIZE];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = 1;
        }
        long oneThreadStart = System.currentTimeMillis();
        for (int i = 0; i < array1.length; i++) {
            array1[i] = (float) (array1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long oneThreadFinish = System.currentTimeMillis();
        System.out.println("Calculation in array using one thread takes: " + (oneThreadFinish - oneThreadStart));
    }

    public void twoThreads() {
        float[] array2 = new float[SIZE];
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];

        for (int i = 0; i < array2.length; i++) {
            array2[i] = 1;
        }
        long twoThreadsStart = System.currentTimeMillis();
        System.arraycopy(array2, 0, arr1, 0, HALF);
        System.arraycopy(array2, HALF, arr2, 0, HALF);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < arr2.length; i++) {
                int originalIndex = HALF + i;
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + originalIndex / 5) * Math.cos(0.2f + originalIndex / 5) *
                        Math.cos(0.4f + originalIndex / 2));
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, array2, 0, HALF);
        System.arraycopy(arr2, 0, array2, HALF, HALF);

        long twoThreadsFinish = System.currentTimeMillis();
        System.out.println("Calculation in array using two threads takes: " + (twoThreadsFinish - twoThreadsStart));
    }
}
/*
Calculation in array using one thread takes: 1748
Calculation in array using two threads takes: 902
 */