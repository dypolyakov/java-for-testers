package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("Dmitry");

        double squareSide = 5;
        System.out.println("Площадь квадрата со сторонами " + squareSide + " = " + area(squareSide));

        double firstRectangleSide = 6;
        double secondRectangleSide = 7;
        System.out.println("Площадь прямоугольника со стороной " + firstRectangleSide + " и " + secondRectangleSide + " = " + area(firstRectangleSide, secondRectangleSide));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double sideLength) {
        return sideLength * sideLength;
    }

    public static double area(double firstSide, double secondSide) {
        return firstSide * secondSide;
    }
}