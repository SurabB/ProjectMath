<h2>GaussJordanMatrixSolver-->src/main/java/project/sb/Math/GaussJordanMatrixSolver.java</h2>
<p>
One can solve any invertible square matrix using methods of this class.
<br>
We use gauss jordan elimination method to solve the matrix.
<br>
method description:
<br>
1 GaussJordanMatrixSolver() : accepts invertible square matrix and initialize it.<br>
2 compute() : solves any invertible square matrix and returns the solution.<br>
3 isValidSquareMatrix() : Accepts matrix in its parameter and check if it is valid square matrix or not<br>
4 createInitialAns(): private method , used to create an Identity matrix so that we can replicate the computation from problem matrix and generate solution.<br>
5 getColumn(): Accepts matrix and the column Index in its method parameter. Returns the column based on column Index.<br>
6 computeExceptDiagonal():  makes a valid non diagonal element that is passed in method param  0. (also performs necessary computation in that row )<br>
7 computeForDiagonal():  makes a valid  diagonal element that is passed in method param  1.(also performs necessary computation in that row )<br>
8 getDeterminant(): solves given matrix to find its determinant.
9 eliminateRowCol(): deletes  row and col of an array  and returns a new array. (It doesn't make changes in passed array ,it just copies values except row and col that need to be deleted)

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


        GaussJordanMatrixSolver matrixSolver = new GaussJordanMatrixSolver(A1);
        double[][] compute = matrixSolver.compute();
        Arrays.stream(A1).forEach(ar->System.out.println(Arrays.toString(ar)));
        System.out.println("____S______O________L______U_______T_______I_____O_____N______\n");
        Arrays.stream(compute).forEach(g->System.out.println(Arrays.toString(g)));
    }
</pre>
