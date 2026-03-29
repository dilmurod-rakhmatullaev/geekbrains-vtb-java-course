package com.geekbrains.lesson8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {
        doSomething(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Java");
            }
        });
        doSomething(() -> System.out.println("100"));

        List<Integer> integers = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        integers.stream().filter((o) -> o % 2 == 0).forEach(System.out::println);   // 0\n2\n4\n6\n8\n
        List<Integer> out1 = integers.stream().filter((o) -> o % 2 == 0).toList();   // [0, 2, 4, 6, 8]

        System.out.println(out1);

        Stream.of("AA", "BBB", "C", "DDDD").map((o) -> o.length()).forEach(System.out::println);
        Stream.of(1, 2, 3, 4).map((o) -> o * 10).forEach(System.out::println);
        Stream.of("UN", "ISO", "UNESCO").map(String::length).forEach(System.out::println);
    }

    public static void doSomething(Runnable runnableObj) {
        runnableObj.run();
    }

    private static void secondEx() {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> out = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .limit(2)
                .toList();
        System.out.println(out);
    }

    private static void thirdEx() {
        System.out.println("First option: ");
        Arrays.asList(1, 2, 3, 4, 4, 3, 2, 3, 2, 1).stream().distinct().forEach(System.out::println);
        System.out.println("Second option: ");
        Arrays.asList(1, 2, 3, 4, 4, 3, 2, 3, 2, 1).stream().distinct().forEach(n -> System.out.println(n));
    }

    private static void matchEx() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        System.out.println(list.stream().allMatch(n -> n < 10));
        System.out.println(list.stream().anyMatch(n -> n == 4));
        System.out.println(list.stream().noneMatch(n -> n == 2));
    }
}
