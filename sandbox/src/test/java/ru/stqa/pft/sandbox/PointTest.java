package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void distanceTest1() {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(3, 4);

        double distance = point1.distance(point2);
        Assert.assertEquals(distance, 5);
    }

    @Test
    public void distanceTest2() {
        Point point1 = new Point(-1, 3);
        Point point2 = new Point(6, 2);

        double distance = point1.distance(point2);
        Assert.assertEquals(distance, 7.0710678118654755);
    }

    @Test
    public void distanceTest3() {
        Point point1 = new Point(2, 12);
        Point point2 = new Point(-1, 8);

        double distance = point1.distance(point2);
        Assert.assertEquals(distance, 5);
    }
}
