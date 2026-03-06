# Interpolation

**Location**

```
src/main/java/project/sb/Math/Interpolation.java
```

`Interpolation` provides numerical methods to estimate the value of a function **between known data points**.

Interpolation is commonly used when:

- Values of a function are known at discrete points
- We need to estimate the value at an **intermediate point**

Example

If we know

| x | y |
|---|---|
| 0 | 0 |
| 1 | 1 |
| 2 | 12 |
| 3 | 57 |

we can estimate **y at x = 2.7**.

---

# Methods

The class supports the following interpolation techniques:

- Lagrange Interpolation
- Newton Forward Interpolation
- Newton Backward Interpolation

All methods in this class are **static**.

---

# lagrange()

Computes interpolation using the **Lagrange polynomial**.

The general formula:

y(x) = Σ L<sub>i</sub>(x) y<sub>i</sub>

Where

L<sub>i</sub>(x) = Π (x − x<sub>j</sub>) / (x<sub>i</sub> − x<sub>j</sub>)  
for j ≠ i

---

### Example (4 data points)

```
y(x) =
[(x-x₁)(x-x₂)(x-x₃)y₀] / [(x₀-x₁)(x₀-x₂)(x₀-x₃)] +

[(x-x₀)(x-x₂)(x-x₃)y₁] / [(x₁-x₀)(x₁-x₂)(x₁-x₃)] +

[(x-x₀)(x-x₁)(x-x₃)y₂] / [(x₂-x₀)(x₂-x₁)(x₂-x₃)] +

[(x-x₀)(x-x₁)(x-x₂)y₃] / [(x₃-x₀)(x₃-x₁)(x₃-x₂)]
```

---

## Parameters

| Parameter | Type | Description |
|----------|------|-------------|
| `ax` | `double[]` | array containing x values |
| `ay` | `double[]` | array containing y values |
| `x` | `double` | position where y needs to be calculated |

---

## Returns

```
double
```

Estimated value

y<sub>p</sub> = y(x)

---

# newtonForward()

Computes interpolation using **Newton Forward Difference Method**.

This method is suitable when the required value is **near the beginning of the table**.

---

### Formula

For 4 data points:

```
y(x) = y₀
     + uΔy₀
     + [u(u−1)/2!]Δ²y₀
     + [u(u−1)(u−2)/3!]Δ³y₀
```

Where

```
u = (x - x₀) / h
h = x₁ - x₀
```

Forward differences:

```
Δy₀ = y₁ − y₀

Δ²y₀ = Δy₁ − Δy₀

Δ³y₀ = Δ²y₁ − Δ²y₀
```

---

## Parameters

| Parameter | Type | Description |
|----------|------|-------------|
| `ax` | `double[]` | array of x values |
| `ay` | `double[]` | array of y values |
| `x` | `double` | position where y is required |

---

## Returns

```
double
```

Estimated value

y<sub>p</sub>

---

# newtonBackward()

Computes interpolation using **Newton Backward Difference Method**.

This method is preferred when the required value is **near the end of the table**.

---

### Formula

For 4 data points:

```
y(x) = y₃
     + u∇y₃
     + [u(u+1)/2!]∇²y₃
     + [u(u+1)(u+2)/3!]∇³y₃
```

Where

```
u = (x - x₃) / h
h = x₁ - x₀
```

Backward differences:

```
∇y₃ = y₃ − y₂

∇²y₃ = ∇y₃ − ∇y₂

∇³y₃ = ∇²y₃ − ∇²y₂
```

---

## Parameters

| Parameter | Type | Description |
|----------|------|-------------|
| `ax` | `double[]` | array of x values |
| `ay` | `double[]` | array of y values |
| `x` | `double` | position where y is required |

---

## Returns

```
double
```

Estimated value

y<sub>p</sub>

---

# Example

```java
public static void main(String[] args) {

    double[] ax = {0, 1, 2, 3};
    double[] ay = {0, 1, 12, 57};

    double x = 2.7;

    double forward = Interpolation.newtonForward(ax, ay, x);
    double backward = Interpolation.newtonBackward(ax, ay, x);
    double lagrange = Interpolation.lagrange(ax, ay, x);

    System.out.println("Newton Forward: " + forward);
    System.out.println("Lagrange: " + lagrange);
    System.out.println("Newton Backward: " + backward);

}
```

---

# Methods Summary

| Method | Best Used When | Requirement |
|------|------|------|
| Lagrange | Any point in table | No equal spacing required |
| Newton Forward | Near beginning | Equal spacing required |
| Newton Backward | Near end | Equal spacing required |

---

# Notes

- Arrays `ax` and `ay` must have the **same length**.
- Newton Forward and Backward methods assume **equally spaced x values**.
- Lagrange interpolation works for **unequally spaced data**.

---