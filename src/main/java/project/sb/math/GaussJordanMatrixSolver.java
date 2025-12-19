package project.sb.math;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GaussJordanMatrixSolver {

    private static void validation(double[][] matrix) {
        if(matrix==null){
            throw new IllegalArgumentException("Matrix passed as parameter should not be null");
        }


    //checks if provided matrix is valid square matrix
        if(!isValidSquareMatrix(matrix)){
           throw new IllegalArgumentException("Illegal matrix passed for computation: matrix should be a square matrix") ;
        }

    }

    public static boolean isValidSquareMatrix(double[][] matrix) {
        return IntStream.range(0,matrix.length).allMatch(i->matrix[i].length==matrix.length);
    }

    private static double[][] createInitialAns(double[][] matrix) {
        double[][] arr=new double[matrix.length][matrix.length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j< matrix.length;j++){
                if(i==j){
                    arr[i][i]=1;
                }
                else arr[i][j]=0;
            }
        }
        return  arr;
    }

public static double getDeterminantByElimination(double[][] matrix){

        validation(matrix);

        //creates deep copy of given 2d array so that passed array is not modified for computation
    double[][] arr =  Arrays.stream(matrix)
            .map(double[]::clone)
            .toArray(double[][]::new);

    //keeps track of how many times rows are swapped
        int swappedRows = 0;
        for(int i=0;i<arr.length-1;i++){
            //for diagonal

                    if (arr[i][i] == 0) {

                        //swap rows
                      boolean pivotFound=false;
                        for (int k = i+1; k < arr.length; k++) {
                            if (arr[k][i] != 0) {

                                for (int l = 0; l < arr.length; l++) {

                                    double mainRowVal = arr[i][l];
                                    arr[i][l] = arr[k][l];
                                    arr[k][l] = mainRowVal;
                                    pivotFound=true;
                                    swappedRows++;
                                }
                                break;
                            }

                        }
                        if (!pivotFound) return 0;
                    }




            for(int j=i+1;j<arr.length;j++){


                //for element except diagonal
                     double multiplyConstant=((arr[j][i]/arr[i][i]));
                    if (arr[j][i] != 0) {
                        //it traverses through pivot element(arr[i][i]) row and currVal row to perform necessary operation
                        for(int k=0;k<arr.length;k++){

                            arr[j][k] -= (arr[i][k]*multiplyConstant);
                        }


                    }





            }

        }
        //find determinant by multiplying diagonal elements
    double determinant=1;
    for(int p=0;p<arr.length;p++){

        determinant *=arr[p][p];

    }
    return determinant*Math.pow(-1,swappedRows);


}
    public static double[][] inverse(double[][] arr){

         if(getDeterminantByElimination(arr)==0) throw new IllegalArgumentException("provided matrix is not invertible");

         double[][] matrix= Arrays.stream(arr)
                 .map(double[]::clone) // Clones each inner array
                 .toArray(double[][]::new);

         double[][] ans=createInitialAns(matrix);

        for(int i=0;i< matrix.length;i++){

            //makes diagonal element 1
            computeForDiagonal(matrix,ans, i);

            //compute for all non-diagonal element of that particular column
            for (int j= matrix.length-1;j>=0;j--){
                //except for diagonal element compute other elements to make them 0


                if(i!=j) {
                    computeExceptDiagonal(matrix,ans,j,i);
                }

            }


        }


        return ans;
    }

    private static void computeExceptDiagonal(double[][] matrix,double[][] ans,int i,int j) {

        double currVal=matrix[i][j];
        //if element already 0 for non-diagonal element, skip the process else follow the process.
        if (currVal != 0) {

            //it traverses through pivot element row and currVal row to perform necessary operation
            for(int k=0;k<matrix.length;k++){
                matrix[i][k]=matrix[i][k]-matrix[j][k]*currVal;
                ans[i][k]=ans[i][k]-ans[j][k]*currVal;
            }


        }
    }

    private static void computeForDiagonal(double[][] matrix,double[][] ans,int i)  {
        double currVal=matrix[i][i];
        if(currVal==1) return; //if currVal is a already 1 ,don't do any computation.

        if(currVal!=0){ //if current value is less than or greater than 0

            //loop through currVal row to update value
            for(int k=0;k<matrix.length;k++){
                matrix[i][k]=matrix[i][k]/currVal; //just divide by currVal to make diagonal element 1
                ans[i][k]=ans[i][k]/currVal;//replicate the computation done in matrix array in ans array
            }
            return;
        }
        //if currVal is 0, we need to loop through the rows from beginning to end until we find a row suitable for computation
        //Traversing row to find appropriate row
        for(int k=0;k<matrix.length;k++){

            if(i!=k) {//skips the currVal row
                double checkVal = matrix[k][i];//get the element above or below the currVal row
                if(checkVal!=0){

                    //loop through the checkVal row and currVal row  to update values in currVal row
                    for(int l=0;l<matrix.length;l++) {
                        double newVal = matrix[k][l];//get the value from checkVal  row at particular moment
                        matrix[i][l]=matrix[i][l]+newVal/checkVal;//get value from currVal row  where opr is performed
                        ans[i][l]=ans[i][l]+ans[k][l]/checkVal;//replicate the performed opr in matrix array into ans array
                    }
                    //break through the loop after update happens in matrix and ans array
                    break;
            }




            }


        }
       
    }




}
