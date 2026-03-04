<h2>RootFinder-->src/main/java/project/sb/Math/RootFinder.java</h2>
<br>
<span>
One can find root by providing non-linear eqn using newtonRaphson,bisection and secant methods in RootFinder class. All the methods in this class are static. <br>
</span>
<ol>
<li>Seed class: inner static class that acts as a blueprint to carry initial guesses</li>
<li>getSeed():method that computes initial guesses based on user provided starting,ending points and steps(jumps).
<ul>
<li> params: start(starting point provided by user for guess), end(ending point provided by user for guess), steps(steps to jump ) ,Function &ltDouble,Double&gt f(equation from which root is calculated)</li>
<li> returns Optional&ltseed&gt(may return empty optional if guesses could not be computed or returns Optional of seed->object consisting of initial guesses computed by method getSeed()). </li>
</ul>
</li>
<li>newtonRaphson():method that calculates root from provided equation.
<ul>
<li> params: Seed (call getSeed() to get its object),Function &ltDouble, Double&gt f(derivative of equation from which root should be calculated ), double eps(precision required) </li>
<li> returns double ->root from provided equation</li>
</ul>
</li>
<li>bisection():method that calculates root from provided equation.
<ul>
<li> params: Seed (call getSeed() to get its object), double eps(precision required) </li>
<li> returns double ->root from provided equation</li>
</ul>
</li>
<li>secant():method that calculates root from provided equation.
<ul>
<li> params: Seed (call getSeed() to get its object), double eps(precision required) </li>
<li> returns double ->root from provided equation</li>
</ul>
</li>
</ol>
<h2>Eg</h2>
<pre>
  public static void main(String[] args) {
        Seed seed = RootFinder.getSeed(-10, 1000, 1, x -> Math.pow(x, 3) - x - 1).get();
        double n = RootFinder.newtonRaphson(seed, x -> 3 * Math.pow(x, 2) - 1, 0.0001);
        double b = RootFinder.bisection(seed, 0.0001);
        double s = RootFinder.secant(seed, 0.0001);
        System.out.println(n);
        System.out.println(b);
        System.out.println(s);
    }
</pre>
