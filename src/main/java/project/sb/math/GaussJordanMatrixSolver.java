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

    public static double[][] compute(double[][] arr){
         validation(arr);
         double[][] matrix= Arrays.stream(arr)
                 .map(double[]::clone) // Clones each inner array
                 .toArray(double[][]::new);

         double[][] ans=createInitialAns(matrix);

        for(int i=0;i< matrix.length;i++){
            //gets a pivot element: diagonal element for that particular column
            double pivotElement = matrix[i][i];
            //makes diagonal element 1
            computeForDiagonal(matrix,ans, i);

            //compute for all non-diagonal element of that particular column
            for (int j= matrix.length-1;j>=0;j--){
                //except for diagonal element compute other elements to make them 0


                if(i!=j) {
                    computeExceptDiagonal(matrix,ans,j,i);
                }
                if(i== matrix.length-2 && j==matrix.length-1){
                    if(!isInvertible(matrix)){
                        throw  new IllegalArgumentException("matrix is not invertible");
                    }
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
    private static boolean isInvertible(double[][] arr) {
        double esp=1e-10;
        for(int i=0;i< arr.length;i++){
            if((Math.abs(arr[i][i])-esp)<=0){
                return false;
            }
        }
        return true;
    }


    //func eliminates/removes particular row and col passed in parameter from provided array and returns new array(doesn't work for jagged array)
    public static double[][] eliminateRowCol(double[][] arr, int row,int col){
        double[][] newArr=new double[arr.length-1][arr[0].length-1];
        boolean rowEliminated=false;
        for(int i=0;i<arr.length;i++){
            boolean colValEliminated=false;
            for(int j=0;j<arr[0].length;j++){
                if(i==row){
                    rowEliminated=true;
                    break;
                }
                if(j==col){
                    colValEliminated=true;
                    continue;
                }
                if(rowEliminated&&colValEliminated)
                    newArr[i-1][j-1]=arr[i][j];

                else if(rowEliminated)
                    newArr[i-1][j]=arr[i][j];

                else if(colValEliminated) newArr[i][j-1]=arr[i][j];

                else newArr[i][j]=arr[i][j];

            }


        }
        return newArr;
    }


}
