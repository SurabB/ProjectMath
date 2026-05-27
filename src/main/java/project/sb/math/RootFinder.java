package project.sb.math;

import java.util.Optional;
import java.util.function.Function;

public class RootFinder {


    public static Optional<Seed> getSeed(double start, double end, double steps, Function<Double, Double> function) {
        if (start >= end) {
            throw new IllegalArgumentException("End must be greater than Start");
        }
        if (end - start < steps) {
            throw new IllegalArgumentException("Steps must be less than End minus Start");

        }


        end = Math.min(end, 1000);
        int interval = (int) ((end - start) / steps);
        for (int i = 0; i < interval; i++) {
            double f = start + (i * steps);
            double s = f + steps;
            double first = function.apply(f);
            double second = function.apply(s);
            if (first * second < 0) {
                return Optional.of(new Seed(f, s, function));
            }

        }
        return Optional.empty();
    }

    public static double newtonRaphson(Seed seed, Function<Double, Double> f, double eps) {
        if (Math.abs(f.apply(seed.F_GUESS)) < 1e-10) {
            throw new ArithmeticException("Derivative too small");
        }
        double maxLoop = 1000;
        double xnow = seed.F_GUESS;
        double xnext = xnow - (seed.function.apply(xnow) / f.apply(xnow));
        for (int i = 0; i < maxLoop; i++) {
            if (Math.abs(xnext - xnow) < eps) {
                return xnext;
            }
            xnow = xnext;
            xnext = xnow - (seed.function.apply(xnow) / f.apply(xnow));

        }

        throw new RuntimeException("Newton Raphson did not converge");
    }

    public static double bisection(Seed seed, double eps) {
        int maxLoop = 1000;

        double a = seed.F_GUESS;
        double b = seed.S_GUESS;

        double fa = seed.function.apply(a);
        double fb = seed.function.apply(b);

        if (fa * fb >= 0) {
            throw new IllegalArgumentException("Invalid interval: f(a) and f(b) must have opposite signs");
        }

        for (int i = 0; i < maxLoop; i++) {

            double mid = (a + b) / 2;
            double fmid = seed.function.apply(mid);

            if (Math.abs(fmid) < eps) {
                return mid;
            }

            if (fa * fmid < 0) {
                b = mid;
                fb = fmid;
            } else {
                a = mid;
                fa = fmid;
            }
        }

        throw new RuntimeException("Bisection Method did not converge");
    }
    public static double secant(Seed seed, double eps) {
        int maxLoop = 1000;

        double a = seed.F_GUESS;
        double b = seed.S_GUESS;

        double fa = seed.function.apply(a);
        double fb = seed.function.apply(b);

        if (fb - fa == 0) {
            throw new ArithmeticException("Division by zero in secant method");
        }

        for (int i = 0; i < maxLoop; i++) {

            double xn =b- (fb*(b-a))/(fb-fa);
            double fxn = seed.function.apply(xn);
            if (Math.abs(fxn) < eps) {
                return xn;
            }
            a=b;
            fa=fb;
            b=xn;
            fb=fxn;
            if (fb - fa == 0) {
                throw new ArithmeticException("Division by zero in secant method");
            }

        }

        throw new RuntimeException("Secant Method did not converge");
    }
    public final static class Seed {
        private final double F_GUESS;
        private final double S_GUESS;
        private final Function<Double, Double> function;

        private Seed(double fguess, double sguess, Function<Double, Double> function) {
            this.function = function;
            this.F_GUESS = fguess;
            this.S_GUESS = sguess;
        }
    }
}
