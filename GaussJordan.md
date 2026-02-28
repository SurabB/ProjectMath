<h2>GaussJordan-->src/main/java/project/sb/Math/GaussJordan.java</h2>
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
4 solveEqn(): Takes parameter A and B for Ax=B, solves it to produce eqn values like for 3*3 A, it provides soln of x,y,z.

</p>
<h2> Eg  </h2>
<pre>
  
  public static void main(String[] args) {
        //double[][] A1={{7,5,1},{3,2,6},{4,7,9}};
        double[][] matrix = {
                {2.5, -1.2, 0.5, 3.1},
                {0.4, 4.0, -1.1, 0.0},
                {1.2, 0.0, 5.0, -2.2},
                {-3.0, 1.5, 0.0, 6.0}
        };
        double determinant = GaussianElimination.getDeterminant(matrix);
        System.out.println("determinant :"+determinant);
        double[][] inverse = GaussJordan.inverse(matrix);
        System.out.println("__S__O__L__U__T__I__O__N   I__N__V__E__R__S__E \n");
        Arrays.stream(inverse).forEach(ar->System.out.println(Arrays.toString(ar)));
    }
</pre>
