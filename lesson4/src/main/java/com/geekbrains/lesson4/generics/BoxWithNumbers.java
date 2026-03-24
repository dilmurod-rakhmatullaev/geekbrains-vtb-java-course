package com.geekbrains.lesson4.generics;

public class BoxWithNumbers<N extends Number> {
    private N[] array;

    public BoxWithNumbers(N... array) {
        this.array = array;
    }

    public double average() {
        double sum = 0.0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i].doubleValue();
        }
        return sum / array.length;
    }

    public boolean compareAverage(BoxWithNumbers<?> another) {
        return Math.abs(this.average() - another.average()) < 0.0001;
    }
}
