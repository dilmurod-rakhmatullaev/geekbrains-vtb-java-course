package com.geekbrains.lesson3.exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainApp {
    public static void main(String[] args) {
        // old method, don't do this with resources
        BufferedReader reader1 = null;
        try {
            reader1 = new BufferedReader(new FileReader("log.txt"));
            System.out.println(reader1.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader1 != null) {
                try {
                    reader1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // modern and efficient method
        try (BufferedReader reader2 = new BufferedReader(new FileReader("log.txt"))) {
            String line = reader2.readLine();
            System.out.println("First Line: " + line);
        } catch (IOException e) {
            System.err.println("Error in reading file: " + e.getMessage());
        }
    }
}