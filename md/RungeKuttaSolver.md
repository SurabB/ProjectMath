# RungeKuttaSolver

**Location:**
```
src/main/java/project/sb/Math/RungeKuttaSolver.java
```

The `RungeKuttaSolver` class provides utilities to solve **Ordinary Differential Equations (ODEs)** numerically.

It supports:

- Euler Method
- Runge–Kutta 2nd Order (Heun’s Method)
- Runge–Kutta 4th Order

It can handle:

- First-order ODEs: `dy/dx = f(x,y)` 
- Second-order ODEs: `d²y/dx² = g(x,y,z)` (using substitution `z = dy/dx`)

---

# Mathematical Background

## First Order ODE

***
dy/dx = f(x,y) \
y(x<sub>0</sub>) = y<sub>0</sub>
***

## Second Order ODE

***
d²y/dx² = g(x,y,z)\
Let z = dy/dx\
Then:\
  dy/dx = z\
  dz/dx = g(x,y,z)\
y(x<sub>0</sub>) = y<sub>0</sub>\
z(x<sub>0</sub>) = z<sub>0</sub>
***

---

# Methods

## First Order Solvers

### firstOrderDiffUsingEuler()

**System:**
```
dy/dx = f(x,y)
```

**Formula:**
***
y<sub>n+1</sub> = y<sub>n</sub> + h * F[x<sub>n</sub>, y<sub>n</sub>]\
x<sub>n+1</sub> = x<sub>n</sub> + h
***

---

### firstOrderDiffUsingRk2()

**Heun's Method (Rk2):**
***
y<sub>n+1</sub> = y<sub>n</sub> + (h/2) * [F(x<sub>n</sub>,y<sub>n</sub>) + F(x<sub>n+1</sub>, y<sub>n+1</sub><sup>euler</sup>)]\
x<sub>n+1</sub> = x<sub>n</sub> + h\
y<sub>n+1</sub><sup>euler</sup> = y<sub>n</sub> + h * F(x<sub>n</sub>,y<sub>n</sub>)
***

---

### firstOrderDiffUsingRk4()

**System:**
```
dy/dx = f(x,y)
```

**Formula:**
***
k<sub>1</sub> = h * f(x<sub>n</sub>, y<sub>n</sub>)\
k<sub>2</sub> = h * f(x<sub>n</sub> + h/2, y<sub>n</sub> + k<sub>1</sub>/2)\
k<sub>3</sub> = h * f(x<sub>n</sub> + h/2, y<sub>n</sub> + k<sub>2</sub>/2)\
k<sub>4</sub> = h * f(x<sub>n</sub> + h, y<sub>n</sub> + k<sub>3</sub>)\
y<sub>n+1</sub> = y<sub>n</sub> + (1/6) * (k<sub>1</sub> + 2*k<sub>2</sub> + 2*k<sub>3</sub> + k<sub>4</sub>)\
x<sub>n+1</sub> = x<sub>n</sub> + h
***

---

## Second Order Solvers

### secondOrderDiffUsingEuler()

**System:**
```
dy/dx = z
dz/dx = g(x,y,z)
```

**Formulas:**
***
y<sub>n+1</sub> = y<sub>n</sub> + h * F(x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>)\
z<sub>n+1</sub> = z<sub>n</sub> + h * g(x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>)\
x<sub>n+1</sub> = x<sub>n</sub> + h
***

---

### secondOrderDiffUsingRk2()

**Heun's Method (Rk2):**
***
y<sub>n+1</sub> = y<sub>n</sub> + (h/2) * [F(x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>) + F(x<sub>n+1</sub>,y<sub>n+1</sub><sup>euler</sup>,z<sub>n+1</sub><sup>euler</sup>)]\
z<sub>n+1</sub> = z<sub>n</sub> + (h/2) * [g(x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>) + g(x<sub>n+1</sub>,y<sub>n+1</sub><sup>euler</sup>,z<sub>n+1</sub><sup>euler</sup>)]\
x<sub>n+1</sub> = x<sub>n</sub> + h
***

---

### secondOrderDiffUsingRk4()

**System:**
```
dy/dx = z
dz/dx = g(x,y,z)
```

**Formulas:**
***
k<sub>1</sub> = h * F(x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>)\
l<sub>1</sub> = h * g(x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>)\
k<sub>2</sub> = h * F(x<sub>n</sub>+h/2, y<sub>n</sub>+k<sub>1</sub>/2, z<sub>n</sub>+l<sub>1</sub>/2)\
l<sub>2</sub> = h * g(x<sub>n</sub>+h/2, y<sub>n</sub>+k<sub>1</sub>/2, z<sub>n</sub>+l<sub>1</sub>/2)\
k<sub>3</sub> = h * F(x<sub>n</sub>+h/2, y<sub>n</sub>+k<sub>2</sub>/2, z<sub>n</sub>+l<sub>2</sub>/2)\
l<sub>3</sub> = h * g(x<sub>n</sub>+h/2, y<sub>n</sub>+k<sub>2</sub>/2, z<sub>n</sub>+l<sub>2</sub>/2)\
k<sub>4</sub> = h * F(x<sub>n</sub>+h, y<sub>n</sub>+k<sub>3</sub>, z<sub>n</sub>+l<sub>3</sub>)\
l<sub>4</sub> = h * g(x<sub>n</sub>+h, y<sub>n</sub>+k<sub>3</sub>, z<sub>n</sub>+l<sub>3</sub>)\
y<sub>n+1</sub> = y<sub>n</sub> + (1/6)*(k<sub>1</sub> + 2*k<sub>2</sub> + 2*k<sub>3</sub> + k<sub>4</sub>)\
z<sub>n+1</sub> = z<sub>n</sub> + (1/6)*(l<sub>1</sub> + 2*l<sub>2</sub> + 2*l<sub>3</sub> + l<sub>4</sub>)\
x<sub>n+1</sub> = x<sub>n</sub> + h
***

---

# Parameters and Return Types

### First Order Solvers

| Parameter | Description |
|-----------|-------------|
| x<sub>0</sub> | Initial x |
| y<sub>0</sub> | Initial y |
| h | Step size |
| function | dy/dx |
| iterations | Number of iterations |

**Returns:**  
`HashMap<Integer, List<Double>>` → iteration → `[x, y]`

### Second Order Solvers

| Parameter | Description |
|-----------|-------------|
| x<sub>0</sub> | Initial x |
| y<sub>0</sub> | Initial y |
| z<sub>0</sub> | Initial z |
| h | Step size |
| function | dz/dx |
| iterations | Number of iterations |

**Returns:**  
`HashMap<Integer, List<Double>>` → iteration → `[x, y, z]`

---

# Example

```java
public static void main(String[] args) { 

    HashMap<Integer, List<Double>> values = 
        RungeKuttaSolver.secondOrderDiffUsingRk4(
            0, 0, 0, 0.25, (x, y, z) -> 5 + 4*y - 2*z, 2
        ); 

    values.forEach((key, value) -> { 
        System.out.println("itr: %d , values = %s".formatted(key, value)); 
    }); 
}
```

This will output the **iteration number and computed `[x, y, z]` values** for each step.
