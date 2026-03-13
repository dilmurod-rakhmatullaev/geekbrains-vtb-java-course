package com.geekbrains.lesson2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassTypes {
    static class Inner {
        int innerValue;

        public Inner(int innerValue) {
            this.innerValue = innerValue;
        }

        public void innerMethod() {
            System.out.println(innerValue);
            // System.out.println(outerValue);
            // outerMethod(); can't run
        }
    }

    int outerValue;

    public void outerMethod() {
        System.out.println(outerValue);
//        System.out.println(innerValue);
//        innerMethod();

        Inner inner = new Inner(26);
    }

    public static void main(String[] args) {
        // Inner inner = new ClassTypes().new Inner(15); Inner Class

        Inner inner = new Inner(10); // "static" makes Inner class -> Nested class

        class LocalClass {
            int num;

            public LocalClass(int num) {
                this.num = num;
            }

            public void info() {
                System.out.println(num);
            }
        }

        LocalClass localClass = new LocalClass(10);
        localClass.info();

        Flyable flyable = new Flyable() {    // creating anonymous class
            @Override
            public void fly() {

            }
        };
        System.out.println(flyable.getClass().getName());

        // public class ClassTypes$1 implements Flyable {
        // @Override
        // public void fly () {
        // }
        // }
        // ClassTypes flyable = new ClassTypes();


        JButton button1 = new JButton("Btn 1");
        JButton button2 = new JButton("Btn 2");
        JButton button3 = new JButton("Btn 3");
        button1.addActionListener(new ButtonAction());
        button2.addActionListener(new Button2Action());
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(3);
            }
        });


    }
}
