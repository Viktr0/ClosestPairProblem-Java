package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClosestPairProblem {
    private final Points pointsObj;

    public ClosestPairProblem(Points pointsObj) {
        this.pointsObj = pointsObj;
    }

    public Pair getClosestPair() {
        // Calculates and returns the closest pair of points.
        int n = pointsObj.getSize();
        int d = pointsObj.getDim();

        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // # Calls Brute Force or Divide and Conquer algorithm based on the points and the complexity.
        if ((d * Math.pow(n, 2)) < (n * Math.pow(Math.log(n), d))) {
            System.out.println("BruteForce");
            return bruteForce(indices, 0, Double.MAX_VALUE);
        }
        else {
            System.out.println("Divide and Conquer");
            int[] sortedIndices = pointsObj.sortIndicesByCoordinate(indices, 0);
            return divideAndConquer(sortedIndices, 0, Double.MAX_VALUE);
        }
    }

    public Pair divideAndConquer(int[] indices, int dim, double delta) {
        // A recursive function that implements the Divide and Conquer algorithm.
        // Recursively separates the space and projects points into lower dimensions.
        if (indices.length <= 3) {
            return bruteForce(indices, dim, delta);
        }

        // Find midpoint and separates the points equally by a hyperplane.
        int m = indices.length / 2;
        double m1 = pointsObj.getByIndex(m-1, 0).get(dim);
        double m2 = pointsObj.getByIndex(m, 0).get(dim);
        double midpoint = (m1 + m2) / 2;
        int[] s1 = Arrays.copyOfRange(indices, 0, m);
        int[] s2 = Arrays.copyOfRange(indices, m, indices.length);

        // Get closest pair from each side and choose smaller distance.
        Pair pair1 = divideAndConquer(s1, dim, delta);
        Pair pair2 = divideAndConquer(s2, dim, delta);
        Pair pair = pair1.compare(pair2);
        if (pair.getDistance() < delta) {
            delta = pair.getDistance();
        }

        // Select points within delta of the hyperplane.
        List<Integer> slabIndicesList = new ArrayList<>();
        for (int index : indices) {
            double coordinate = pointsObj.getByIndex(index, 0).get(dim);
            if (midpoint - delta < coordinate && coordinate < midpoint + delta) {
                slabIndicesList.add(index);
            }
        }
        int [] slabIndices = slabIndicesList.stream().mapToInt(Integer::intValue).toArray();

        // Project and solve problem in reduced dimension.
        Pair pair3;
        if (dim < pointsObj.getDim() - 2) {
            int[] sortedSlabIndices = pointsObj.sortIndicesByCoordinate(slabIndices, dim + 1);
            pair3 = divideAndConquer(sortedSlabIndices, dim + 1, delta);
        }
        else {
            pair3 = linearSearch(slabIndices, delta);
        }

        return pair3.compare(pair);
    }

    public Pair bruteForce(int[] indices, int dim, double delta) {
        // A naive algorithm for finding the closest pair of points.
        int n = indices.length;
        Pair closestPair = new Pair(-1, -1, Double.MAX_VALUE);
        if (n >= 2) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    int iInd = indices[i];
                    int jInd = indices[j];
                    double lowerDimDist = pointsObj.getDistance(iInd, jInd, dim);
                    if (lowerDimDist < delta) {
                        double realDist = pointsObj.getDistance(iInd, jInd, 0);
                        if (realDist < delta) {
                            closestPair = new Pair(iInd, jInd, realDist);
                            delta = realDist;
                        }
                    }
                }
            }
        }
        return closestPair;
    }

    private Pair linearSearch(int[] indices, double delta) {
        // Optimised closest pair search on a 1D sorted array.
        int n = indices.length;
        Pair closestPair = new Pair(-1, -1, Double.MAX_VALUE);
        if (n >= 2) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < Math.min(i + 7, n); j++) {
                    int iInd = indices[i];
                    int jInd = indices[j];
                    double distance = pointsObj.getDistance(iInd, jInd, 0);
                    if (distance < delta) {
                        closestPair = new Pair(iInd, jInd, distance);
                        delta = distance;
                    }
                }
            }
        }
        return closestPair;
    }
}