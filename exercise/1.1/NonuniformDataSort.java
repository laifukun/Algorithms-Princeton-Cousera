public class NonuniformDataSort {
    //exercise 2.1.35 and 2.1.36
    public static void main(String[] args) {
        String alg1 = args[0];

        int N = Integer.parseInt(args[1]);
        double basetime = 0.0, time = 0.0;
        StdOut.println("Test for nonuniform random data " + alg1 + "Sort: ");

        Double[] a = new Double[N];
        for (int j = 0; j < N; j++)
            a[j] = StdRandom.uniform();
        time = SortCompare.time(alg1, a);
        basetime = time;

        StdOut.printf("   Uniform:     %.1f     %.1f\n", time, time / basetime);

        for (int j = 0; j < N; j++)
            a[j] = StdRandom.gaussian();
        time = SortCompare.time(alg1, a);
        StdOut.printf("  Gaussian:     %.1f     %.1f\n", time, time / basetime);

        for (int j = 0; j < N; j++)
            a[j] = (double) StdRandom.poisson(1.0);
        time = SortCompare.time(alg1, a);
        StdOut.printf("   Poisson:     %.1f     %.1f\n", time, time / basetime);

        for (int j = 0; j < N; j++)
            a[j] = (double) StdRandom.geometric(.5);
        time = SortCompare.time(alg1, a);
        StdOut.printf(" Geometric:     %.1f     %.1f\n", time, time / basetime);

        double[] prob = new double[N];
        double sum = 0.0;
        for (int j = 0; j < N; j++) {
            prob[j] = StdRandom.uniform();
            sum += prob[j];
        }
        for (int j = 0; j < N; j++) {
            prob[j] /= sum;
        }

        for (int j = 0; j < N; j++)
            a[j] = (double) StdRandom.discrete(prob);

        time = SortCompare.time(alg1, a);
        StdOut.printf("  Discrete:     %.1f     %.1f\n", time, time / basetime);

        StdOut.println();

        for (int j = 0; j < N / 2; j++)
            a[j] = 0.0;
        for (int j = N / 2; j < N; j++)
            a[j] = 1.0;
        time = SortCompare.time(alg1, a);
        StdOut.printf("                     Half 0, Half 1:     %.1f     %.1f\n", time,
                      time / basetime);

        for (int j = 0; j < N / 2; j++)
            a[j] = 0.0;
        for (int j = N / 2; j < N; j++)
            a[j] = StdRandom.uniform();
        time = SortCompare.time(alg1, a);
        StdOut.printf("                Half 0, Half Random:     %.1f     %.1f\n", time,
                      time / basetime);

        int h = N / 2;
        double val = 0.0;

        while (h > 0) {
            for (int j = h; j < 2 * h; j++)
                a[j] = val;
            val++;
            h /= 2;
        }

        time = SortCompare.time(alg1, a);
        StdOut.printf(" Half 0, Half Reminder 1, Further 2:     %.1f     %.1f\n", time,
                      time / basetime);


    }
}
