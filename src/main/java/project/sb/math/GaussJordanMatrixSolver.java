package project.sb.math;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GaussJordanMatrixSolver {
    private final double[][] matrix;//stores problem matrix
    private final double[][] ans;// stores solution


    public GaussJordanMatrixSolver(double[][] matrix) {
        if(matrix==null){
            throw new IllegalArgumentException("Matrix passed in constructor should not be null");
        }
        double[][] copy= Arrays.stream(matrix)
                .map(double[]::clone)
                .toArray(double[][]::new);

    //checks if provided matrix is valid square matrix
        if(!isValidSquareMatrix(copy)){
           throw new IllegalArgumentException("Illegal matrix passed for computation: matrix should be a square matrix") ;
        }
        if(getDeterminant(copy)==0) throw new IllegalArgumentException("provided matrix is not invertible");

        this.matrix = copy;
        this.ans=createInitialAns(copy);
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

    public double[][] compute(){
        for(int i=0;i< matrix.length;i++){
            //gets a particular column based on column index
            double[] column = getColumn(matrix,i);

            //gets a pivot element: diagonal element for that particular column
            double pivotElement = column[i];
            //makes diagonal element 1
            computeForDiagonal(pivotElement, i);

            //compute for all non-diagonal element of that particular column
            for (int j=0;j<column.length;j++){
                //except for diagonal element compute other elements to make them 0
                if(i!=j) {
                    computeExceptDiagonal(column[j],j,i);
                }
            }

        }


        return ans;
    }

    public static double[] getColumn(double[][] arr,int column) {
        return IntStream.range(0, arr.length)
                .mapToDouble(i -> arr[i][column])
                .toArray();
    }
    public void computeExceptDiagonal(double currVal,int i,int j) {
        //if element already 0 for non-diagonal element, skip the process else follow the process.
        if (currVal != 0) {

            //it traverses through pivot element row and currVal row to perform necessary operation
            for(int k=0;k<matrix.length;k++){
                matrix[i][k]=matrix[i][k]-matrix[j][k]*currVal;
                ans[i][k]=ans[i][k]-ans[j][k]*currVal;
            }


        }
    }

    public void computeForDiagonal(double currVal,int i)  {
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
    public static double getDeterminant(double[][] arr){
        //when arr is reduced to have only one element return it
        if(arr.length==1){
            return arr[0][0];
        }

        //stores determinant value;
        double sum=0;
        for(int i=0;i<arr.length;i++){
           //at alternate value add - symbol
            double val = (i % 2 == 0) ? arr[0][i] : -arr[0][i];

            //eliminates provided row and col to give a new array
            double[][] newArr = eliminateRowCol(arr, 0, i);

            //recursively calls determinant func with new array
            sum+=val*getDeterminant(newArr);

        }
        return sum;
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
