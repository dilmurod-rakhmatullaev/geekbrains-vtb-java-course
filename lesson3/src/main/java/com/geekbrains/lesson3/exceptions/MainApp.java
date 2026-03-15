package com.geekbrains.lesson3.exceptions;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) {
        // Login App
        System.out.println("--- Starting Login Process ---");

        try {
            // 1. Validation (Logic check)
            checkPassword("123");

            // 2. Network Simulation (External check)
            connectToServer();

            System.out.println("Login Successful!");

        } catch (IllegalArgumentException e) {
            // Unchecked: User's fault (bad input)
            System.out.println("Validation Error: " + e.getMessage());
            System.out.println("Action: Please enter a password longer than 6 characters.");

        } catch (IOException e) {
            // Checked: External fault (no internet)
            System.out.println("Network Error: " + e.getMessage());
            System.out.println("Action: Check your Wi-Fi and try again.");
        }

        System.out.println("--- Process Finished ---");
    }

    // Unchecked: We decide that "short" is an error
    public static void checkPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password is too short!");
        }
    }

    // Checked: Java forces us to handle the risk of a network crash
    public static void connectToServer() throws IOException {
        boolean isServerUp = false; // Mocking a server crash
        if (!isServerUp) {
            throw new IOException("Server is unreachable.");
        }
    }
}