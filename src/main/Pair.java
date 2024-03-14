package main;

public class Pair {
    private int index1;
    private int index2;
    private double distance;

    public Pair(int index1, int index2, double distance) {
        if (index1 < index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
        else {
            this.index1 = index2;
            this.index2 = index1;
        }
        this.distance = distance;
    }

    public Pair compare(Pair p) {
        if (p.getDistance() < this.distance) {
            return p;
        }
        else {
            return this;
        }
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public double getDistance() {
        return distance;
    }
}
