<h2>GaussJordanMatrixSolver-->src/main/java/project/sb/Math/GaussJordanMatrixSolver.java</h2>
<p>
One can solve any invertible square matrix using methods of this class.
<br>
We use gauss jordan elimination method to solve the matrix.
<br>
method description:
<br>
1 inverse() : solves any invertible square matrix and returns the solution.<br>
2 isValidSquareMatrix() : Accepts matrix in its parameter and check if it is valid square matrix or not<br>
3 createInitialAns(): private method , used to create an Identity matrix so that we can replicate the computation from problem matrix and generate solution.<br>
4 computeExceptDiagonal():  makes a valid non diagonal element that is passed in method param  0. (also performs necessary computation in that row )<br>
5 computeForDiagonal():  makes a valid  diagonal element that is passed in method param  1.(also performs necessary computation in that row )<br>
6 getDeterminantByElimination(): solves given matrix to find its determinant by gauss elimination method.


</p>
<h2> Eg  </h2>
<pre>
    public static void main(String[] args) {

        //double[][] A1={{7,5,1},{3,2,6},{4,7,9}};
        double[][] A1=   {
                {4, 2, 3, 1},
                {2, 5, 1, 3},
                {1, 1, 4, 2},
                {3, 2, 1, 5}
        };


        double determinant = GaussJordanMatrixSolver.getDeterminantByElimination(A1);
        double[][] inverse = GaussJordanMatrixSolver.inverse(A1);
        System.out.println("__S__O__L__U__T__I__O__N   I__N__V__E__R__S__E \n");
        Arrays.stream(inverse).forEach(ar->System.out.println(Arrays.toString(ar)));
        System.out.println("determinant :"+determinant);
 }
</pre>
