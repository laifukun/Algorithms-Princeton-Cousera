public class RunningTime {

    public static double timeRandomInput(String alg, int N,
                                         int T) { // Use alg to sort T random arrays of length N.
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) { // Perform one experiment (generate and sort an array).
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += SortCompare.time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        int N = Integer.parseInt(args[1]);
        int T = Integer.parseInt(args[2]);
        int R = Integer.parseInt(args[3]);
        double basetime = 0.0;
        StdOut.println("Average Running Time for " + alg1 + "Sort: ");

        int w = 600;
        int h = 300;
        StdDraw.setCanvasSize(w, h);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, 1 + 0.4 / T);


        double[] time = new double[T];
        double max = 1.0;
        for (int i = 1; i <= T; i++, N *= 2) {


            time[i - 1] = timeRandomInput(alg1, N, R) / R;
            if (i == 1) basetime = time[i - 1];

            StdOut.printf(" %6d:     %.1f     %.1f\n", N, time[i - 1], time[i - 1] / basetime);
            //max = time[i - 1] > max ? time[i - 1] : max;
        }
        max = time[T - 1] * 1.1;
        StdDraw.setYscale(0, max);
        for (int i = 0; i < T; i++) {
            double x = 1.0 * i / T + 0.6 / T;
            double y = time[i] / 2.0;
            double rw = 0.4 / T;
            double rh = time[i] / 2.0;

            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
