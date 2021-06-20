public class HeapSort {

    public static void sort(Comparable[] a) {
        int N = a.length - 1;
        for (int i = N / 2; i >= 1; i--)
            sink(a, i, N);
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }

    }

    protected static void swim(Comparable[] a, int k) {
        while (k > 1 && less(a[k / 2], a[k])) {
            exch(a, k / 2, k);
            k = k / 2;
        }
    }

    protected static void sink(Comparable[] a, int k, int N) {

        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a[j], a[j + 1])) j++;
            if (less(a[k], a[j])) {
                exch(a, k, j);
                k = j;
            }
            else break;
        }
    }


    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        int w = 600;
        int h = 300;
        Double[] a = new Double[N];
        double max = Double.MIN_VALUE;
        for (int i = 1; i < N; i++) {
            a[i] = StdRandom.uniform();
            max = max > a[i] ? max : a[i];
        }

        sort(a);

        for (int i = 0; i < N; i++)
            StdOut.print(a[i] + " ");

    }
}
