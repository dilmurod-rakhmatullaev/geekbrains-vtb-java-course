package com.geekbrains.lesson2;

public enum Month {
    January (1, 31), February (2, 28), March (3, 31), April (4, 30), May (5, 31), June(6, 30),
    July(7, 31), August(8, 31), September(9, 30), October(10, 31), November(11, 30), December(12, 31);

    final int ORDER_OF_MONTHS;
    final int DAYS_IN_MONTH;

    Month(int ORDER_OF_MONTHS, int DAYS_IN_MONTH) {
        this.ORDER_OF_MONTHS = ORDER_OF_MONTHS;
        this.DAYS_IN_MONTH = DAYS_IN_MONTH;
    }

    public int getOrderOfMonths() {
        return ORDER_OF_MONTHS;
    }
    public int getDaysInMonth() {
        return DAYS_IN_MONTH;
    }
}