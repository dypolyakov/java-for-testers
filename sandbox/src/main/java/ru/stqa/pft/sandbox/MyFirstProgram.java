package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("Dmitry");

        Square square = new Square(5);
        System.out.println("Площадь квадрата со сторонами " + square.length + " = " + square.area());

        Rectangle rectangle = new Rectangle(6, 7);
        System.out.println("Площадь прямоугольника со стороной " + rectangle.lengthA + " и " + rectangle.lengthB + " = " + rectangle.area());
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}