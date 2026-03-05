package project.sb.math;

public class Interpolation {


    public static double lagrange(double[] ax, double[] ay, double x) {
        if (ax == null || ay == null) {
            throw new IllegalArgumentException("Arrays must not be null");
        }

        int length = ax.length;

        if (length <= 1) {
            throw new IllegalArgumentException("length of ax should be greater than 1");
        }
        if (length != ay.length) {
            throw new IllegalArgumentException("ax length should be equal to ay length");

        }

        double total = 0;
        for (int i = 0; i < length; i++) {
            double currX = ax[i];
            double upper = 1;
            double lower = 1;
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    continue;
                }
                upper = upper * (x - ax[j]);
                lower = lower * (currX - ax[j]);

            }
            if (Math.abs(lower) < 1e-10) {
                throw new ArithmeticException("Duplicate x not allowed");
            }
            total += (upper / lower) * ay[i];
        }
        return total;
    }

    public static double newtonForward(double[] ax, double[] ay, double x) {
        if (ax == null || ay == null) {
            throw new IllegalArgumentException("Arrays must not be null");
        }
        int length = ax.length;
        if (length <= 1) {

            throw new IllegalArgumentException("length of ax should be greater than 1");
        }
        if (length != ay.length) {
            throw new IllegalArgumentException("ax length should be equal to ay length");

        }
        double[][] ans = new double[length][];
        double h = ax[1] - ax[0];
        for (int i = 1; i < length; i++) {
            if (ax[i - 1] >= ax[i]) {
                throw new IllegalArgumentException("values of ax should be strictly increasing");

            }
            if (!(Math.abs(ax[i] - ax[i - 1] - h) < 1.0e-10)) {
                throw new IllegalArgumentException("values of ax should be in equal interval");

            }
        }
        for (int i = 0; i < length; i++) {
            ans[i] = new double[length - i];
        }

        System.arraycopy(ay, 0, ans[0], 0, length);
        for (int i = 0; i < length - 1; i++) {
            double[] curr = ans[i];
            for (int j = 0; j < ans[i].length - 1; j++) {
                ans[i + 1][j] = curr[j + 1] - curr[j];
            }
        }

        double y0 = ay[0];
        double p = (x - ax[0]) / h;
        double yp = y0;
        double factorial = 1;
        double multipleP = 1;

        for (int i = 0; i < length - 1; i++) {
            double currY = ans[i + 1][0];
            multipleP = multipleP * (p - i);
            factorial = factorial * (i + 1);
            yp = yp + ((multipleP * currY) / factorial);

        }

        return yp;
    }

    public static double newtonBackward(double[] ax, double[] ay, double x) {
        if (ax == null || ay == null) {
            throw new IllegalArgumentException("Arrays must not be null");
        }
        int n = ax.length;
        if (n <= 1) {
            throw new IllegalArgumentException("length of ax should be greater than 1");
        }
        if (n != ay.length) {
            throw new IllegalArgumentException("ax length should be equal to ay length");

        }
        double[][] ans = new double[n][];
        double h = ax[1] - ax[0];
        for (int i = 1; i < n; i++) {
            if (ax[i - 1] >= ax[i]) {
                throw new IllegalArgumentException("values of ax should be strictly increasing");

            }
            if (!(Math.abs(ax[i] - ax[i - 1] - h) < 1.0e-10)) {
                throw new IllegalArgumentException("values of ax should be in equal interval");

            }
        }
        for (int i = 0; i < n; i++) {
            ans[i] = new double[n - i];
        }

        System.arraycopy(ay, 0, ans[0], 0, n);
        for (int i = 0; i < n - 1; i++) {
            double[] curr = ans[i];
            for (int j = 0; j < ans[i].length - 1; j++) {
                ans[i + 1][j] = curr[j + 1] - curr[j];
            }
        }

        double yn = ay[n - 1];

        double p = (x - ax[n - 1]) / h;
        double yp = yn;
        double factorial = 1;
        double multipleP = 1;
        for (int i = 0; i < n - 1; i++) {

            double currY = ans[i + 1][ans[i + 1].length - 1];
            multipleP = multipleP * (p + i);
            factorial = factorial * (i + 1);
            yp = yp + ((multipleP * currY) / factorial);

        }

        return yp;
    }

}
