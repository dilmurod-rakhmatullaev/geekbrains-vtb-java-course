package com.geekbrains.lesson2;

import javax.lang.model.element.AnnotationMirror;

public class MainApp {
    static class Human {
        private Transport currentTransport;

        public void stop() {
            if (currentTransport != null) {
                currentTransport.stop();
                currentTransport = null;
            } else {
                System.out.println("I haven't been moving");
            }
        }

        public void drive(Transport transport) {
            transport.start();
            this.currentTransport = transport;
        }
    }

    static class Car implements Transport {
        @Override
        public void start() {
            System.out.println("Driving a car has been started");
        }

        @Override
        public void stop() {
            System.out.println("Driving a car has been finished");
        }
    }

    static class Bicycle implements Transport {
        @Override
        public void start() {
            System.out.println("Riding a bicycle has been started");
        }

        @Override
        public void stop() {
            System.out.println("Riding a bicycle has been finished");
        }
    }

    public static void main(String[] args) {
        Flyable[] flyables = {
                new Duck(),
                new Airplane()
        };

        for (Flyable o : flyables) {
            o.fly();
        }

        Transport transport = new Car();
        Human human = new Human();

        human.stop();
        human.drive(transport);
        human.stop();
    }
}
