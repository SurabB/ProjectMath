# GaussianElimination

**Location**

[GaussianElimination.java](../src/main/java/project/sb/math/GaussianElimination.java)


The `GaussianElimination` class provides utilities to solve **linear algebra problems** using the **Gauss Elimination method**.

It supports:

- Solving **linear systems of equations** (Ax = B)
- Computing the **determinant** of a square matrix
- Validating whether a matrix is a **square matrix**

All methods in this class are **static**.

---

# Fields
## EPS : private static final field that helps to control precision for floating point values


# Methods

## isValidSquareMatrix()

Checks whether a matrix is a **valid square matrix**.

### Method Signature

```java
public static boolean isValidSquareMatrix(double[][] matrix);
```

### Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `matrix` | `double[][]` | matrix to validate |

### Returns

```
boolean
```

- `true` → matrix is square
- `false` → matrix is not square

---

## solveEquation()

Solves a system of linear equations:

```
A * X = B
```

Where:

- `A` → coefficient matrix
- `X` → vector of unknowns (x₁, x₂, …, xₙ)
- `B` → constants vector

### Method Signature

```java
public static double[] solveEquation(double[][] A, double[] B);
```

### Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `A` | `double[][]` | coefficient matrix (must be square) |
| `B` | `double[]` | constant vector |

### Returns

```
double[]
```

Solution vector:


*** 
X = [x<sub>1</sub>, x<sub>2</sub> ..., x<sub>n</sub>]
***


---

## getDeterminant()

Computes the **determinant** of a square matrix.

### Method Signature

```java
public static double getDeterminant(double[][] matrix);
```

### Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `matrix` | `double[][]` | square matrix whose determinant is computed |

### Returns

```
double
```

Determinant value:

```
det(A)
```

---

# Example

```java
public static void main(String[] args) {

    double[][] A = {
            {1, 1, 1, 1},
            {2, -1, 1, 3},
            {1, 3, 2, -1},
            {3, 1, -1, 2}
    };

    double[] b = {10, 5, 12, 7};

    // Expected solutions for reference
    double[] expectedSolution = {-3.8, 6.8, 0.8, 6.2};
    double expectedDeterminant = 15;

    // Compute determinant
    double determinant = GaussianElimination.getDeterminant(A);
    System.out.println("determinant: " + determinant);

    // Solve using Gaussian Elimination
    double[] gaussianSolution = GaussianElimination.solveEquation(A, b);
    System.out.println("Gaussian Solution: " + Arrays.toString(gaussianSolution));

    // Solve using Gauss-Jordan (for comparison)
    double[] jordanSolution = GaussJordan.solveEqn(A, b);
    System.out.println("Gauss-Jordan Solution: " + Arrays.toString(jordanSolution));

    // Verify solution
    if (jordanSolution.length == 0) System.exit(0);

    for (int i = 0; i < A.length; i++) {
        double total = 0;
        for (int j = 0; j < A.length; j++) {
            total += A[i][j] * jordanSolution[j];
        }
        System.out.println("Row " + i + " Check: total = " + total + ", B = " + b[i]);
    }
}
```

---

# Notes

- Matrix `A` **must be square**.
- Gaussian elimination may **fail if the matrix is singular** (determinant = 0).
- The solution vector `X` is verified by computing **A * X ≈ B**.

---

# Summary

| Method | Description |
|--------|-------------|
| `isValidSquareMatrix()` | Checks if a matrix is square |
| `solveEquation()` | Solves Ax = B using Gaussian elimination |
| `getDeterminant()` | Computes determinant of a square matrix |

---

# Mathematical Notation

- x<sub>i</sub> → unknown variable in the solution vector
- det(A) → determinant of matrix A

This notation is used consistently for **linear algebra calculations**.
