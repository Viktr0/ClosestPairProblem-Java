package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairProblemTest {
    private static Points pointsObj;
    private static ClosestPairProblem closestPairProblem;

    void init(String path) throws IOException {
        FileOperator fileOperator = new FileOperator(path, "Data\\output.txt");
        pointsObj = fileOperator.readPoints();
        closestPairProblem = new ClosestPairProblem(pointsObj);
    }

    @Test
    void getClosestPair() throws IOException {
        init("Data\\sample_input_100_100.tsv");

        Pair closestPair = closestPairProblem.getClosestPair();

        int[] expected = new int[]{48, 96};
        int[] actual = new int[]{closestPair.getIndex1() + 1, closestPair.getIndex2() + 1};

        assertArrayEquals(expected, actual);

    }

    @Test
    void divideAndConquer() throws IOException {
        init("Data\\sample_input_3_1000.tsv");

        int n = pointsObj.getSize();
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        int[] sortedIndices = pointsObj.sortIndicesByCoordinate(indices, 0);
        Pair closestPair = closestPairProblem.divideAndConquer(sortedIndices, 0, Double.MAX_VALUE);

        int[] expected = new int[]{223, 388};
        int[] actual = new int[]{closestPair.getIndex1() + 1, closestPair.getIndex2() + 1};

        assertArrayEquals(expected, actual);
    }

    @Test
    void bruteForce() throws IOException {
        init("Data\\sample_input_100_100.tsv");

        int n = pointsObj.getSize();
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Pair closestPair = closestPairProblem.bruteForce(indices, 0, Double.MAX_VALUE);

        int[] expected = new int[]{48, 96};
        int[] actual = new int[]{closestPair.getIndex1() + 1, closestPair.getIndex2() + 1};

        assertArrayEquals(expected, actual);
    }
}