package com.geekbrains.lesson7;

/*
1. Создайте массив объектов типа Сотрудник (с полями Имя, Возраст, Зарплата) и вычислите среднюю зарплату сотрудника;
2. Напишите метод, способный найти в массиве сотрудников из п. 2 найдите N самых старших
сотрудников и отпечатает в консоль сообщение вида “N самых старших сотрудников зовут:
имя1, имя2, имяN;”.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

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

        printOldestEmployees(employees, 3);
    }

    public static void printOldestEmployees(Employee[] employees, int n) {
        if (employees == null || employees.length == 0) {
            System.out.println("Employees list is empty");
            return;
        }

        if (n <= 0) {
            System.out.println("Input number bigger zero");
        }

        if (n > employees.length) {
            System.out.println("Input number between 0 and " + employees.length);
        }

        String oldestEmployees = Arrays.stream(employees)
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(n)
                .map(Employee::getName)
                .collect(Collectors.joining(", "));

        System.out.println(n + " oldest employees in the company: " + oldestEmployees);
    }
}
/*
Average salary in the company is: 73000.0
Average age in the company is: 31
 */