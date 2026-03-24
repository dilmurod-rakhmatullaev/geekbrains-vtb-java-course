package com.geekbrains.lesson4.generics;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        SimpleBox intBox1 = new SimpleBox(10);
        SimpleBox intBox2 = new SimpleBox(20);
        // intBox1.setObj("Java");
        if (intBox1.getObj() instanceof Integer && intBox2.getObj() instanceof Integer) {
            int sum1 = (Integer) intBox1.getObj() + (Integer) intBox2.getObj();
            System.out.println("sum1: " + sum1);
        } else {
            System.out.println("ClassCastException");
        }


        GenBox<String> strBox = new GenBox<>("Java");
        GenBox<Integer> intBox3 = new GenBox<>(15);
        GenBox<Integer> intBox4 = new GenBox<>(25);
        // intBox3.setObj("Generics");
        int sum2 = intBox3.getObj() + intBox4.getObj();
        System.out.println("sum2: " + sum2);

        BoxWithNumbers<Integer> intBox5 = new BoxWithNumbers<>(1, 2, 3, 4);
        System.out.println(intBox5.average());
        BoxWithNumbers<Float> floatBox = new BoxWithNumbers<>(1.0f, 2.0f, 3.0f, 4.0f);
        System.out.println(floatBox.average());
        BoxWithNumbers<Integer> intBox6 = new BoxWithNumbers<>(1, 2, 3, 4);
        System.out.println(intBox5.compareAverage(floatBox));

        GenBox<Number> gbn = new GenBox<>(1);
        GenBox<Integer> gbi = new GenBox<>(1);
        doSomething1(gbn);
        doSomething2(gbn);
        doSomething1(gbi);
//        doSomething2(gbi);
//        GenBox<Number> gbx = new GenBox<Integer>(123);
    }

    public static <T extends Number> T getFirstElement(List<T> list) {
        return list.get(0);
    }
    public static void doSomething1(GenBox<? extends Number> box) {}
    public static void doSomething2(GenBox<Number> box) {}
    public static void doSomething3(GenBox<? super Number> box) {}
}
