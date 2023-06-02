package ru.stqa.pft.sandbox;

public class Rectangle {
    public double lengthA;
    public double lengthB;

    public Rectangle(double lengthA, double lengthB) {
        this.lengthA = lengthA;
        this.lengthB = lengthB;
    }

    public double area() {
        return this.lengthA * this.lengthB;
    }
}
