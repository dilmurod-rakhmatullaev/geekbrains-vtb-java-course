package com.geekbrains.lesson2;

public class RunningTrack implements Obstacle{
    private int length;

    public RunningTrack(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean overcome(RunnableAndJumpable participant) {
        return participant.run(length);
    }
}
