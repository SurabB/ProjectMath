package project.sb.math;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GaussJordan {

    public static boolean isValidSquareMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix passed as parameter should not be null");
        }
        return IntStream.range(0, matrix.length).allMatch(i -> matrix[i].length == matrix.length);
    }

    private static double[][] createInitialAns(double[][] matrix) {
        double[][] arr = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    arr[i][i] = 1;
                } else arr[i][j] = 0;
            }
        }
        return arr;
    }


    public static double[][] inverse(double[][] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Matrix passed as parameter should not be null");
        }
        if (!isValidSquareMatrix(arr)) {
            throw new IllegalArgumentException("Illegal matrix passed for computation: matrix should be a square matrix");

        }
        double[][] matrix = Arrays.stream(arr)
                .map(double[]::clone) // Clones each inner array
                .toArray(double[][]::new);

        double[][] ans = createInitialAns(matrix);
        final double eps = 1.0e-6;

        for (int i = 0; i < matrix.length; i++) {
            if (Math.abs(matrix[i][i]) < eps) {
                boolean swapped = false;
                for (int l = i + 1; l < matrix.length; l++) {
                    if (Math.abs(matrix[l][i]) > eps) {
                        double[] temp = matrix[l];
                        matrix[l] = matrix[i];
                        matrix[i] = temp;

                        double[] temp2 = ans[l];
                        ans[l] = ans[i];
                        ans[i] = temp2;

                        swapped = true;
                        break;
                    }

                }
                if (!swapped) {
                    throw new IllegalArgumentException("Provided matrix is not invertible...");
                }
            }
            double currVal = matrix[i][i];
            //loop through currVal row to update value
            for (int k = 0; k < matrix.length; k++) {
                matrix[i][k] = matrix[i][k] / currVal; //just divide by currVal to make diagonal element 1
                ans[i][k] = ans[i][k] / currVal;//replicate the computation done in matrix array in ans array
            }
            //compute for all non-diagonal element of that particular column
            for (int j = matrix.length - 1; j >= 0; j--) {
                //except for diagonal element compute other elements to make them 0


                if (i != j) {
                    double currValJ = matrix[j][i];
                    //if element already 0 for non-diagonal element, skip the process else follow the process.
                    if (Math.abs(currValJ) > eps) {

                        //it traverses through pivot element row and currValJ row to perform necessary operation
                        for (int k = 0; k < matrix.length; k++) {
                            matrix[j][k] = matrix[j][k] - matrix[i][k] * currValJ;
                            ans[j][k] = ans[j][k] - ans[i][k] * currValJ;
                        }


                    }
                }

            }


        }
        return ans;
    }

    public static double[] solveEqn(double[][] A, double[] B) {
        if (A == null) {
            throw new IllegalArgumentException("Matrix passed as parameter should not be null");
        }
        if (A.length != B.length) {
            throw new IllegalArgumentException("For AX=B , A.length is not equal to B.Length. Matrix A rows should be equal to Matrix B rows ");

        }

        if (!isValidSquareMatrix(A)) {
            throw new IllegalArgumentException("Illegal arrA passed for computation: arrA should be a square arrA");

        }
        double[][] arrA = Arrays.stream(A)
                .map(double[]::clone) // Clones each inner array
                .toArray(double[][]::new);

        double[] arrB = Arrays.copyOf(B, B.length);
        final double eps = 1.0e-6;

        for (int i = 0; i < arrA.length; i++) {
            if (Math.abs(arrA[i][i]) < eps) {
                boolean swapped = false;
                for (int l = i + 1; l < arrA.length; l++) {
                    if (Math.abs(arrA[l][i]) > eps) {
                        double[] temp = arrA[l];
                        arrA[l] = arrA[i];
                        arrA[i] = temp;

                        double temp2 = arrB[l];
                        arrB[l] = arrB[i];
                        arrB[i] = temp2;

                        swapped = true;
                        break;
                    }

                }
                if (!swapped) {
                    return new double[]{};
                  }
            }
            double currVal = arrA[i][i];
            arrB[i] = arrB[i] / currVal;
            //loop through currVal row to update value
            for (int k = 0; k < arrA.length; k++) {
                arrA[i][k] = arrA[i][k] / currVal; //just divide by currVal to make diagonal element 1
            }
            //compute for all non-diagonal element of that particular column
            for (int j = arrA.length - 1; j >= 0; j--) {
                //except for diagonal element compute other elements to make them 0


                if (i != j) {
                    double currValJ = arrA[j][i];

                    //if element already 0 for non-diagonal element, skip the process else follow the process.
                    if (Math.abs(currValJ) > eps) {
                        arrB[j] = arrB[j] - arrB[i] * currValJ;
                        //it traverses through pivot element row and currValJ row to perform necessary operation
                        for (int k = 0; k < arrA.length; k++) {
                            arrA[j][k] = arrA[j][k] - arrA[i][k] * currValJ;

                        }


                    }
                }

            }


        }
        return arrB;
    }

}





