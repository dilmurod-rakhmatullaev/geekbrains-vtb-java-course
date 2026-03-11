package com.geekbrains.lesson1;

public class Box {
    String colour;
    int size;

    public Box(String colour, int size) {
        this.colour = colour;
        this.size = size;
    }

    @Override
    public boolean equals (Object object) {
        if (this == object) return true;
        if (!(object instanceof Box)) return false;

        Box anotherBox = (Box) object;
        return this.colour.equals(anotherBox.colour) && this.size == anotherBox.size;
    }

    @Override
    public int hashCode() {
        return colour.length() * 13 + size * 71;
    }
}
