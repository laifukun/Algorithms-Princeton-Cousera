import java.util.Arrays;

public class ThreeSum {
    public static int countBinarySearch(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                if (BinarySearch.rank(-a[i] - a[j], a) > j)
                    cnt++;
        return cnt;

    }

    public static int naiveImplement(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    if (i < j && j < k)
                        if (a[i] + a[j] + a[k] == 0)
                            cnt++;
        return cnt;
    }

    public static void DoublingTest(int M) {
        // int M = 8;
        double x[] = new double[M];
        double y1[] = new double[M];
        double y2[] = new double[M];
        for (int N = 250, i = 0; i < M; i++, N = 2 * N) {
            int[] a = new int[N];
            for (int j = 0; j < N; j++)
                a[j] = StdRandom.uniform(-10000000, 10000000);
            Stopwatch timer = new Stopwatch();
            int cnt = ThreeSum.countBinarySearch(a);
            double time = timer.elapsedTime();
            x[i] = (double) N;
            y1[i] = time;
            timer = new Stopwatch();
            cnt = ThreeSum.naiveImplement(a);
            time = timer.elapsedTime();
            y2[i] = time;
            StdOut.printf("%7d %5.1f, %5.1f\n", N, y1[i], y2[i]);

        }
        TwoDimLines twoDline = new TwoDimLines(1000, 800, 0, 35000, 0, 30);
        twoDline.addLine(x, y1, true);
        twoDline.addLine(x, y2, true);
    }

    public static void main(String[] args) {

        int M = StdIn.readInt();
        DoublingTest(M);


    }
}
