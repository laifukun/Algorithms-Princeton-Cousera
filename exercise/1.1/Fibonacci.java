public class Fibonacci {
    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    public static long F(int N, long[] a) {
        if (N == 0) {
            a[N] = 0;
            return 0;
        }
        if (N == 1) {
            a[N] = 1;
            return 1;
        }
        a[N - 1] = F(N - 1, a);
        a[N - 2] = F(N - 2, a);
        return a[N - 1] + a[N - 2];
    }

    public static void main(String[] args) {
        int M = 50;
        long starttime = System.nanoTime();
        for (int N = 0; N < M; N++)
            StdOut.println(N + " " + F(N));
        long timeElapsed = System.nanoTime() - starttime;
        StdOut.println("Ver 1 Execution time: " + timeElapsed / 1000000);

        starttime = System.nanoTime();

        long[] a = new long[M];
        a[M - 1] = F(M - 1, a);

        for (int i = 0; i < M; i++)
            StdOut.println(i + " " + a[i]);
        timeElapsed = System.nanoTime() - starttime;
        StdOut.println("Ver 2 Execution time: " + timeElapsed / 1000000);
    }
}
