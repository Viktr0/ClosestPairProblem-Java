package main;

import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {

        String inputPath = "Data\\sample_input_3_1000.tsv"; // Specify your input file path
        String outputPath = "Data\\output.txt"; // Specify your output file path

        FileOperator fileOperator = new FileOperator(inputPath, outputPath);

        Points points = fileOperator.readPoints();

        ClosestPairProblem cpp = new ClosestPairProblem(points);
        Pair pair = cpp.getClosestPair();

        fileOperator.writeClosestPair(pair, points);

    }
}
