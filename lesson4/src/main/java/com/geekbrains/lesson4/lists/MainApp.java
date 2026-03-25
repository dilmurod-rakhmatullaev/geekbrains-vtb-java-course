package com.geekbrains.lesson4.lists;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        //  1. ArrayList
        List<String> arrayList = new ArrayList<>(Arrays.asList("ZZ", "AAA", "BBBB", "C", "B", "D", "B"));
        System.out.println(arrayList);  // [ZZ, AAA, BBBB, C, B, D, B]
        while (arrayList.remove("B"));
        System.out.println(arrayList);  // [ZZ, AAA, BBBB, C, D]
        arrayList.sort((o1, o2) -> o1.length() - o2.length());
        System.out.println(arrayList);  // [C, D, ZZ, AAA, BBBB]

        //  2.LinkedList
        List<Integer> alist = new ArrayList<>();
        List<Integer> llist = new LinkedList<>();

        for (int i = 0; i < 1000000; i++) {
            int x = (int) (Math.random() * 5000);
            alist.add(x);
            llist.add(x);
        }

        long time = System.currentTimeMillis();
        Collections.sort(alist);
        System.out.println("time: " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        Collections.sort(llist);
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }
}
