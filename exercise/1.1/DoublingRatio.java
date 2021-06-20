public class DoublingRatio {
    public static double timeTrial(int N) {
        int Max = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-Max, Max);
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.countBinarySearch(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        int M = 8;
        double x[] = new double[M];
        double y[] = new double[M];
        for (int N = 250, i = 0; i < M; i++, N = 2 * N) {
            double time = timeTrial(N);
            x[i] = (double) N;
            y[i] = time;
            StdOut.printf("%7d %5.1f\n", N, time);

        }
        TwoDimLines twoDline = new TwoDimLines(1000, 800, 0, 35000, 0, 30);
        twoDline.addLine(x, y, true);

    }
}
