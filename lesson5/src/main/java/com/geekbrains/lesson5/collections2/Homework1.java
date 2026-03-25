package com.geekbrains.lesson5.collections2;

import java.util.HashMap;
import java.util.Map;

/*
Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и
вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
Посчитать, сколько раз встречается каждое слово.
 */
public class Homework1 {
    public static void main(String[] args) {
        String[] fruits = {
                "apple", "banana", "orange", "apple", "grape",
                "banana", "cherry", "apple", "kiwi", "melon",
                "orange", "lemon", "grape", "berry", "apple"
        };

        Map<String, Integer> fruitCount = new HashMap<>();

        for (String fruit : fruits) {
            if (fruitCount.containsKey(fruit)) {
                fruitCount.put(fruit, fruitCount.get(fruit) + 1);
            } else {
                fruitCount.put(fruit, 1);
            }
        }

        System.out.println("Unique fruits and their amount:");

        for (Map.Entry<String, Integer> entry : fruitCount.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " times");
        }
        System.out.println("\nList of unique fruits: " + fruitCount.keySet());
    }
}
