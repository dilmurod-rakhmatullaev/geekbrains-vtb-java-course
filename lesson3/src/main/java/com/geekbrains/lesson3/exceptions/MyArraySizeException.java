package com.geekbrains.lesson3.exceptions;
// Incorrect array size
public class MyArraySizeException extends Throwable {
    public MyArraySizeException(String message) {
        super(message);
    }
}
