package com.geekbrains.lesson2;

public class Hurdle implements Obstacle{
    private int height;

    public Hurdle(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean overcome(RunnableAndJumpable participant) {
        return participant.jump(height);
    }
}
