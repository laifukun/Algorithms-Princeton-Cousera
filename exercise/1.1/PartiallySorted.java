public class PartiallySorted {
    public static void main(String[] args) {
        String alg1 = args[0];

        int N = Integer.parseInt(args[1]);
        double basetime = 0.0, time = 0.0;
        StdOut.println("Test for Partially Sorted Data, " + alg1 + " Sort: ");

        Double[] a = new Double[N];
        for (int j = 0; j < N; j++)
            a[j] = StdRandom.uniform();
        time = SortCompare.time(alg1, a);
        basetime = time;

        StdOut.printf("             Uniform:     %.1f     %.1f\n", time, time / basetime);

        for (int j = 0; j < 0.95 * N; j++)
            a[j] = (double) j;
        for (int j = (int) 0.95 * N; j < N; j++)
            a[j] = (double) StdRandom.uniform(N);
        time = SortCompare.time(alg1, a);
        StdOut.printf("          95%% sorted:     %.1f     %.1f\n", time, time / basetime);

        for (int j = 0; j < N - 10; j++)
            a[j] = (double) StdRandom.uniform(N);
        for (int j = N - 10; j < N; j++)
            a[j] = (double) j;
        time = SortCompare.time(alg1, a);
        StdOut.printf("     Final 10 Sorted:     %.1f     %.1f\n", time, time / basetime);

        for (int j = 0; j < N; j++)
            a[j] = (double) j;
        for (int j = 0; j < 0.05 * N; j++)
            a[StdRandom.uniform(N)] = (double) StdRandom.uniform(N);
        time = SortCompare.time(alg1, a);
        StdOut.printf(" 5%% random dispersed:     %.1f     %.1f\n", time, time / basetime);

    }
}
