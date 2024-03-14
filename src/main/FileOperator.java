package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOperator {
    private final String inputPath;
    private final String outputPath;

    public FileOperator(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public Points readPoints() throws IOException {
        List<Double> pointsList = new ArrayList<>();
        int rowCounter = 0;
        int dimension = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(this.inputPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rowCounter++;
                String[] coordinates = line.split("\t");
                for (String c : coordinates) {
                    pointsList.add(Double.parseDouble(c));
                }
            }
            dimension = pointsList.size() / rowCounter;
        } catch (Exception e) {
            System.out.println("Error during loading the file.");
        }

        if (dimension * rowCounter != pointsList.size()) {
            System.out.println("Could not load data. Dimension problem in the input file.");
            return new Points(new ArrayList<>(), 0, 0);
        }

        return new Points(pointsList, rowCounter, dimension);
    }

    public void writeClosestPair(Pair pair, Points pointsObj) throws IOException {
        if (pointsObj.getSize() > 2) {
            List<Double> point1 = pointsObj.getByIndex(pair.getIndex1(), 0);
            List<Double> point2 = pointsObj.getByIndex(pair.getIndex2(), 0);

            StringBuilder line1 = new StringBuilder(new String(String.valueOf(pair.getIndex1() + 1) + ':' + point1.get(0)));
            StringBuilder line2 = new StringBuilder(new String(String.valueOf(pair.getIndex2() + 1) + ':' + point2.get(0)));
            for (int i = 1; i < pointsObj.getDim(); i++) {
                line1.append('\t').append(point1.get(i).toString());
                line2.append('\t').append(point2.get(i).toString());
            }
            String output = line1.toString() + '\n' + line2;

            try (FileWriter writer = new FileWriter(outputPath)) {
                writer.write(output);

            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        }
        else {
            System.out.println("There is not enough points in pointsObj.");
        }
    }
}
