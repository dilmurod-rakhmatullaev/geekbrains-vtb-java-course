package com.geekbrains.lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homework {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};
        swapElements(numbers, 1, 3);
        System.out.println(Arrays.toString(numbers));   // [1, 4, 3, 2, 5]

        String[] names = {"Alice", "Bob", "Charlie", "George"};
        swapElements(names, 0, 2);
        System.out.println(Arrays.toString(names)); // [Charlie, Bob, Alice, George]

        List<Integer> numberList = arrayToList(numbers);
        System.out.println(numberList);    // [1, 4, 3, 2, 5]

        List<String> nameList = arrayToList(names);
        System.out.println(nameList);    // [Charlie, Bob, Alice, George]
    }

    public static <T> void swapElements(T[] array, int index1, int index2) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (index1 < 0 && index2 >= array.length || index2 < 0 && index1 >= array.length) {
            throw new IllegalArgumentException("Invalid indices");
        }

        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static <T> List<T> arrayToList(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        List<T> list = new ArrayList<>(array.length);
        for (T element : array) {
            list.add(element);
        }
        return list;
//        return new ArrayList<>(Arrays.asList(array));
    }
}
