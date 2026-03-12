package com.geekbrains.lesson2;

public class Calendar {
    public void orderAndDaysAmountInfo(Month month) {
        System.out.println(month + " is the " + month.getOrderOfMonths() + " month and there are " + month.getDaysInMonth() + " days in it");
    }
}
