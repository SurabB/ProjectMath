# GaussJordan

**Location**


[GaussJordan.java](../src/main/java/project/sb/math/GaussJordan.java)


The `GaussJordan` class provides utilities to solve linear algebra problems using the **Gauss–Jordan Elimination Method**.

It supports:

- Computing the **inverse of an invertible square matrix**
- Solving **linear systems of equations**
- Validating whether a matrix is a **valid square matrix**

All methods in this class are **static**.

---

# Gauss–Jordan Elimination

Gauss–Jordan elimination converts a matrix into **Reduced Row Echelon Form (RREF)** through row operations.

Elementary row operations used:

1. Swap two rows
2. Multiply a row by a non-zero scalar
3. Add/subtract a multiple of one row to another row

Example transformation:

```
[A | I]  →  [I | A⁻¹]
```

Where

- `A` → original matrix
- `I` → identity matrix
- `A⁻¹` → inverse matrix

---
# Fields
## EPS :
  `private static final` field that helps to control precision for floating point values


# Methods

## inverse()

Computes the **inverse of an invertible square matrix** using Gauss–Jordan elimination.

### Method Signature

```java
public static double[][] inverse(double[][] matrix);
```

### Parameters

| Parameter | Type | Description |
|----------|------|-------------|
| `matrix` | `double[][]` | square matrix whose inverse needs to be computed |

### Returns

```
double[][]
```

Inverse matrix **A<sup>-1</sup>**

### Notes

- Matrix must be **square**
- Matrix must be **invertible**
- Internally converts

```
[A | I] → [I | A⁻¹]
```

---

## isValidSquareMatrix()

Checks whether a matrix is a **valid square matrix**.

### Method Signature

```java
public static boolean isValidSquareMatrix(double[][] matrix);
```

### Parameters

| Parameter | Type | Description |
|----------|------|-------------|
| `matrix` | `double[][]` | matrix to validate |

### Returns

```
boolean
```

- `true` → matrix is square
- `false` → matrix is not square

---


## solveEqn()

Solves a system of linear equations.

For a system

```
Ax = B
```

Where

```
A = coefficient matrix
x = unknown variables
B = constants
```

The method computes:

```
x = A⁻¹ B
```

### Method Signature

```java
public static double[] solveEqn(double[][] A, double[] B);
```

### Parameters

| Parameter | Type | Description |
|----------|------|-------------|
| `A` | `double[][]` | coefficient matrix |
| `B` | `double[]` | constant vector |

### Returns

```
double[]
```

Solution vector

```
[x₁, x₂, x₃, ...]
```

Example for 3×3 system:

```
x
y
z
```

---

# Example

```java
public static void main(String[] args) {

        double[][] matrix = {
                {2.5, -1.2, 0.5, 3.1},
                {0.4, 4.0, -1.1, 0.0},
                {1.2, 0.0, 5.0, -2.2},
                {-3.0, 1.5, 0.0, 6.0}
        };

        double[][] inverse = GaussJordan.inverse(matrix);

        System.out.println("__S__O__L__U__T__I__O__N   I__N__V__E__R__S__E\n");

        Arrays.stream(inverse)
                .forEach(row -> System.out.println(Arrays.toString(row)));

}
```

---

# Example Output

```

__S__O__L__U__T__I__O__N   I__N__V__E__R__S__E

[0.23541172774270938, 0.11596355431150208, 0.001970809174259529, -0.12090676263650475]
[-0.02410670931992801, 0.2307846105509697, 0.05318328525320613, 0.03195567107480507]
[-0.002056496529662069, -0.027705578246836705, 0.1941104224386621, 0.07223634476783489]
[0.12373254120133677, 2.856245180086249E-4, -0.01231041672617178, 0.09822436757971306]
```

---

# Summary

| Method | Description |
|------|-------------|
| `inverse()` | Computes inverse of square matrix |
| `isValidSquareMatrix()` | Validates if matrix is square |
| `solveEqn()` | Solves system of equations Ax = B |

---

# Notes

- Matrix must be **invertible** for inverse computation.
- Determinant must satisfy:

```
det(A) ≠ 0
```

Otherwise, inverse **does not exist**.