package com.geekbrains.lesson7;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MostFrequentWord {
    public static void main(String[] args) {
        String[] words = {"sun", "moon", "star", "comet", "star", "planet", "sun", "star", "meteorite"};

        Map<String, Long> frequency = Arrays.stream(words)
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));

        Optional<Map.Entry<String, Long>> maxEntry = frequency.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        String mostFrequent1 = maxEntry
                .map(Map.Entry::getKey).orElse("No words");
        System.out.println("Most frequently used word: " + mostFrequent1);

        String mostFrequent2 = Arrays.stream(words)
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("no words");
        System.out.println("Most frequently used word: " + mostFrequent2);
    }
}
