package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        Point point1 = new Point(-1, 3);
        Point point2 = new Point(6, 2);

        System.out.println("Расстояние между двумя точками (" + point1.x + ", " + point1.y + ") и ("
                + point2.x + ", " + point2.y + ") равно " + Point.distance(point1, point2));

    }

}