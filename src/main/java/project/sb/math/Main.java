package project.sb.math;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[][] arr={
                {1, 0, 3, 4},
                {2, 0, 5, 6},
                {7, 0, 8, 9},
                {3, 0, 1, 2}
        };
        double[][] arr2= {
                {1, 2, 3, 4},
                {2, 1, 1, 2},
                {3, 3, 4, 6},
                {5, 0, 0, 1}
        };
        double[][] compute = GaussJordanMatrixSolver.compute(arr);

        System.out.println(GaussJordanMatrixSolver.getDeterminant(arr));
        Arrays.stream(compute).forEach(a-> System.out.println(Arrays.toString(a)));
    }
}
