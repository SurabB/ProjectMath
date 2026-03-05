<h1>Interpolation-->src/main/java/project/sb/Math/Interpolation.java</h1>
<hr>
<p> One can solve interpolation problems using methods of this class</p>
<br>
<ol>
<li>lagrange(): Follows lagrange interpolation formula:<br>
<pre>
For x and y consisting of 4 values.
y<sub>x</sub> =[(x-x<sub>1</sub>)*(x-x<sub>2</sub>)*(x-x<sub>3</sub>)*y<sub>0</sub>]/[(x<sub>0</sub>-x<sub>1</sub>)*(x<sub>0</sub>-x<sub>2</sub>)*(x<sub>0</sub>-x<sub>3</sub>)]+
     [(x-x<sub>0</sub>)*(x-x<sub>2</sub>)*(x-x<sub>3</sub>)*y<sub>1</sub>]/[(x<sub>1</sub>-x<sub>0</sub>)*(x<sub>1</sub>-x<sub>2</sub>)*(x<sub>1</sub>-x<sub>3</sub>)]+
     [(x-x<sub>0</sub>)*(x-x<sub>1</sub>)*(x-x<sub>3</sub>)*y<sub>2</sub>]/[(x<sub>2</sub>-x<sub>0</sub>)*(x<sub>2</sub>-x<sub>1</sub>)*(x<sub>2</sub>-x<sub>3</sub>)]+
     [(x-x<sub>0</sub>)*(x-x<sub>1</sub>)*(x-x<sub>2</sub>)*y<sub>3</sub>]/[(x<sub>3</sub>-x<sub>0</sub>)*(x<sub>3</sub>-x<sub>1</sub>)*(x<sub>3</sub>-x<sub>2</sub>)]
</pre>
<ul>
<li>params: ax-> array of x, ay-> array of y, x-> x at position p(x(p)) which y needs to be calculated </li>
<li> returns: y<sub>p</sub>-> y at position x(p)</li>
</ul>
</li>

<li>
newtonForward():  Follows newton forward interpolation formula.
<pre>
For x and y consisting of 4 values (where u = (x - x<sub>0</sub>) / h):

y<sub>x</sub> = y<sub>0</sub> + u&Delta;y<sub>0</sub> + [u(u-1)/2!]&Delta;<sup>2</sup>y<sub>0</sub> + [u(u-1)(u-2)/3!]&Delta;<sup>3</sup>y<sub>0</sub>

Where:
u      = (x - x<sub>0</sub>) / h
h      = x<sub>1</sub> - x<sub>0</sub>
&Delta;y<sub>0</sub>     = y<sub>1</sub> - y<sub>0</sub>
&Delta;<sup>2</sup>y<sub>0</sub>    = &Delta;y<sub>1</sub> - &Delta;y<sub>0</sub>
&Delta;<sup>3</sup>y<sub>0</sub>    = &Delta;<sup>2</sup>y<sub>1</sub> - &Delta;<sup>2</sup>y<sub>0</sub>
</pre>
<ul>
<li>params: ax-> array of x, ay-> array of y, x-> x at position p(x(p)) which y needs to be calculated </li>
<li> returns: y<sub>p</sub>-> y at position x(p)</li>
</ul>

</li>

<li> newtonBackward()-> Follows newton backward interpolation formula:
<pre>


For x and y consisting of 4 values (where u = (x - x<sub>3</sub>) / h):

y<sub>x</sub> = y<sub>3</sub> + u&nabla;y<sub>3</sub> + [u(u+1)/2!]&nabla;<sup>2</sup>y<sub>3</sub> + [u(u+1)(u+2)/3!]&nabla;<sup>3</sup>y<sub>3</sub>

Where:
u      = (x - x<sub>3</sub>) / h
h      = x<sub>1</sub> - x<sub>0</sub>
&nabla;y<sub>3</sub>     = y<sub>3</sub> - y<sub>2</sub>
&nabla;<sup>2</sup>y<sub>3</sub>    = &nabla;y<sub>3</sub> - &nabla;y<sub>2</sub>
&nabla;<sup>3</sup>y<sub>3</sub>    = &nabla;<sup>2</sup>y<sub>3</sub> - &nabla;<sup>2</sup>y<sub>2</sub>
</pre>
<ul>
<li>params: ax-> array of x, ay-> array of y, x-> x at position p(x(p)) which y needs to be calculated </li>
<li> returns: y<sub>p</sub>-> y at position x(p)</li>
</ul>
</li>

</ol>
<h2>Eg:</h2>
<pre>
    public static void main(String[] args) {
        double[] ax = {0, 1, 2, 3};
        double[] ay = {0, 1, 12, 57};
        double x = 2.7;
        double forward = Interpolation.newtonForward(ax, ay, x);
        double back = Interpolation.newtonBackward(ax, ay, x);
        double lagrange = Interpolation.lagrange(ax, ay, x);
        System.out.println("N Forward:" + forward);
        System.out.println("lagrange:" + lagrange);
        System.out.println("N Backward:" + back);

    }
</pre>