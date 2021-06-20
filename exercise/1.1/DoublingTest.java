public class DoublingTest {
    public static void main(String[] args) {
        String alg1 = args[0];

        int N = Integer.parseInt(args[1]);
        int T = Integer.parseInt(args[2]);
        double basetime = 0.0, time = 0.0;
        StdOut.println("Doubling Test for " + alg1 + "Sort: ");

        for (int i = 1; i <= T; i++, N *= 2) {

            Double[] a = new Double[N];
            for (int j = 0; j < N; j++)
                a[j] = StdRandom.uniform();
            time = SortCompare.time(alg1, a);
            if (i == 1) basetime = time;

            StdOut.printf(" %6d:     %.1f     %.1f\n", N, time, time / basetime);
        }

    }
}
