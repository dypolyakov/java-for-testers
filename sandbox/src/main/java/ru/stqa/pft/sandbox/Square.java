package ru.stqa.pft.sandbox;

public class Square {

    public double length;

    public Square(double length) {
        this.length = length;
    }

    public double area() {
        return this.length * this.length;
    }

}
