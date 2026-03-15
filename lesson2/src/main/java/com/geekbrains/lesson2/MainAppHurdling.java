package com.geekbrains.lesson2;

public class MainAppHurdling {
    public static void main(String[] args) {
        RunnableAndJumpable[] participants = {
                new Human("Alex", 1000, 150),
                new Human("John", 800, 125),
                new Cat("Tom", 200, 200),
                new Cat("Martin", 500, 175),
                new Robot("Terminator", 5000, 100),
                new Robot("Walle", 2000, 50)
        };

        Obstacle[] obstacles = {
                new RunningTrack(200),
                new Hurdle(50),
                new RunningTrack(500),
                new Hurdle(100),
                new RunningTrack(1000),
                new Hurdle(125),
                new RunningTrack(2000),
                new Hurdle(150),
                new RunningTrack(5000),
                new Hurdle(175),
                new RunningTrack(6000),
                new Hurdle(200)
        };

        for (RunnableAndJumpable p : participants) {
            System.out.println("\nParticipant " + p.getName() + " begins hurdling");

            for (Obstacle o : obstacles) {
                boolean success = o.overcome(p);

                if (!success) {
                    break;
                }
            }
        }
    }
}