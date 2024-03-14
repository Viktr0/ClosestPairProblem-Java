package main;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PointsTest {
    private static Points pointsObj;

    @BeforeAll
    static void setup() {
        Random r = new Random();
        List<Double> points = new ArrayList<Double>();
        // Point 1
        points.add(0.0);
        points.add(0.0);
        points.add(0.0);
        // Point 2
        points.add(7.0);
        points.add(6.333);
        points.add(7.0001);
        // Point 3
        points.add(-22.0);
        points.add(-21.0);
        points.add(-23.23312);
        // Point 4
        points.add(10.0);
        points.add(10.0);
        points.add(10.0);

        int dimension = 3;
        int size = 4;

        pointsObj = new Points(points, size, dimension);
    }


    @Test
    void getDistance() {
        double expected = Math.sqrt(10*10 + 10*10 + 10*10);
        assertEquals(expected, pointsObj.getDistance(0,3,0));
    }

    @Test
    void getByIndex() {
        List<Double> point4 = new ArrayList<Double>();
        point4.add(10.0);
        point4.add(10.0);
        point4.add(10.0);
        assertEquals(point4, pointsObj.getByIndex(3, 0));
    }

    @Test
    void sortIndicesByCoordinate() {
        int n = pointsObj.getSize();
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        int[] expected = new int[]{2, 0, 1, 3};
        int[] actual = pointsObj.sortIndicesByCoordinate(indices, 1);
        assertArrayEquals(expected, actual);
    }

    @Test
    void getSize() {
        assertEquals(4, pointsObj.getSize());
    }

    @Test
    void getDim() {
        assertEquals(3, pointsObj.getDim());
    }
}