package com.geekbrains.lesson3.exceptions;

public class MainAppMyExceptions {
    public static void main(String[] args) {
        String[][] arr1 = {
                {"7", "8", "9", "0"},
                {"4", "5", "6", "7"},
                {"1", "2", "f", "4"},
                {"0", "1", "2", "3"}
        };
        String[][] arr2 = {
                {"7", "8", "9", "0"},
                {"4", "5", "6", "7"},
                {"1", "2", "3", "4"},
                {"0", "1", "2", "3"}
        };

        try {
            int result = sumArray(arr1);
            System.out.println("Sum of all elements:" + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            int result = sumArray(arr2);
            System.out.println("Sum of all elements of array: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int sumArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr == null) {
            throw new MyArraySizeException("Array length can't be null");
        }
        if (arr.length != 4) {
            throw new MyArraySizeException("Array length should be 4, but got " + arr.length);
        }
        for (String[] row : arr) {
            if (row.length != 4) {
                throw new MyArraySizeException("Row length should be 4, but got " + row.length);
            }
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Cannot convert '" + arr[i][j] + "' at position [" + i + "][" + j + "] to int.");
                }
            }
        }
        return sum;
    }
}
