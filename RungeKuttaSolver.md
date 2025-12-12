<h2>RangeKuttaSolver-->src/main/java/project/sb/Math/RangeKuttaSolver.java</h2>
<p> Takes initial values x<sub>0</sub>, y<sub>0</sub>, h, F(x,y) and no of itr for first order differential equation and perform necessary opr</p>
<p> Takes initial values x<sub>0</sub>, y<sub>0</sub>,z<sub>0</sub>, h, g(x,y) and no of itr for second order differential equation and perform necessary opr</p>

<p>method firstOrderDiffUsingEuler()-> follows Euler formula: y<sub>n+1</sub> = y<sub>n</sub>+h * F[x<sub>n</sub>, y<sub>n</sub>], and x<sub>n+1</sub>= x<sub>n</sub> + h</p>
<p>method secondOrderDiffUsingEuler()-> follows Euler formula: y<sub>n+1</sub> = y<sub>n</sub>+h * F[x<sub>n</sub>, y<sub>n</sub>,z<sub>n</sub>], where[x,y,z] is assumed to be dy/dx=z and x<sub>n+1</sub>= x<sub>n</sub> + h</p>
<p>z<sub>n+1</sub>=z<sub>n</sub>+h*g[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>] ,where g[x,y,z] is assumed to be dz/dx
<br>
and x<sub>n+1</sub>= x<sub>n</sub> + h</p>

<p>method firstOrderDiffUsingRk2()-> follows Heun's formula: y<sub>n+1</sub> = y<sub>n</sub>+(h/2) * [F(x<sub>n</sub>, y<sub>n</sub>) + F(x<sub>n+1</sub>,y<sub>n+1</sub> <sup>euler</sup>)]  and x<sub>n+1</sub>=x<sub>n</sub> + h <br> and y<sub>n+1</sub> <sup>euler</sup>
  =y<sub>n</sub> + h * F[x<sub>n</sub>, y<sub>n</sub>]</p>

<p>method secondOrderDiffUsingRk2()-> follows Heun's formula: y<sub>n+1</sub> = y<sub>n</sub>+(h/2) * [F(x<sub>n</sub>, y<sub>n</sub> , z<sub>n</sub>) + F(x<sub>n+1</sub>,y<sub>n+1</sub> <sup>euler</sup>, z<sub>n+1</sub> <sup>euler</sup>)]  and x<sub>n+1</sub>=x<sub>n</sub> + h <br> and
y<sub>n+1</sub> <sup>euler</sup> =y<sub>n</sub> + h * F[x<sub>n</sub>, y<sub>n</sub> ,z<sub>n</sub>] where F[x<sub>n</sub>, y<sub>n</sub> ,z<sub>n</sub>]=z<sub>n</sub>
<br>
also,
z<sub>n+1</sub> <sup>euler</sup> =z<sub>n</sub> + h * g[x<sub>n</sub>, y<sub>n</sub> ,z<sub>n</sub>] where g[x<sub>n</sub>, y<sub>n</sub> ,z<sub>n</sub>]=dz/dx

</p>

<p>method secondOrderDiffUsingRk4()-><br>
follows formulas:<br>
k<sub>1</sub> =h*F[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>] where F[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>] is assumed to be z<sub>n</sub> =dy/dx, <br>
l<sub>1</sub> = h*g[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>] where g[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>]=dz/dx<br>
k<sub>2</sub> =h*F[x<sub>n</sub>+h/2,y<sub>n</sub>+k<sub>1</sub>/2,z<sub>n</sub>+l<sub>1</sub>/2] where F[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>] is assumed to be z<sub>n</sub> , <br>
l<sub>2</sub> =h*g[x<sub>n</sub>+h/2,y<sub>n</sub>+k<sub>1</sub>/2,z<sub>n</sub>+l<sub>1</sub>/2] where g[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>]=dz/dx<br>
k<sub>3</sub> =h*F[x<sub>n</sub>+h/2,y<sub>n</sub>+k<sub>2</sub>/2,z<sub>n</sub>+l<sub>2</sub>/2] where F[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>] is assumed to be z<sub>n</sub> , <br>
l<sub>3</sub> =h*g[x<sub>n</sub>+h/2,y<sub>n</sub>+k<sub>2</sub>/2,z<sub>n</sub>+l<sub>2</sub>/2] where g[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>]=dz/dx<br>
k<sub>4</sub> =h*F[x<sub>n</sub>+h,y<sub>n</sub>+k<sub>3</sub>,z<sub>n</sub>+l<sub>3</sub>] where F[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>] is assumed to be z<sub>n</sub> , <br>
l<sub>4</sub> =h*g[x<sub>n</sub>+h,y<sub>n</sub>+k<sub>3</sub>,z<sub>n</sub>+l<sub>3</sub>] where g[x<sub>n</sub>,y<sub>n</sub>,z<sub>n</sub>]=dz/dx<br>
y<sub>n+1</sub>=y<sub>n</sub>+(1/6)*(k<sub>1</sub>+ 2*k<sub>2</sub> +2*k<sub>3</sub> +k<sub>4</sub>)<br>
z<sub>n+1</sub>=z<sub>n</sub>+(1/6)*(l<sub>1</sub>+ 2*l<sub>2</sub> +2*l<sub>3</sub> +l<sub>4</sub>)<br>



</p>

<h2> EG: </h2>
<pre>
  public static void main(String[] args) {
           //firstOrderDiffUsingRk2(1,2,0.5,(x,y)->(2*y)/x,2);

       // HashMap<Integer, List<Double>> values =firstOrderDiffUsingEuler(0, 1, 0.1, (x, y) -> 2 * x * y, 3);
        //x0,y0,h,dy/dx,no.of itr

      //  HashMap<Integer, List<Double>> values = firstOrderDiffUsingRk2(1, 2, 0.25, (x, y) -> y + Math.sin(x), 2);
         //x0,y0,h,dy/dx,no.of itr

       // HashMap<Integer, List<Double>> values = secondOrderDiffUsingEuler(0, 0, 1, 0.2, (x, y, z) -> 6 + 3 * y - 2 * z, 2);
        //x0,y0,z0,h,dz/dx,no.of itr

        //HashMap<Integer, List<Double>> values = secondOrderDiffUsingRk2(0, 0, 0, 0.25, (x, y, z) -> 5 - 2 * z + 4 * y, 2);
          //x0,y0,z0,h,dz/dx,no.of itr

        HashMap<Integer, List<Double>> values = RungeKuttaSolver.secondOrderDiffUsingRk4(0, 0, 0, 0.25, (x, y, z )-> 5 + 4 * y - 2 * z, 2);
        //x0,y0,z0,h,dz/dx,no.of itr
        values.forEach((key,value)->{
            System.out.println("itr: %d , values = %s".formatted(key,value));
        });

    }
</pre>