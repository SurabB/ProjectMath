package project.sb.math;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GaussianElimination {
    private static final double EPS = 1.0e-6;
    public static boolean isValidSquareMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix passed as parameter should not be null");
        }
        return IntStream.range(0, matrix.length).allMatch(i -> matrix[i].length == matrix.length);
    }

    public static double getDeterminant(double[][] matrix) {

        if (matrix == null) {
            throw new IllegalArgumentException("Matrix passed as parameter should not be null");
        }
        if (!isValidSquareMatrix(matrix)) {
            throw new IllegalArgumentException("Illegal matrix passed for computation: matrix should be a square matrix");

        }
        //creates deep copy of given 2d array so that passed array is not modified for computation
        double[][] arr = Arrays.stream(matrix)
                .map(double[]::clone)
                .toArray(double[][]::new);

        //keeps track of how many times rows are swapped
        int swappedRows = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            //for diagonal

            if (Math.abs(arr[i][i]) < EPS) {

                //swap rows
                boolean pivotFound = false;
                for (int k = i + 1; k < n; k++) {
                    if (Math.abs(arr[k][i]) > EPS) {

                        double[] temp = arr[i];
                        arr[i] = arr[k];
                        arr[k] = temp;
                        pivotFound = true;
                        swappedRows++;
                        break;
                    }

                }
                if (!pivotFound) return 0;
            }


            for (int j = i + 1; j < n; j++) {


                //for element except diagonal
                double multiplyConstant = ((arr[j][i] / arr[i][i]));
                if (Math.abs(arr[j][i]) > EPS) {
                    //it traverses through pivot element(arr[i][i]) row and currVal row to perform necessary operation
                    for (int k = 0; k < n; k++) {

                        arr[j][k] -= (arr[i][k] * multiplyConstant);
                    }


                }


            }

        }
        //find determinant by multiplying diagonal elements
        double determinant = 1;
        for (int p = 0; p < n; p++) {

            determinant *= arr[p][p];

        }
        return determinant * Math.pow(-1, swappedRows);


    }

    public static double[] solveEquation(double[][] A, double[] B) {
        if (A == null || B == null) {
            throw new IllegalArgumentException("Matrix passed as parameter should not be null");
        }
        if (A.length != B.length) {
            throw new IllegalArgumentException("For AX=B , A.length is not equal to B.Length. Matrix A rows should be equal to Matrix B rows ");

        }
        if (!isValidSquareMatrix(A)) {
            throw new IllegalArgumentException("Illegal matrix passed for computation: matrix should be a square matrix");

        }

        //creates deep copy of given 2d array so that passed array is not modified for computation
        double[][] arr = Arrays.stream(A)
                .map(double[]::clone)
                .toArray(double[][]::new);

        double[] b = Arrays.copyOf(B, B.length);

        for (int i = 0; i < arr.length - 1; i++) {
            //for diagonal

            if (Math.abs(arr[i][i]) < EPS) {

                //swap rows
                boolean pivotFound = false;
                for (int k = i + 1; k < arr.length; k++) {
                    if (Math.abs(arr[k][i]) > EPS) {
                        double[] temp = arr[i];
                        arr[i] = arr[k];
                        arr[k] = temp;

                        double temp2 = b[i];
                        b[i] = b[k];
                        b[k] = temp2;

                        pivotFound = true;
                        break;
                    }

                }
                if (!pivotFound) {
                    throw new RuntimeException("Cannot solve eqn as matrix is not invertible");
                }
            }


            for (int j = i + 1; j < arr.length; j++) {


                //for element except diagonal
                double multiplyConstant = ((arr[j][i] / arr[i][i]));
                if (Math.abs(arr[j][i]) > EPS) {
                    b[j] -= (b[i] * multiplyConstant);

                    for (int k = 0; k < arr.length; k++) {

                        arr[j][k] -= (arr[i][k] * multiplyConstant);

                    }


                }


            }

        }
        //find value of eg: x,y,z in 3*3 matrix, or x,y in 2*2 matrix and store soln in ans
        double[] ans = new double[A.length];
        for (int i = A.length - 1; i >= 0; i--) {
            var curr = arr[i];
            var currB = b[i];

            for (int j = A.length - 1; j >= i; j--) {
                if (j == i) {

                    ans[j] = currB / curr[j];
                } else {
                    currB -= curr[j] * ans[j];
                }

            }

        }
        return ans;
    }

}
