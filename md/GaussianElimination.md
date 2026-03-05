<h2>GaussianElimination-->src/main/java/project/sb/Math/GaussianElimination.java</h2>
<p>
One can solve any invertible square matrix using methods of this class.
<br>
We use gauss Elimination method to solve the matrix.
</p>

<div>
method description:
<br>
1 isValidSquareMatrix() : Accepts matrix in its parameter and check if it is valid square matrix or not<br>
2 solveEquation(): Takes parameter A and B for Ax=B, solves it to produce eqn values like for 3*3 A, it provides soln of x,y,z.<br>
3 getDeterminant(): params:square matrix, returns determinant of provided matrix. 
</div>
<h2>Eg: </h2>
<pre>

    public static void main(String[] args) {
        double[][] A = {
                {1, 1, 1, 1},
                {2, -1, 1, 3},
                {1, 3, 2, -1},
                {3, 1, -1, 2}
        };
        double[] b = {10, 5, 12, 7};

        double[] expectedSolution = {
                -3.8,
                6.8,
                0.8,
                6.2
        };

        double expectedDeterminant = 15;
        double determinant = GaussianElimination.getDeterminant(A);
        System.out.println("deter:"+determinant);
        System.out.println("gaussian soln:"+Arrays.toString(GaussianElimination.solveEquation(A,b)));
        double[] doubles = GaussJordan.solveEqn(A, b);
        System.out.println("jordan soln:"+Arrays.toString(doubles));

        if(doubles.length==0) System.exit(0);
        for(int i=0;i<A.length;i++){
            double total=0;
            for(int j=0;j<A.length;j++){
                total+=(A[i][j]*doubles[j]);
            }
            System.out.println("total:"+total+", B3:"+b[i]);
        }
    }
</pre>
