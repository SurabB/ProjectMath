package project.sb.math;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        double[][] arr={
                {0,1,2},
                {1,2,3},
                {3,1,1}
        };
        double[][] arr2=   {
                {1, 2, 3, 4},
                {2, 1, 1, 2},
                {3, 4, 1, 2},
                {1, 1, 1, 1}
        };
        GaussJordanMatrixSolver g=new GaussJordanMatrixSolver(arr2);
        double[][] compute = g.compute();
        Arrays.stream(compute).forEach(a-> System.out.println(Arrays.toString(a)));
    }
}
