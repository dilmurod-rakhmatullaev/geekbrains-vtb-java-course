package com.geekbrains.lesson7;

// Создайте массив объектов типа Сотрудник (с полями Имя, Возраст, Зарплата) и вычислите среднюю зарплату сотрудника;

import java.util.Arrays;

public class StaffInfo {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Alice", 30, 75000),
                new Employee("Bob", 25, 68000),
                new Employee("John", 35, 85000),
                new Employee("Cole", 45, 58000),
                new Employee("Mike", 20, 95000),
                new Employee("Lucy", 29, 48000),
                new Employee("Charlie", 32, 82000)
        };

        double averageSalary = Arrays.stream(employees)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("Average salary in the company is: " + averageSalary);

        double averageAge = Arrays.stream(employees)
                .mapToDouble(Employee::getAge)
                .average()
                .orElse(0.0);

        System.out.println("Average age in the company is: " + Math.round(averageAge));
    }
}
/*
Average salary in the company is: 73000.0
Average age in the company is: 31
 */