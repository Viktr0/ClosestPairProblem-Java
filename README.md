# Task description

The task is to create a Java/Python program that reads a text file where each line contains the
coordinates of a multidimensional point and then looks for the closest pair of points in the file. If
the program has found the closest pair of points, it should output the line numbers and the
coordinates of the two closest points.

The text file contains one point per line. The coordinate values are separated by a tabulator
character. The coordinate values are not necessarily integers. In the case of a floating-point
coordinate value, the decimal separator is a period.

# My solution

To solve this problem I implemented the naive (brute-force) and the divide and conquer
algorithms in Java.

## Architecture

My solution follows the OO principles and contains 4 classes:
* FileOperator
* Points
* ClosestPairProblem
* Pair

<p align="center">
  <img src="https://github.com/Viktr0/ClosestPairProblem/assets/47856193/24282bde-414d-4dcd-841d-96dd6157e96d" />
</p>

### FileOperator

The FileOperator reads the input file containing the multidimensional points and creates a
Points object. If the input file has a damaged structure then it returns with an empty Points
object. The other task of this class is writing the line numbers and coordinate values of the
closest pair in the desired format to an output file.

### Points

The Points data structure is the base of the program. This class stores the coordinate values of
the points in a list, and also the size and the dimension. It can calculate the distance between
two points, return a point by index and sort the points given by their indices by a specified
coordinate value.

### ClosestPairProblem

The task of the ClosestPairProblem class is to find the closest pair in the given Points object.
The class decides which algorithm is more suited for the problem based on the number and
dimension of the points. It can choose from the brute-force and the recursive divide and conquer
algorithms.

### Pair

The Pair class contains the indices and the distance of the closest pair. It can also compare
itself to another Par object and returns with the smaller.

## Running time

### Brute-force

It is easy to find the closest pair in O(d*n^2) time by computing the distance of all pairs and
selecting the minimum, which is the naive algorithm. But we can aim for a better time as long as
the dimension is low with the divide and conquer algorithm.

### Divide and conquer

This algorithm computes the median of the specified coordinates and separates the points into
two sets by a hyperplane that is perpendicular to the specified coordinate axis. Then recursively
solve the closest pair in each set. In the next step it calculates the smaller distance (delta) of the
closest pairs from each set and selects the points that are within delta distance of the
hyperplane. Then project these points and solve the same problem in a lower dimension.
The complexity of the algorithm is O(n*logn^(d-1)).

## Limitations

The divide and conquer algorithm performs well in lower dimensions, but the complexity of the
algorithm is exponential in d, so it is very sensitive to the dimension parameter. On the other
hand naive algorithms are linear in dimension.

## Usage instructions

Place the input files in the Data folder in the working directory. Both in the Java and in the
Python project, set the input file path and the result file path in the main, then run the program.
The ClosestPairProblem class prints to the standard output which algorithm was used for the
calculations and the results are shown in the output file.
The test files can be found in the Test/main/ folder:

<p align="center">
  <img src="https://github.com/Viktr0/ClosestPairProblem/assets/47856193/672a1643-c507-4f9f-9782-3d372ff2c0e6" />
</p>

