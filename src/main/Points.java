package main;

import java.util.*;

public class Points {
    private final List<Double> data;
    private final int size;
    private final int dim;

    public Points(List<Double> data, int size, int dimension) {

        this.data = data;
        this.size = size;
        this.dim = dimension;
    }

    public double getDistance(int index1, int index2, int startCoordinate) {
        // Calculates the Euclidean distance between two points in the specified dimension.
        List<Double> p1 = getByIndex(index1, startCoordinate);
        List<Double> p2 = getByIndex(index2, startCoordinate);

        return calculateEuclideanDistance(p1, p2);
    }

    private double calculateEuclideanDistance(List<Double> p1, List<Double> p2) {
        double sum = 0.0;
        int n = p1.size();
        for (int i = 0; i < n; i++) {
            sum += Math.pow(p1.get(i) - p2.get(i), 2);
        }
        return Math.sqrt(sum);
    }

    public List<Double> getByIndex(int index, int startCoordinate) {
        // Return an array of the point with the specified index.
        // It can be in a lower dimension based on the startCoordinate.
        int start = index * this.dim + startCoordinate;
        int end = index * this.dim + this.dim;

        return this.data.subList(start, end);
    }

    public int[] sortIndicesByCoordinate(int[] indices, int coordinate) {
        // Create a list with the index and coordinate values pairs.
        List<ArrayList<Double>> indicesAndCoordinates = new ArrayList<ArrayList<Double>>();
        for (int index : indices) {
            Double[] indexAndCoordinatePair = new Double[]{(double) index, getByIndex(index, 0).get(coordinate)};
            indicesAndCoordinates.add(new ArrayList<Double>(Arrays.asList(indexAndCoordinatePair)));
        }

        // Sort the index and coordinate value pairs by the coordinate values.
        indicesAndCoordinates.sort(new Comparator<ArrayList<Double>>() {
            @Override
            public int compare(ArrayList<Double> o1, ArrayList<Double> o2) {
                return o1.get(1).compareTo(o2.get(1));
            }
        });

        // Get the sorted indices
        int[] sorted_indices = new int[indices.length];
        for (int i = 0; i < indices.length; i++) {
            sorted_indices[i] = indicesAndCoordinates.get(i).get(0).intValue();
        }

        return sorted_indices;
    }

    public int getSize() {
        return size;
    }

    public int getDim() {
        return dim;
    }
}
