public class RunningTimeDist {
    public static void main(String[] args) {
        String alg1 = args[0];
        int N = Integer.parseInt(args[1]);
        int T = Integer.parseInt(args[2]);

        StdOut.println("Running Time for " + alg1 + "Sort: ");

        int w = 600;
        int h = 300;
        StdDraw.setCanvasSize(w, h);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(0, 1 + 0.4 / T);


        double[] time = new double[T];
        double avgTime = 0;
        double maxTime = Double.MIN_VALUE;
        double minTime = Double.MAX_VALUE;
        for (int i = 1; i <= T; i++) {
            Double[] a = new Double[N];
            for (int j = 0; j < N; j++)
                a[j] = StdRandom.uniform();
            time[i - 1] = SortCompare.time(alg1, a);
            maxTime = maxTime > time[i - 1] ? maxTime : time[i - 1];
            minTime = minTime < time[i - 1] ? minTime : time[i - 1];
            avgTime += time[i - 1];
            //StdOut.printf(" %6d:     %.1f", N, time[i - 1]);
            //max = time[i - 1] > max ? time[i - 1] : max;
        }
        avgTime /= T;
        StdDraw.setYscale(0, maxTime);
        for (int i = 0; i < T; i++) {
            double x = 1.0 * i / T + 0.6 / T;
            double y = time[i];
            //double rw = 0.4 / T;
            //double rh = time[i] / 2.0;

            StdDraw.point(x, y);
        }
        StdDraw.line(0, avgTime, 1 + 0.4 / T, avgTime);

    }
}
